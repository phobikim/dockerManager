package com.docker.dockermanager.util;

import java.io.*;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class ExcuteCmd {
    public String javaCmd() throws IOException , InterruptedException {
        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");

        System.out.println("실행환경이 윈도우인가? " + isWindows);


        String homeDirectory = System.getProperty("user.home");
        Process process;
        if(isWindows) {
            process = Runtime.getRuntime()
                    .exec(String.format("cmd.exe /c dir %s", homeDirectory));
        } else {
            process = Runtime.getRuntime()
                    .exec(String.format("sh -c ls ps %s" , homeDirectory));
        }
        StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(),(result) -> {
            System.out.println("cmd result = " + result);
        });
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        int exidCode  = process.waitFor();
        assert exidCode == 0 : "exidCode is not 0";

        return process.toString();
    }

    public static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            try{
                new BufferedReader(new InputStreamReader(inputStream, "utf-8")).lines()
                        .forEach(consumer);
            }catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

}
