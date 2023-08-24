package kitchen;

import java.util.ArrayList;
import guestroom.Tablet;

public class Order {
    private final Tablet tablet;
    protected ArrayList<Dish> ListOfDishes = new ArrayList<>();
    public Order(Tablet tablet){
        this.tablet = tablet;
    }
    public void addToOrder(Dish dish){
        ListOfDishes.add(dish);
    }
    public Tablet getTablet() {
        return tablet;
    }
}
