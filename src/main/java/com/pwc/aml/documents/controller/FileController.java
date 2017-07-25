package com.pwc.aml.documents.controller;


import com.pwc.aml.documents.entity.Documents;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by iwen005 on 7/24/2017.
 */
@Controller
@RequestMapping("/file")
public class FileController extends HttpServlet {

	@RequestMapping("/download")
    public void downloadLocal(HttpServletRequest request, HttpServletResponse response, @RequestParam("fileName") String filename) throws FileNotFoundException {
         File file = ResourceUtils.getFile("classpath:static/files/"+filename);
        String fileName = file.getName();
        System.out.println(file.getPath());

        InputStream inStream = new FileInputStream(file);

        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST )
    public ResponseEntity<Documents> singleSave(@RequestBody MultipartFile file ) {
        String fileName = null;
        if (!file.isEmpty()) {
            try {
                fileName = file.getOriginalFilename();
                //String filePath = "C:\\workspace\\anti-fraud\\upload\\" + fileName;
                String filePath = "classpath:static/files/"+fileName;
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream =
                        new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                buffStream.write(bytes);
                buffStream.flush();
                buffStream.close();
                Documents documents = new Documents();
                documents.setDocId(fileName);
                documents.setDocPath(filePath);
                return new ResponseEntity<Documents>(documents, HttpStatus.OK);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }


}

