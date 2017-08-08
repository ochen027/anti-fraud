package com.pwc.aml;

import au.com.bytecode.opencsv.CSVReader;
import com.pwc.aml.riskCountry.entity.RiskCountry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;




public class Test {


    public static void main(String [] args) throws IOException {

        //common.io
        String filePath="C:\\Users\\egao035\\git\\anti-fraud\\document\\example file\\sdnlist.csv";
        String desPath="C:\\Users\\egao035\\git\\anti-fraud\\document\\example file\\watchList.csv";
        //  MultipartFile file=new File(filePath);
        //  File temp = new File("temp", UUID.randomUUID().toString());
        //  FileUtils.writeByteArrayToFile(temp, file.getBytes());
        //   File file=new File(filePath);
        //   String temp=FileUtils.readFileToString(file);
        try{
        //    BufferedReader reader=new BufferedReader(new FileReader(filePath));
        //    String line=null;
            List<String> desList=new ArrayList<String>();
            CSVReader csvReader = new CSVReader(new FileReader(filePath));
            List<String[]> csvList = csvReader.readAll();
            for(int i=1;i<csvList.size();i++){
                String[] list=csvList.get(i);
                if(list!=null){
                    if(list.length>1)
                        desList.add(list[1]);
                    String[] matchStr1=null,matchStr2=null;
                    if(list.length>11){
                        matchStr1=StringUtils.substringsBetween(list[11],"a.k.a. '","';");
                        if(matchStr1!=null){
                            for(String s:matchStr1){
                                desList.add(s);
                            }
                            matchStr2=StringUtils.substringsBetween(list[11],matchStr1[matchStr1.length-1]+"';a.k.a. '","'.");
                        }
                        else{
                            matchStr2=StringUtils.substringsBetween(list[11],"a.k.a. '","'.");
                        }

                        if(matchStr2!=null){
                            for(String ss:matchStr2){
                                desList.add(ss);
                            }
                        }
                    }

                }

            }
//            while((line=reader.readLine())!=null){
//     //           String[] item=line.split(",");
//                String[] list=line.split(",");
//                //            List<String> temp=Arrays.asList(line.split(","));
//                if(list!=null){
//                    if(list.length>1)
//                    desList.add(list[1]);
//                    String[] matchStr1=null,matchStr2=null;
//                    if(list.length>11){
//                        matchStr1=StringUtils.substringsBetween(list[11],"a.k.a.",";");
//                        matchStr2=StringUtils.substringsBetween(list[11],"a.k.a.",".");
//                        if(matchStr1!=null){
//                            for(String s:matchStr1){
//                                desList.add(s);
//                            }
//                        }
//                        if(matchStr2!=null){
//                            for(String s:matchStr2){
//                                desList.add(s);
//                            }
//                        }
//                    }
//
//                }
//            }
            FileUtils.writeLines(new File(desPath),desList);
        }catch(Exception e){
            e.printStackTrace();
        }
        //     List<String> list1=Arrays.asList(temp.split("[/.]"));
        //     String[] list=temp.split("[].[)]]");
        //     System.out.print(list);
        //     FileUtils.writeStringToFile(file,list);
        //     FileUtils.writeLines(new File(desPath),list1);
//        for (String s : list){
//            FileUtils.writeLines(new File(desPath),s);
//            System.out.println(s);
//        }
    }

//    private static List<String> splitToList(String sss) {
//        return Arrays.asList(sss.split(";"));
//    }
//    public static void readFile(String path){
//        try {
//            String encoding = "GBK";
//            File file = new File(path);
//            if (file.isFile() && file.exists()) {
//                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
//                BufferedReader bf=new BufferedReader(read);
//                String indexLine=null;
//                while((indexLine=bf.readLine())!=null){
//                    System.out.println(indexLine);
//                }
//                read.close();
//            }else{
//                System.out.println("file does not exist");
//            }
//        }catch (Exception e) {
//                System.out.println("读取文件内容出错");
//                e.printStackTrace();
//        }
//    }

}
