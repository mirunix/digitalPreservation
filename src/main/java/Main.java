import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
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
