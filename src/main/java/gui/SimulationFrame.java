package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import businessLogic.*;
public class SimulationFrame extends JFrame {

    private JFrame frame;

    private JLabel lblClients;
    private JTextField txtClients;
    private JLabel lblQueues;
    private JTextField txtQueues;
    private JLabel lblSimulationInterval;
    private JTextField txtSimulationInterval;

    private JLabel lblMinArrivalTime;
    private JTextField txtMinArrivalTime;
    private JLabel lblMaxArrivalTime;
    private JTextField txtMaxArrivalTime;

    private JLabel lblMinServiceTime;
    private JTextField txtMinServiceTime;
    private JLabel lblMaxServiceTime;
    private JTextField txtMaxServiceTime;
    private JButton startButton;

    private int Q;
    private int N;
    private int simulationTime;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int maxServiceTime;
    private int minServiceTime;

    public SimulationFrame() {

        frame = new JFrame("Input Data");

        lblClients = new JLabel("Number of clients (N):");
        txtClients = new JTextField(10);
        lblQueues = new JLabel("Number of queues (Q):");
        txtQueues = new JTextField(10);
        lblSimulationInterval = new JLabel("Simulation interval (MAX):");
        txtSimulationInterval = new JTextField(10);
        lblMinArrivalTime = new JLabel("Minimum arrival time:");
        txtMinArrivalTime = new JTextField(10);
        lblMaxArrivalTime = new JLabel("Maximum arrival time:");
        txtMaxArrivalTime = new JTextField(10);
        lblMinServiceTime = new JLabel("Minimum service time:");
        txtMinServiceTime = new JTextField(10);
        lblMaxServiceTime = new JLabel("Maximum service time:");
        txtMaxServiceTime = new JTextField(10);
        startButton = new JButton("START");


        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(lblClients);
        panel.add(txtClients);
        panel.add(lblQueues);
        panel.add(txtQueues);
        panel.add(lblSimulationInterval);
        panel.add(txtSimulationInterval);
        panel.add(lblMinArrivalTime);
        panel.add(txtMinArrivalTime);
        panel.add(lblMaxArrivalTime);
        panel.add(txtMaxArrivalTime);
        panel.add(lblMinServiceTime);
        panel.add(txtMinServiceTime);
        panel.add(lblMaxServiceTime);
        panel.add(txtMaxServiceTime);
        panel.add(startButton);

        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400, 200);
        frame.setLocation(700, 300);


        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                N = Integer.parseInt(txtClients.getText());
                Q = Integer.parseInt(txtQueues.getText());
                simulationTime=Integer.parseInt(txtSimulationInterval.getText());
                maxArrivalTime=Integer.parseInt(txtMaxArrivalTime.getText());
                minArrivalTime=Integer.parseInt(txtMinArrivalTime.getText());
                maxServiceTime=Integer.parseInt(txtMaxServiceTime.getText());
                minServiceTime=Integer.parseInt(txtMinServiceTime.getText());

                txtClients.setEnabled(false);
                txtQueues.setEnabled(false);
                txtSimulationInterval.setEnabled(false);
                txtMinArrivalTime.setEnabled(false);
                txtMaxArrivalTime.setEnabled(false);
                txtMinServiceTime.setEnabled(false);
                txtMaxServiceTime.setEnabled(false);
                startButton.setEnabled(false);

                Simulation simulation =  new Simulation();
                SimulationManager gen = new SimulationManager(SimulationFrame.this, simulation);
                Thread t = new Thread(gen);
                t.start();
            }
        });

    }

    public void run() {
        setVisible(true);
    }


    public int start() {
       return 1;
    }
     public int getN(){return this.N;}
     public int getQ(){return this.Q;}
     public int getSimulationTime(){return this.simulationTime;}
     public int getMaxArrivalTime(){return this.maxArrivalTime;}
     public int getMinArrivalTime(){return this.minArrivalTime;}
     public int getMaxServiceTime(){return this.maxServiceTime;}
     public int getMinServiceTime(){return this.minServiceTime;}

    public static void main(String[] args) {
        SimulationFrame inputView = new SimulationFrame();
//            if(inputView.start() == 1) {
//                SimulationManager gen = new SimulationManager(inputView);
//                Thread t = new Thread(gen);
//                t.start();
//            }
    }
}