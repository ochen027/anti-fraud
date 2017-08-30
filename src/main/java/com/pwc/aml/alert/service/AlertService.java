package com.pwc.aml.alert.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pwc.aml.alert.dao.IAlertDAO;
import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.alert.entity.Alerts;
import com.pwc.aml.customers.dao.ICustomerBaseDao;
import com.pwc.aml.rules.entity.Scenario;
import com.pwc.aml.suppress.entity.Suppress;
import com.pwc.aml.suppress.service.ISuppressService;
import com.pwc.aml.transation.entity.Transactions;
import com.pwc.aml.workflow.dao.IWorkflowExDao;
import com.pwc.aml.workflow.entity.WorkObj;
import com.pwc.aml.workflow.service.IWorkObjService;
import com.pwc.aml.workflow.service.IWorkflowExService;
import com.pwc.component.systemConfig.dao.IKeyValueDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class AlertService implements IAlertService{

    @Autowired
    IAlertDAO alertDAO;

    @Autowired
    IWorkflowExService workflowExService;

    @Autowired
    IWorkObjService workObjService;

    @Autowired
    ICustomerBaseDao customerBaseDAO;

    @Autowired
    IKeyValueDao keyValueDAO;

    @Autowired
    IWorkflowExDao workflowExDao;
    @Autowired
    ISuppressService suppressService;
    private List<WorkObj> wList;


    static String DEFAULT_WORKFLOW = "DEFAULT_WORKFLOW";

    @Override
    public List<Alerts> getAllAlertsData() throws Exception {
        return alertDAO.getAllAlertsData();
    }

    @Override
    public Alerts getSingleAlert(String alertId) throws Exception {
        return alertDAO.getSingleAlert(alertId);
    }

    @Override
    public void truncateTable() throws IOException {
        alertDAO.truncateTable();
    }

    @Override
    public List<WorkObj> searchClosedAlerts(AlertSearchEntity ase) throws Exception {
        List<String> customerIdList = null;
        if(StringUtils.isNotEmpty(ase.getCustomerId()) || StringUtils.isNotEmpty(ase.getCustomerName())){
            customerIdList = customerBaseDAO.findByIdAndName(ase.getCustomerId(), ase.getCustomerName());
            if(null == customerIdList){
                return null;
            }
            ase.setAllCustomer(false);
            ase.setCustomerIdList(customerIdList);
        }else{
            ase.setAllCustomer(true);
        }

        String defaultEndId = workflowExService.getWorkflowByDefault().getEndPoint().getFlowPointId();
        wList = workObjService.getWorkObjsByPointId(defaultEndId, ase);
        return wList;

    }


    @Override
    public void createAlertByManually(List<Transactions> tList, Scenario scenario, String userName) throws Exception {
        String alertId = alertDAO.createByManually(tList, scenario, userName);
        workObjService.attach(this.getSingleAlert(alertId),workflowExDao.getWorkflowByFlowId(keyValueDAO.get(DEFAULT_WORKFLOW)),userName);
    }

    @Override
    public ResponseEntity<Void> exportClose( HttpServletResponse response) throws Exception {

        if(null == wList){
            return new ResponseEntity<Void>(HttpStatus.OK);
        }

        Resource resource = new ClassPathResource("fonts/calibri.ttf");
        File file = resource.getFile();

        BaseFont baseFont =
                BaseFont.createFont(file.getAbsolutePath(),BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        Font headfont = new Font(baseFont,20, Font.BOLD);
        Font keyfont = new Font(baseFont,11, Font.BOLD);
        Font textfont = new Font(baseFont,11, Font.NORMAL);

        Document document = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);

        document.open();

        document.open();
        Paragraph title = new Paragraph("Closed Alerts Information",headfont);

        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        float[] widths = { 50f, 50f, 50f, 50f, 50f, 50f};
        PdfPTable table = new PdfPTable(widths);

        table.setSpacingBefore(20f);
        table.setWidthPercentage(100);

        PdfPCell cell = null;
        cell = new PdfPCell(new Paragraph("Alert ID",keyfont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Customer Name",keyfont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Create Date",keyfont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Scenario",keyfont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Description",keyfont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Triggering Values (USD)",keyfont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);



        for(WorkObj s : wList){
            cell = new PdfPCell(new Paragraph(s.getAlerts().getAlertId(),textfont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(String.valueOf(s.getAlerts().getCustomerName()),textfont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(String.valueOf(s.getAlerts().getCreatedDate()),textfont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(String.valueOf(s.getAlerts().getScenarioId()),textfont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(String.valueOf(s.getAlerts().getAlertDesc()),textfont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(String.valueOf(s.getAlerts().getTotalAmt()),textfont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);



        }

        document.add(table);
        document.add(new Paragraph("\n"));
        document.close();
        writer.close();


        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");

        response.setContentType("application/pdf");
        response.addHeader("content-disposition", "attachment; filename=Closed Alert Information.pdf");
        response.setContentLength(baos.size());
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();


        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
