package com.docker.util;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

public class StringUtilTest {
    @Test
    void 인코딩() throws UnsupportedEncodingException {
        String originalStr = "테스트 test 111";
        byte[] bytes = originalStr.getBytes("utf-8");
        originalStr = new String(bytes);

        String[] charSet = {"utf-8", "euc-kr", "ksc5601", "iso-8859-1", "x-windows-949"};
        for(int i = 0; i<charSet.length; i++){
            for(int j = 0; j<charSet.length; j++){
                try{
                    System.out.println("[" + charSet[i] + "," + charSet[j] + "]" + new String(originalStr.getBytes(charSet[i]), charSet[j]));
                } catch (UnsupportedEncodingException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
