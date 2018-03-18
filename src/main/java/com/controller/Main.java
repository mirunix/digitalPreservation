package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Main {

        @RequestMapping("/")
        public String homepage(){
            return "homepage";
        }

        public static void main(String[] args) {

            Downloader downloader = new Downloader();
            downloader.download("https://data.wien.gv.at/daten/geo?service=WFS&request=GetFeature&version=1.1.0&typeName=ogdwien:AUFZUGOGD&srsName=EPSG:4326&outputFormat=csv", "AUFZUGOGD.csv");
            downloader.download("https://data.wien.gv.at/daten/geo?service=WFS&request=GetFeature&version=1.1.0&typeName=ogdwien:UBAHNHALTOGD&srsName=EPSG:4326&outputFormat=csv", "UBAHNHALTOGD.csv");

            DataProcessor dataProcessor = new DataProcessor();
            dataProcessor.processData();

            //TODO: use data
            for(String station : dataProcessor.getStationenMitAufzug()){
                System.out.println(station);
            }
        }
}
