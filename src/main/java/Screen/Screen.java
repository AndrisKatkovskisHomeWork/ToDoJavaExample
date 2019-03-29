package Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Screen {

    public static void main(String[] args) {

        ArrayList<JPanel> listPanel = new ArrayList<>();

        final JFrame frame = new JFrame();
        final JPanel panel = new JPanel();
        final JLabel label = new JLabel("My to do list");
        final FlowLayout mgr = new FlowLayout();
        mgr.setAlignment(FlowLayout.LEFT);
        panel.setLayout(mgr);

        JButton addButton = new JButton("Add");
        addButton.setBackground(Color.GREEN);

        JButton saveButton = new JButton("Save progres");
        saveButton.setBackground(Color.YELLOW);


        final JButton deleteButton = new JButton("delete");
        final JTextField addTaskField = new JTextField("", 40);
        label.setBounds(10, 10, 100, 100);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                final JLabel labelForQuest1 = new JLabel(addTaskField.getText());
                JButton deleteButtonX = new JButton("[x]");
                deleteButtonX.setBackground(Color.ORANGE);

                final JPanel panelToDo = new JPanel();
                panelToDo.setPreferredSize(new Dimension(445, 30));
                JCheckBox checkBox = new JCheckBox(addTaskField.getText());

                panelToDo.add(checkBox);
                panelToDo.add(deleteButtonX);
                panelToDo.setLayout(mgr);

                panel.add(panelToDo);
                listPanel.add(panelToDo);


                deleteButtonX.addActionListener(d -> {
                    panel.remove(panelToDo);
                    panel.updateUI();
                });

                panel.validate();
                panel.updateUI();

                addTaskField.setText("");
            }
        });

        deleteButton.addActionListener(i -> {
            for (int j = 0; j < listPanel.size(); j++) {
                if (((JCheckBox) listPanel.get(j).getComponent(0)).isSelected()) {
                    panel.remove(listPanel.get(j));
                    panel.updateUI();

                }
            }
        });

        panel.setPreferredSize(new Dimension(500, 800));
        panel.setBackground(Color.PINK);
        panel.add(label);
        panel.add(addButton);
        panel.add(saveButton);
        panel.add(deleteButton);
        panel.add(addTaskField);

        frame.setBounds(50, 50, 500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel); //only panel is add to FRAME.

        frame.setVisible(true);

    }
}
/*

        //texta fails
        //vispirms ielādē no texta faila

        saveButton.addActionListener(e -> {
            try{
                BufferedWriter bufferedWriter  =  new BufferedWriter(new FileWriter("saved.txt"));
                bufferedWriter.write();
        });

    }
    */