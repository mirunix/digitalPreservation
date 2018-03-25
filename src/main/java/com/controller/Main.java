package com.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
public class Main {
        static DataProcessor dataProcessor = new DataProcessor();

        @RequestMapping("/")
        public String homepage(){
            return "homepage";
        }

        @RequestMapping(value = "getStationsVienna")
        @ResponseBody
        public List<Station> getStationVienna() {

            return dataProcessor.getStationsVienna();
        }

        @RequestMapping(value = "getStationsBudapest")
        @ResponseBody
        public List<Station> getStationBudapest() {

            return dataProcessor.getStationsBudapest();
        }

        public Main(){
            Downloader downloader = new Downloader();
            downloader.download("https://data.wien.gv.at/daten/geo?service=WFS&request=GetFeature&version=1.1.0&typeName=ogdwien:UBAHNHALTOGD&srsName=EPSG:4326&outputFormat=csv", "UBAHNHALTOGD.csv");
            //TODO: downloader.download("http://www.bkk.hu/gtfs/budapest_gtfs.zip", "budapest_gtfs.zip");
            dataProcessor.processData();

        }

        public static void main(String[] args) {

        }
}
