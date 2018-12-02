package com.infoshareacademy.homeworks;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class GradeService {

    public String[][] calculateAverage(String[][] data) {

        String[][] emptyArray = new String[][]{{}};
        DecimalFormat number = new DecimalFormat("0.00");
        if (data == null) {
            return emptyArray;
        }
        if (data.length == 0) {
            return emptyArray;
        }

        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormatSymbols sfs = new DecimalFormatSymbols();
        sfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(sfs);
        List<String> students = new ArrayList<>();
        Map<String, Double> studentsToAmountOfGrades = new HashMap<>();

        for(int i = 0; i<data.length; i++){
            if(!students.contains(data[i][0])) {
                students.add(data[i][0]);
                studentsToAmountOfGrades.put(data[i][0], 1.0);
            }else{
                double amount = studentsToAmountOfGrades.get(data[i][0]);
                amount = amount+1.0;
                studentsToAmountOfGrades.put(data[i][0], amount);
            }
        }
        Map<String, Double> studentsToGrades = new HashMap<>();

        for(String s: students){
            studentsToGrades.put(s, 0.0);
        }

        for(int i = 0; i<data.length; i++){
            double grade = studentsToGrades.get(data[i][0]);
            double grade2 = Double.parseDouble(data[i][1]);
            studentsToGrades.put(data[i][0], grade+(grade2));
        }

        String[][] array = new String[students.size()][2];

        Collections.sort(students);
        double division = 1.0;

        for (int i = 0; i<students.size(); i++){


            array[i][0] = students.get(i);
            division = studentsToAmountOfGrades.get(array[i][0]);
            array[i][1] = df.format(studentsToGrades.get(array[i][0])/division);
        }
        return array;
    }
}
