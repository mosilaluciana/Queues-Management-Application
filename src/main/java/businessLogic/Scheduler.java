package businessLogic;
import model.*;
import java.util.*;
import java.lang.*;

public class Scheduler{

    private List<Server> servers;
    private int maxNoServers;
    private Strategy strategy;

    public Scheduler(int maxNoServers){
        //for maxNoServers
        //create servers objects
        //create thread with the object
        this.maxNoServers=maxNoServers;

        this.servers = new ArrayList<>();
        // Create servers with maxTasksPerServer and add them to the list ??/maxtasksperServer???
        for (int i = 0; i < this.maxNoServers; i++) {
            Server server = new Server();
            servers.add(server);
        }

        // Create a thread for each server and start them
        for (Server server : servers) {
            Thread thread = new Thread(server);
            thread.start();
        }

    }

    public void changeStrategy(SelectionPolicy policy) {
        //apply strategy pattern to instantiate the strategy with the concrete
        //strategy corresponding to policy
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ConcreteStrategyQueue();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
        }
    }

    public void dispatchTask(Task t){
        //call the strategy addTask method
        strategy.addTask(servers,t);
    }

    public List<Server> getServers(){
        return servers;
    }
    public int maxNoServers(){return maxNoServers;}
}

