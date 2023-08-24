package guestroom;

import kitchen.Meal;
import kitchen.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Manager {
    private static final Manager manager = new Manager();
    private final List<Tablet> tablets = new ArrayList<>();
    private final Queue<Order> orderQueue = new ConcurrentLinkedQueue<>();
    private final Queue<Meal> mealQueue = new ConcurrentLinkedQueue<>();
    private int currentTablet = 0;
    private Manager(){}
    public void addTablet(int i){
        this.tablets.add(new Tablet(i));
    }
    public static synchronized Manager getManager(){
        return manager;
    }
    public synchronized Tablet nextTablet(){
        Tablet tablet = tablets.get(currentTablet);
        currentTablet = (currentTablet +1)%10;
        return tablet;
    }

    public Queue<Order> getOrderQueue() {
        return orderQueue;
    }

    public Queue<Meal> getMealQueue() {
        return mealQueue;
    }
}
