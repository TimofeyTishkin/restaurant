package kitchen;

import guestroom.Manager;

public class Cook implements Runnable {
    public boolean goAhead = true;
    private boolean noOrders(){
        return Manager.getManager().getOrderQueue().isEmpty();
    }
    @Override
    public void run() {
        boolean wait;
        while(goAhead || !noOrders()){
            try{
                synchronized (this) {
                    wait = noOrders();
                    if(!wait) toCook();
                }
                if(wait){
                    Thread.sleep(100);
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    private void  toCook() throws InterruptedException{
        Manager manager = Manager.getManager();
        Order order = manager.getOrderQueue().poll();
        System.out.printf("Заказ готовится для стола №%d\n", order.getTablet().getId());
        Thread.sleep(100);
        assert order.ListOfDishes.size()!=0;
        Meal meal = new Meal(order.ListOfDishes.get(order.ListOfDishes.size()-1), order.getTablet());
        System.out.printf("Заказ для стола №%d готов\n", order.getTablet().getId());
        manager.getMealQueue().add(meal);
    }
}

