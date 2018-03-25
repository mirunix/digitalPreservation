package com.controller;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.*;

/**
 * Created by davideder on 17.03.18.
 */


public class Downloader {

    public static final String RESOURCESPATH = "src/main/res/";

    public void download(String link, String fileName){
        File file = new File(RESOURCESPATH+fileName);
        URL url = null;

        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace(System.err);
        }

        if(url != null) {
            try {
                FileUtils.copyURLToFile(url, file);
            } catch (IOException e){
                e.printStackTrace(System.err);
            }
        }

        //TODO: download zip file

    }
}
