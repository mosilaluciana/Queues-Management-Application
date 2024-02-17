package businessLogic;
import model.Server;
import model.Task;
import java.util.List;

public class ConcreteStrategyQueue implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task t){
        int min = Integer.MAX_VALUE;
        Server minServer = null;

        for(Server server:servers){
            if(min>server.getTasks().length){
                min = server.getTasks().length;
                minServer = server;
            }
        }
        minServer.addTask(t);
    }

}


