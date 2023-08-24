package guestroom;

import kitchen.Meal;

public class Waiter implements Runnable{
    private boolean noMeals(){
        return Manager.getManager().getMealQueue().isEmpty();
    }
    @Override
    public void run() {
        Manager manager = Manager.getManager();
        while(true){
            synchronized (this) {
                if (!noMeals()) {
                    Meal meal = manager.getMealQueue().poll();
                    assert meal != null;
                    System.out.printf("Waiter takes the order to table №%d\n", meal.getTablet());
                } /*else {
                    Tablet tablet = manager.nextTablet();

                    Order order = tablet.createOrder();
                    System.out.printf("Waiter took order from table №%f\n", order.getTablet().getId() + 0.0);
                    manager.getOrderQueue().add(order);
                }*/
            }
            try{Thread.sleep(1000);}catch (InterruptedException e){e.printStackTrace();}
        }
    }
}
