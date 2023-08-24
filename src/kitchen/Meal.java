package kitchen;

import guestroom.Tablet;

public class Meal extends Dish {
    private final int tablet;
    protected Meal(Dish dish, Tablet tablet) {
        super(dish.getId(), dish.getName(), dish.getIngredients(),
                dish.getPrice(), dish.getDishClass(), dish.getPicture());
        this.tablet = tablet.getId();
    }

    public int getTablet() {
        return tablet;
    }
}
