package gui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import model.*;
import businessLogic.*;

public class Simulation extends JFrame {

    private JTextArea queueState;

    public Simulation() {
        // Set up the window
        setTitle("Queue Simulation");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add a panel for the queue state
        JPanel queuePanel = new JPanel();
        queuePanel.setBorder(BorderFactory.createTitledBorder("Queue State"));
        queuePanel.setLayout(new BorderLayout());
        queueState = new JTextArea();
        queueState.setEditable(false);
        queueState.setBounds(50,100,700,555);
        JScrollPane scrollPane = new JScrollPane(queueState);
        queuePanel.add(scrollPane, BorderLayout.CENTER);
        this.getContentPane().add(queuePanel); // Add the queuePanel to the content pane
        setVisible(true);
    }

    public void setSimulationArea(String filePath) {
        try {
            // Read the content of the file
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));

            // Set the content of the file as the text of the queueState component
            this.queueState.setText(fileContent);
        } catch (IOException e) {
            // Handle the exception if the file cannot be read
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

}
