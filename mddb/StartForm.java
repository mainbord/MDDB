package com.mddb;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by work on 20.09.2016.
 */
public class StartForm {
    private JPanel panelMain;

    StartForm(Map<String,String> raws){
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayout(raws.keySet().size(),2));

        for (Map.Entry<String,String> temp : raws.entrySet()){
            JTextArea jtext = new JTextArea(temp.getKey());
            jtext.setFont(new Font(jtext.getFont().getName(), Font.BOLD, 16));
            panelMain.add(jtext);
            jtext = new JTextArea(temp.getValue());
            panelMain.add(jtext);
        }
    }

    public static void main(String[] args) {

        String [][] params = new String[10][2];
        Map<String,String> devicesParamsMap = new HashMap<>();

        Connection c = null;
        Statement stmt = null;
        try {
            //Class.forName("org.sqlite.JDBC");
            //To use DriverManager you need sqlite driver in /jre/lib/ext
            c = DriverManager.getConnection("jdbc:sqlite:/home/mainbord/Документы/MDDB");

            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "SELECT * FROM Devices WHERE Company='Motorola'";
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            ArrayList<String> listColumnsNames = new ArrayList<>();
            // Recieving all names of all colums of this table
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String name = rsmd.getColumnName(i);
                listColumnsNames.add(name);
            }
            //printing all data from the table
            while(rs.next()){
                for (String columnName : listColumnsNames){
                    devicesParamsMap.put(columnName,rs.getString(columnName));
                }
            }

            params = new String[listColumnsNames.size()][2];

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("SQL Script sucessfully");


        JFrame frame = new JFrame("Mainbord Device Data Base");
        //Здесь достаём из БД нужный телефон с характеристиками [строк][столбцов]


        frame.setContentPane(new StartForm(devicesParamsMap).panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600,600);
        frame.setVisible(true);
    }

}
