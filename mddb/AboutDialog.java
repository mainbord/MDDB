package com.mddb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by work on 29.09.2016.
 */
class AboutDialog extends JDialog
{
    public AboutDialog(JFrame owner)
    {
        super(owner, "About DialogTest", true);

        // Метка с HTML-форматированием выравнивается по центру.

        add(new JLabel(
                        "<html><h1><i>Все о Java</i></h1><hr>"
                                + "Pro-java.ru уроки Java</html>"),
                BorderLayout.CENTER);

        // При активизации кнопки ОК диалогове окно закрывается.

        JButton ok = new JButton("ok");
        ok.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                setVisible(false);
            }
        });

        // Кнопка ОК помещается в нижнюю часть окна.

        JPanel panel = new JPanel();
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
        setSize(260, 160);
    }
}
