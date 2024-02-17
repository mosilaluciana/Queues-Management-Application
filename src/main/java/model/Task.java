package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Task implements Comparable<Task>{
   private int ID;
   private int arrivalTime;
   private AtomicInteger serviceTime;

    public Task(int ID, int arrivalTime, int serviceTime){
        this.ID=ID;
        this.arrivalTime=arrivalTime;
        this.serviceTime= new AtomicInteger(serviceTime);
    }

    public int getID(){return this.ID;}
    public int getArrivalTime(){return  this.arrivalTime;}
    public long getServiceTime(){return this.serviceTime.longValue();}
    public void setID(int ID){this.ID=ID;}
    public void setArrivalTime(int arrivalTime){this.arrivalTime = arrivalTime;}
    public void setServiceTime(int serviceTime){this.serviceTime = new AtomicInteger(serviceTime);}

    public String toString() {
        return "(" +ID+ "," +arrivalTime+ "," +serviceTime.intValue()+ ") ";
        }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.arrivalTime, other.arrivalTime);
    }
}


