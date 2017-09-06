package com.pwc.aml.suppress.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pwc.aml.alert.entity.AlertSearchEntity;
import com.pwc.aml.customers.dao.ICustomerBaseDao;
import com.pwc.aml.suppress.dao.ISuppressDao;
import com.pwc.aml.suppress.entity.Suppress;
import com.pwc.common.util.FormatUtils;
import com.pwc.component.authorize.users.entity.Users;
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
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;


@Service
public class SuppressService implements ISuppressService {


    @Autowired
    private ISuppressDao suppressDao;
    @Autowired
    ICustomerBaseDao customerBaseDAO;
    private List<Suppress> sList;
    @Autowired
    ISuppressService suppressService;

    @Override
    public void addSuppress(Suppress suppress, Users users) throws Exception {
        suppress.setSuppressId(UUID.randomUUID().toString());
        suppress.setCreatedBy(users.getUserName());
        suppress.setCreationDate(FormatUtils.getCurrentDay());
        suppress.setLastUpdatedBy(users.getUserName());
        suppress.setLastUpdateDate(FormatUtils.getCurrentDay());
        suppress.setStatus(true);
        suppressDao.createOrUpdate(suppress);
    }

    @Override
    public List<Suppress> findAll() throws Exception {
        return suppressDao.findAll();
    }
    @Override
    public List<Suppress> findSuppress(AlertSearchEntity ase) throws Exception {
        return suppressDao.findSuppress(ase);
    }

    @Override
    public void inActiveSuppress(List<String> idList, String userName) throws Exception {
        if(null != idList && idList.size()>0){
            for(String id : idList){
                Suppress suppress = this.findSuppressById(id);
                suppress.setStatus(false);
                suppress.setLastUpdatedBy(userName);
                suppress.setLastUpdateDate(FormatUtils.getCurrentDay());
                suppressDao.createOrUpdate(suppress);
            }
        }
    }
    @Override
    public List<Suppress> searchSuppress(AlertSearchEntity ase) throws Exception {
        List<String> customerIdList = null;
        if(StringUtils.isNotEmpty(ase.getCustomerId()) || StringUtils.isNotEmpty(ase.getCustomerName())){
            customerIdList = customerBaseDAO.findByIdAndName(ase.getCustomerId(), ase.getCustomerName());
            if(null == customerIdList){
                return null;
            }
            ase.setCustomerIdList(customerIdList);
            ase.setAllCustomer(false);

        }else{
            ase.setAllCustomer(true);
        }
//       List<Suppress> SuppressList=suppressService.findAllActive();
        List<Suppress> SuppressList=this.findSuppress(ase);
        return SuppressList;
    }

    @Override
    public List<Suppress> findAllActive() throws Exception {
        return suppressDao.findAllActive();
    }

    @Override
    public ResponseEntity<Void> export(AlertSearchEntity ase ,HttpServletResponse response) throws Exception {
        if(null == sList){
            sList = suppressService.searchSuppress(ase);
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
        Paragraph title = new Paragraph("Suppress Information",headfont);

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

        cell = new PdfPCell(new Paragraph("Scenario ID",keyfont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Description",keyfont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Create Date",keyfont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Created By",keyfont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);



        for(Suppress s : sList){
            cell = new PdfPCell(new Paragraph(s.getAlerts().getAlertId(),textfont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(String.valueOf(s.getCustomerBase().getCustomerFullName()),textfont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(String.valueOf(s.getScenario().getId()),textfont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(String.valueOf(s.getScenario().getScenarioDesc()),textfont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(String.valueOf(s.getCreationDate()),textfont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(String.valueOf(s.getCreatedBy()),textfont));
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
        response.addHeader("content-disposition", "attachment; filename=suppress_information.pdf");
        response.setContentLength(baos.size());
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();


        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    private Suppress findSuppressById(String suppressId) throws Exception{
        return suppressDao.findSupressById(suppressId);
    }
}
