package com.pwc.aml;

import org.apache.commons.lang.StringUtils;

public class TempTest {
    public static void main(String [] args){
        String temp="aabbddaaccdd";
        String[] list=null;
        list=StringUtils.substringsBetween(temp,"aa","dd");
        for(String ss:list){
            System.out.println(ss);
        }
    }
}
