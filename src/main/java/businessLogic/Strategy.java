package businessLogic;
import model.*;
import java.util.*;
import java.lang.*;

import model.Server;

public interface Strategy {
    public void addTask(List<Server> servers, Task t);
}
