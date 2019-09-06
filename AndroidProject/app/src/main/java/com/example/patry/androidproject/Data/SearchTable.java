package com.example.patry.androidproject.Data;

import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public class SearchTable {
    public static List<String[]> screenTable;
    public static List<Double[][]> screenTableCal;
    public String[] LaptopScreen = {
            "16 i więcej", "15 - 15.9", "14 - 14.9", "13 - 13.9", "12 - 12.9", "11.9 i  mniej"
    };
    public Double[][] laptopScreenCal = {
            {16.0,100.0},{15.0,15.9},{14.0,14.9},{13.0,13.9},{12.0,12.9},{0.0,11.9}
    };
    public String[] PhonetScreen = {
            "6 i więcej", "5.50 - 5.99", "5.00 - 5.49", "4.50 - 4.99", "4.49 i mniej"
    };
    public Double[][] phoneScreenCal = {
            {6.00,10.0},{5.50,5.99},{5.00,5.49},{4.50,4.99},{0.0,4.49}
    };
    public String[] TabletScreen = {
            "11 i więcej","10 - 10.9", "9 - 9.9", "8 - 8.9", "7.9 i mniej"
    };
    public Double[][] tabletScreenCal = {
            {11.0,100.0},{10.0,10.9},{9.0,9.9},{8.0,8.9},{0.0,7.9}
    };
    SearchTable(){
        screenTable = new ArrayList<>();
        screenTableCal = new ArrayList<>();
        screenTable.add(LaptopScreen);
        screenTableCal.add(laptopScreenCal);
        screenTable.add(PhonetScreen);
        screenTableCal.add(phoneScreenCal);
        screenTable.add(TabletScreen);
        screenTableCal.add(tabletScreenCal);
    }
}
