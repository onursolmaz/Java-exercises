package com.company;

public class Main {

    public static String compress(String text,int n){

        char string[] = text.toCharArray();

        for(int i=0;i<string.length;i++) {
            int count=1;
            for(int j=i+1;j<string.length;j++){

                if(string[i] == string[j] && string[i] != ' ')
                    count++;
            }

            if(count>=n)
                text=text.replace(String.valueOf(string[i]),"");
        }
        return text;

    }


    public static void main(String[] args) {

        String testMsg="aaba kouq bux";
        testMsg=compress(testMsg,3);
        System.out.println(testMsg);

    }
}
