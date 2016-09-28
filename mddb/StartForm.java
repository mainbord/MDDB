package com.mddb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;

/**
 * Created by work on 20.09.2016.
 */
public class StartForm {
    private JPanel panelMain;
    private JButton openDevice;
    private static JTextField jTextFieldDatabase;





    StartForm(Map<Integer, String> devicesMap){
    //StartForm(Map<String,String> raws, Map<Integer, String> devicesMap){
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayout(raws.keySet().size(),2));
        int additionalPlaces = 0;

        openDevice = new JButton("Show device");
        panelMain.add(openDevice);
        additionalPlaces++;
        jTextFieldDatabase = new JTextField("C:/Users/work/IdeaProjects/MDDB/MDDB.mddb");
        panelMain.add(jTextFieldDatabase);
        additionalPlaces++;
        panelMain.add(new JButton("Other Button"));
        additionalPlaces++;
        panelMain.add(new JLabel(""));
        additionalPlaces++;
        panelMain.add(new JLabel("Введите название устройства"));
        additionalPlaces++;
        panelMain.setLayout(new GridLayout(raws.keySet().size() + additionalPlaces,2));

        openDevice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {








                String [][] params = new String[10][2];
                Map<String,String> devicesParamsMap = new HashMap<>();
                Connection c = null;
                Statement stmt = null;
                try {
                    //Class.forName("org.sqlite.JDBC");
                    //To use DriverManager you need sqlite driver in /jre/lib/ext
                    //linux path
                    //c = DriverManager.getConnection("jdbc:sqlite:/home/mainbord/Документы/MDDB");
                    //windows path

                    String dataBasePath = jTextFieldDatabase.getText();
                    String driverManagerString = "jdbc:sqlite:" + dataBasePath;
                    c = DriverManager.getConnection(driverManagerString);

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
                } catch ( Exception ee ) {
                    System.out.println("Ошибка в блоке работы с базой данных");
                    System.err.println( ee.getClass().getName() + ": " + ee.getMessage() );
                    System.exit(0);
                }
                System.out.println("SQL Script sucessfully");









                for (Map.Entry<String,String> temp : raws.entrySet()){
                    JTextArea jtext = new JTextArea(temp.getKey());
                    jtext.setFont(new Font(jtext.getFont().getName(), Font.BOLD, 16));
                    panelMain.add(jtext);
                    jtext = new JTextArea(temp.getValue());
                    panelMain.add(jtext);
                }
                panelMain.revalidate();
            }
        });

        //List of devices
        /*
        Object []arr = devicesMap.values().toArray();
        String []arr2 = new String[arr.length];
        System.arraycopy(arr, 0, arr2, 0, arr.length);
        panelMain.add(new JList<String>(arr2));
        additionalPlaces++;
*/
    }

    public static void main(String[] args) {

        Map<Integer, String> devicesMap = new TreeMap<>();
        int countDevies = 0;

        JFrame frame = new JFrame("Mainbord Device Data Base");
        //frame.setContentPane(new StartForm(devicesParamsMap, devicesMap).panelMain);
        frame.setContentPane(new StartForm(devicesMap).panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(600,200);
        frame.setVisible(true);







        //Здесь достаём из БД нужный телефон с характеристиками [строк][столбцов]


    }

}
