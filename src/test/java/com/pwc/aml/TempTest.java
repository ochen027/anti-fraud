package com.pwc.aml;

public class TempTest {
    public static void main(String [] args){
        String s="abcabcbb";
        int i,j,length,count,result;
        length=s.length();
        result=1;
        for(i=0;i<length-1;i++){
            count=1;
            for(j=i+1;j<length;j++)
            {
                if(s.charAt(i)==s.charAt(j)){
                    break;
                }
                else{
                    count++;
                    if(count>result){
                        result=count;
                    }
                }
            }
        }
        System.out.println(s.valueOf(3));
        System.out.println(result);

    }
}
