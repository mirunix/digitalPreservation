package com.controller;

import org.springframework.expression.spel.ast.Elvis;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
public class Main {
        DataProcessor dataProcessor = new DataProcessor();

        @RequestMapping("/")
        public String homepage(){
            return "homepage";
        }

        @RequestMapping(value = "getElevators")
        @ResponseBody
        public List<Elevator> getElevators() {

            return dataProcessor.getElevators();
        }

        @RequestMapping(value = "getStations")
        @ResponseBody
        public List<Station> getStation() {

            return dataProcessor.getStations();
        }

        public Main(){
            dataProcessor.processData();
        }

        public static void main(String[] args) {

            Downloader downloader = new Downloader();
            downloader.download("https://data.wien.gv.at/daten/geo?service=WFS&request=GetFeature&version=1.1.0&typeName=ogdwien:AUFZUGOGD&srsName=EPSG:4326&outputFormat=csv", "AUFZUGOGD.csv");
            downloader.download("https://data.wien.gv.at/daten/geo?service=WFS&request=GetFeature&version=1.1.0&typeName=ogdwien:UBAHNHALTOGD&srsName=EPSG:4326&outputFormat=csv", "UBAHNHALTOGD.csv");

        }
}
