package businessLogic;
import model.*;
import java.lang.*;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import model.*;

public class ConcreteStrategyTime implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task t) {
        int min = Integer.MAX_VALUE;
        Server minServer = null;
        for(Server server:servers){
            if(min>server.getServiceTime()){
                min = server.getServiceTime();
                minServer = server;
            }
        }
        minServer.addTask(t);
    }
}
