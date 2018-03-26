package com.controller;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipFile;

public class DataProcessor {

    private List stationsVienna = new ArrayList<>();
    private List stationsBudapest = new ArrayList<>();

    private static final String RESOURCESPATH = "/src/main/res/";
    private String splitBy = ",";


    public void processData() {
        //get current path
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();

        String line;
        File fileVienna = new File(s + RESOURCESPATH + "UBAHNHALTOGD.csv");
        File zipFile = new File(s + RESOURCESPATH + "budapest_gtfs.zip");
        try {
            ZipFile zip = new ZipFile(zipFile);
            InputStream is = zip.getInputStream(zip.getEntry("stops.txt"));
            Files.copy(is, Paths.get(s + RESOURCESPATH + "stops.txt"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File fileBudapest = new File(s + RESOURCESPATH + "stops.txt");

        //vienna
        try (BufferedReader br = new BufferedReader(new FileReader(fileVienna))) {

            while ((line = br.readLine()) != null) {
                String[] lineInCsv = line.split(splitBy);
                if (!lineInCsv[5].equals("HTXT")) {
                    Station station = new Station();
                    station.setName(lineInCsv[5]);

                    //Set coordinates
                    String[] coordinates = parseCoordinates(lineInCsv[2]);
                    station.setLongitude(new BigDecimal(coordinates[0]));
                    station.setLatitude(new BigDecimal(coordinates[1]));
                    station.setCity("Vienna");
                    if (!stationsVienna.contains(station)){
                        stationsVienna.add(station);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //budapest
        try (BufferedReader br = new BufferedReader(new FileReader(fileBudapest))) {

            while ((line = br.readLine()) != null) {

                String[] lineInTxt = line.split(splitBy);

                if(lineInTxt[1].endsWith("M") || lineInTxt[1].contains("M+")){
                        Station station = new Station();
                        station.setName(lineInTxt[1]);

                        //Set coordinates
                        int count = 2;
                        while(!lineInTxt[count].matches("[0-9.]*")){
                            count += 1;
                        }
                        station.setLongitude(new BigDecimal(lineInTxt[count+1]));
                        station.setLatitude(new BigDecimal(lineInTxt[count]));
                        station.setCity("Budapest");
                        if (!stationsBudapest.contains(station)){
                            stationsBudapest.add(station);
                        }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String[] parseCoordinates(String s){
        s = s.replace("POINT (", "");
        s = s.replace(")", "");

        return  s.split(" ");
    }

    public List getStationsVienna() {
        return stationsVienna;
    }

    public List getStationsBudapest() {
        return stationsBudapest;
    }

}
