package com.pwc.aml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;


public class Test {


    public static void main(String [] args){
        String sss="aaaa;bbbb;ccc;";

        File a=new File("path");




        List<String>  result=splitToList(sss);
        System.out.println(result);
    }

    private static List<String> splitToList(String sss) {
        return Arrays.asList(sss.split(";"));
    }
    public static void readFile(String path){
        try {
            String encoding = "GBK";
            File file = new File(path);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bf=new BufferedReader(read);
                String indexLine=null;
                while((indexLine=bf.readLine())!=null){
                    System.out.println(indexLine);
                }
                read.close();
            }else{
                System.out.println("file does not exist");
            }
        }catch (Exception e) {
                System.out.println("读取文件内容出错");
                e.printStackTrace();
        }
    }

}
