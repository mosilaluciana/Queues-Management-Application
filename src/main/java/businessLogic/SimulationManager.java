package businessLogic;
import model.*;
import gui.*;

import java.io.*;
import java.util.*;

public class SimulationManager implements Runnable {

    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;

    private Scheduler scheduler; //entity responsible with queue management and client distribution
    private List<Task> generatedTasks;
    private SimulationFrame frame; //frame from displaying simulation and contains all the input data

    private String outputFileName = "output.txt";

    private int Q;
    private int N;
    private int simulationTime;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int maxServiceTime;
    private int minServiceTime;
    private Simulation simulation;

    public SimulationManager(SimulationFrame frame, Simulation simulation) {
        this.simulation = simulation;
        this.Q = frame.getQ();
        this.N = frame.getN();
        this.simulationTime = frame.getSimulationTime();
        this.maxArrivalTime = frame.getMaxArrivalTime();
        this.minArrivalTime = frame.getMinArrivalTime();
        this.maxServiceTime = frame.getMaxServiceTime();
        this.minServiceTime = frame.getMinServiceTime();
        this.generatedTasks = new ArrayList<>();
        generateNRandomTasks();
        for (int i = 0; i < Q; i++) {
            Server server = new Server();
            Thread t = new Thread(server);
            t.start();
        }

        scheduler = new Scheduler(frame.getQ());
        scheduler.changeStrategy(selectionPolicy);
    }

    private void generateNRandomTasks() {

        for (int i = 0; i < N; i++) {
            int serviceTime = (int) (Math.random() * (maxServiceTime - minServiceTime) + minServiceTime);
            int arrivalTime = (int) (Math.random() * (maxArrivalTime - minArrivalTime) + minArrivalTime);
            Task task = new Task(i + 1, arrivalTime, serviceTime);
            generatedTasks.add(task);
        }
        Collections.sort(generatedTasks);
    }

    public void run() {
        int currentTime = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            // Create a BufferedReader to read the contents of the output file
            BufferedReader reader = new BufferedReader(new FileReader(outputFileName));
            String line;
            while ((line = reader.readLine()) != null) {
                // Display the contents of the file on the simulation frame
                simulation.setSimulationArea(line);
            }
            reader.close();

            while (currentTime < simulationTime) {
                // Check if any new tasks have arrived and dispatch them to the scheduler
                List<Task> arrivedTasks = new ArrayList<>();
                for (Task task : generatedTasks) {
                    if (task.getArrivalTime() == currentTime) {
                        scheduler.dispatchTask(task);
                        arrivedTasks.add(task);
                    }
                }
                generatedTasks.removeAll(arrivedTasks);

                // Create a new BufferedWriter for each loop iteration to avoid the "Stream closed" error
                BufferedWriter currentWriter = new BufferedWriter(new FileWriter(outputFileName, true));

                // Write the current time to the output file
                currentWriter.write("\nCurrent Time:" + currentTime + "\n");

                // Print the list of waiting tasks
                currentWriter.write("    Waiting Tasks:");
                currentWriter.newLine();
                currentWriter.write("    ");
                for (Task task : generatedTasks)
                    currentWriter.write(task.toString() + " ");
                currentWriter.newLine();

                // Print the current state of each queue
                List<Server> servers = scheduler.getServers();
                for (int i = 0; i < servers.size(); i++) {
                    currentWriter.write("    Queue:" + (i + 1));
                    currentWriter.newLine();
                    Task[] tasks = servers.get(i).getTasks();
                    currentWriter.write("        Tasks: ");
                    for (int j = 0; j < tasks.length; j++)
                        currentWriter.write(tasks[j].toString());
                    currentWriter.newLine();
                }

                // Close the current writer to flush the buffer and release the file
                currentWriter.close();
                simulation.setSimulationArea("D:/PT2023_30226_Mosila_Luciana_Assignment_2/output.txt");
                // Wait for 1 second before moving to the next time step
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                }

                currentTime++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


