package com.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataProcessor {

    private ArrayList<String> stationenMitAufzug = new ArrayList<>();
    private HashMap<String,List<Integer>> stationenNameUndLinie = new HashMap<>();

    public void processData(){
        //get current path
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);


        //find all files in the resources directory
        File folder = new File(s + "/src/main/res");
        File[] listOfFiles = folder.listFiles();
        String cvsSplitBy = ",";


        if (listOfFiles != null) {
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    if (listOfFile.getName().equals("AUFZUGOGD.csv")) {

                        String line;
                        try (BufferedReader br = new BufferedReader(new FileReader(listOfFile))) {

                            while ((line = br.readLine()) != null) {
                                String[] lineInCsv = line.split(cvsSplitBy);
                                if (!lineInCsv[3].equals("STATION") && !lineInCsv[4].equals("USTR") && !stationenMitAufzug.contains(lineInCsv[3])) {
                                    stationenMitAufzug.add(lineInCsv[3].trim());
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (listOfFile.getName().equals("UBAHNHALTOGD.csv")) {

                        String line;
                        try (BufferedReader br = new BufferedReader(new FileReader(listOfFile))) {

                            while ((line = br.readLine()) != null) {
                                String[] lineInCsv = line.split(cvsSplitBy);
                                if (!lineInCsv[5].equals("HTXT")) {
                                    if (stationenNameUndLinie.containsKey(lineInCsv[5])) {
                                        List<Integer> values = stationenNameUndLinie.get(lineInCsv[5]);
                                        values.add(Integer.valueOf(lineInCsv[3]));
                                        stationenNameUndLinie.put(lineInCsv[5], values);
                                    } else {
                                        List<Integer> values = new ArrayList<>();
                                        values.add(Integer.valueOf(lineInCsv[3]));
                                        stationenNameUndLinie.put(lineInCsv[5], values);
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public ArrayList<String> getStationenMitAufzug() {
        return stationenMitAufzug;
    }

    public void setStationenMitAufzug(ArrayList<String> stationenMitAufzug) {
        this.stationenMitAufzug = stationenMitAufzug;
    }

    public HashMap<String, List<Integer>> getStationenNameUndLinie() {
        return stationenNameUndLinie;
    }

    public void setStationenNameUndLinie(HashMap<String, List<Integer>> stationenNameUndLinie) {
        this.stationenNameUndLinie = stationenNameUndLinie;
    }
}
