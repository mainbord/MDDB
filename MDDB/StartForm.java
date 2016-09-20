package com.MDDB;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by work on 20.09.2016.
 */
public class StartForm {
    private JPanel panelMain;

    StartForm(String [][] rows){
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayout(rows.length,rows[0].length));
        ArrayList<JTextArea> listOfTextFields = new ArrayList<>();

        /*for (String[] ss : rows){
            for (int j = 0; j < ss.length; j++) {
                //JTextArea jtext = new JTextArea(ss[j]);
                JTextArea jtext = new JTextArea(j + " ");
                if (j%2!=0){
                    jtext.setFont(new Font(jtext.getFont().getName(), Font.BOLD, 18));
                }
                listOfTextFields.add(0,jtext);
            }
        }*/


        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[0].length; j++) {
                JTextArea jtext = new JTextArea(rows[i][j]);
                if (j%2==0){
                    jtext.setFont(new Font(jtext.getFont().getName(), Font.BOLD, 18));
                }
                panelMain.add(jtext);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mainbord Device Data Base");

        //Здесь достаём из БД нужный телефон с характеристиками [строк][столбцов]
        String [][] params = new String[10][2];
        params[0][0] = "Операционная система";
        params[0][1] = "Неизвестно";
        params[1][0] = "Плата";
        params[1][1] = "Неизвестно";
        params[2][0] = "Центральный процессор";
        params[2][1] = "Неизвестно";
        params[3][0] = "Графический ускоритель";
        params[3][1] = "Неизвестно";
        params[4][0] = "Оперативная память (ОЗУ, RAM)";
        params[4][1] = "Неизвестно";
        params[5][0] = "Встроенный накопитель (ПЗУ, ROM)";
        params[5][1] = "Неизвестно";
        params[6][0] = "Аудиочип";
        params[6][1] = "Неизвестно";
        params[7][0] = "Аккумулятор";
        params[7][1] = "Неизвестно";
        params[8][0] = "Форм-фактор";
        params[8][1] = "Неизвестно";
        params[9][0] = "Размеры";
        params[9][1] = "Неизвестно";

        frame.setContentPane(new StartForm(params).panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
