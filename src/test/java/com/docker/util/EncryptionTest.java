package com.docker.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;


public class EncryptionTest {
    @Test
    void 암호화() {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        BASE64Decoder base64Decoder = new BASE64Decoder();

        String txt = "김민정입니다";

        System.out.println("source = " + txt);

        String base64Txt = base64Encoder.encode(txt.getBytes());
        System.out.println("Base64 Encode = " + base64Txt);

        try{
            txt = new String(base64Decoder.decodeBuffer(base64Txt));
            System.out.println("base64Decoder = " + txt);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
