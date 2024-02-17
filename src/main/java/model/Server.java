package model;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.LinkedBlockingQueue;

public class Server implements Runnable{

    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;

    public Server(){
        this.tasks = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);
    }

    public void addTask(Task newTask){
        tasks.add(newTask);
        waitingPeriod.incrementAndGet();
    }

    @Override
    public void run() {
        while (true) {
            Task t = null;
            try {
                t = tasks.element();
            } catch (NoSuchElementException exception) {

            }
            if(t!=null) {
                int n = (int) t.getServiceTime();
                for (int i = 0; i < n; i++) {
                    ;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waitingPeriod.decrementAndGet();
                    t.setServiceTime((int) t.getServiceTime() - 1);
                }
                tasks.remove(t);
            }
}}



    public Task[] getTasks(){
         int numTasks = tasks.size();
        Task[] taskArray = new Task[numTasks];
        tasks.toArray(taskArray);
        return taskArray;
    }
    public int getServiceTime(){
        int sum = 0;
        for(Task t:tasks){
            sum = sum+ (int)t.getServiceTime();
        }
        return sum;

    }
}


