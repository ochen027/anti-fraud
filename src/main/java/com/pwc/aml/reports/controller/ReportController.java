package com.pwc.aml.reports.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pwc.aml.reports.entity.Reports;
import com.pwc.aml.reports.entity.SearchEntity;
import com.pwc.aml.reports.service.IReportsService;
import com.pwc.common.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("reports")
public class ReportController extends BaseController {

    @Autowired
    private IReportsService reportsService;

    private List<Reports> rList;
    @PostMapping("search")
    public ResponseEntity<List<Reports>> SearchReports(@RequestBody SearchEntity se) throws Exception{
        rList = reportsService.SearchByDate(se.getStartDate(), se.getEndDate());
        return new ResponseEntity<List<Reports>>(rList, HttpStatus.OK);
    }

    @GetMapping("export")
    public ResponseEntity<Void> exportReport(SearchEntity se,HttpServletResponse response) throws Exception{
        if(null == rList){
            rList = reportsService.SearchByDate(se.getStartDate(), se.getEndDate());
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
        Paragraph title = new Paragraph("Alert Status Summary by Date",headfont);

        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        float[] widths = {25f, 50f, 50f, 50f, 50f, 50f, 50f};
        PdfPTable table = new PdfPTable(widths);

        table.setSpacingBefore(20f);
        table.setWidthPercentage(100);

        PdfPCell cell = null;
        cell = new PdfPCell(new Paragraph("Date",keyfont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Total Number of Transaction",keyfont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Total Number of Alerts",keyfont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Total Number of  Closed Alerts",keyfont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Total Number of suppressed Alerts",keyfont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Total Number of Alerts Reported to SAR",keyfont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Total Number oAf ctive",keyfont));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        for(Reports r : rList){
            cell = new PdfPCell(new Paragraph(r.getDate(),textfont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(String.valueOf(r.getTransCount()),textfont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(String.valueOf(r.getAlertCount()),textfont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(String.valueOf(r.getAlertClosedCount()),textfont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(String.valueOf(r.getAlertSuppressCount()),textfont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(String.valueOf(r.getAlertSARCount()),textfont));
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(String.valueOf(r.getAlertActiveCount()),textfont));
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
        response.addHeader("content-disposition", "attachment; filename=alert_status_summary_by_date.pdf");
        response.setContentLength(baos.size());
        OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
        os.close();


        return new ResponseEntity<Void>(HttpStatus.OK);
    }




}
