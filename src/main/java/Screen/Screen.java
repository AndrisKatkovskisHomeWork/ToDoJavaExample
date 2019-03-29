package Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Screen {

    public static void main(String[] args) throws FileNotFoundException {

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
                createToDoPanel(addTaskField.getText(), listPanel, panel, mgr);
                addTaskField.setText("");
            }
        });

        deleteButtonMethod(listPanel, panel, deleteButton);

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


        String filename = "savedFileToDo.txt";
        saveButton.addActionListener(e -> {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));
                for (int j = 0; j < listPanel.size(); j++) {
                    bufferedWriter.write(((JCheckBox) listPanel.get(j).getComponent(0)).getText() + "\n");
                }
                bufferedWriter.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        FileReader fileReader = new FileReader(filename);
        Scanner scanner = new Scanner(fileReader);
        while (scanner.hasNextLine()) {
            createToDoPanel(scanner.nextLine(),listPanel,panel,mgr);
        }

    }

    private static void createToDoPanel(String text, ArrayList<JPanel> listPanel, JPanel panel, FlowLayout mgr) {
        JButton deleteButtonX = new JButton("[x]");
        deleteButtonX.setBackground(Color.ORANGE);

        final JPanel panelToDo = new JPanel();
        panelToDo.setPreferredSize(new Dimension(445, 30));
        JCheckBox checkBox = new JCheckBox(text);

        panelToDo.add(checkBox);
        panelToDo.add(deleteButtonX);
        panelToDo.setLayout(mgr);

        panel.add(panelToDo);
        listPanel.add(panelToDo);


        deleteButtonMethodX(deleteButtonX, panelToDo, panel);

        panel.validate();
        panel.updateUI();


    }

    private static void deleteButtonMethodX(JButton deleteButtonX, JPanel panelToDo, JPanel panel) {
        deleteButtonX.addActionListener(d -> {
            panel.remove(panelToDo);
            panel.updateUI();
        });
    }

    private static void deleteButtonMethod(ArrayList<JPanel> listPanel, JPanel panel, JButton deleteButton) {
        deleteButton.addActionListener(i -> {
            for (int j = 0; j < listPanel.size(); j++) {
                if (((JCheckBox) listPanel.get(j).getComponent(0)).isSelected()) {
                    panel.remove(listPanel.get(j));
                    panel.updateUI();

                }
            }
        });
    }
}