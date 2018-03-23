package com.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DataProcessor {

    private ArrayList<String> stationenMitAufzug = new ArrayList<>();
    private HashMap<String, List<Integer>> stationenNameUndLinie = new HashMap<>();
    private List stations = new ArrayList<>();
    private List elevators = new ArrayList<>();

    public static final String RESOURCESPATH = "/src/main/res/";


    public void processData() {
        //get current path
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();

        String cvsSplitBy = ",";


        File elevatorFile = new File(s + RESOURCESPATH + "AUFZUGOGD.csv");
        File stationFile = new File(s + RESOURCESPATH + "UBAHNHALTOGD.csv");
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(elevatorFile))) {

            while ((line = br.readLine()) != null) {
                String[] lineInCsv = line.split(cvsSplitBy);
                if (!lineInCsv[3].equals("STATION") && !lineInCsv[4].equals("USTR")) {
                    Elevator elevator = new Elevator();
                    elevator.setName(lineInCsv[3].trim());

                    //Set coordinates
                    String[] coordinates = parseCoordinates(lineInCsv[2]);
                    elevator.setLongitude(new BigDecimal(coordinates[0]));
                    elevator.setLatitude(new BigDecimal(coordinates[1]));

                    if(!elevators.contains(elevator))
                        elevators.add(elevator);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(stationFile))) {

            while ((line = br.readLine()) != null) {
                String[] lineInCsv = line.split(cvsSplitBy);
                if (!lineInCsv[5].equals("HTXT")) {
                    Station station = new Station();
                    station.setName(lineInCsv[5]);

                    //Set coordinates
                    String[] coordinates = parseCoordinates(lineInCsv[2]);
                    station.setLongitude(new BigDecimal(coordinates[0]));
                    station.setLatitude(new BigDecimal(coordinates[1]));
                    station.setHasElevator(false);

                    for (Elevator elevator:
                            (List <Elevator>)elevators) {
                        if(elevator.getName().equals(station.getName()))
                            station.setHasElevator(true);
                    }

                    if (!stations.contains(station))
                        stations.add(station);

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

    public List getStations() {
        return stations;
    }

    public List getElevators() {
        return elevators;
    }
}
