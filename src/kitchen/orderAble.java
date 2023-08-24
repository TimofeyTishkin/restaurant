package kitchen;

import java.util.List;

public interface orderAble {
    public int getId();
    public String getName();
    public List<String> getIngredients();
    public int getPrice();
    public DishTypes getDishClass();
}
