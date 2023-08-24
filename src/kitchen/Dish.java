package kitchen;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Dish implements orderAble{
    private static final List<orderAble> menu = new ArrayList<>();

    public static void initialize(Connection connection){
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM menu");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int dishId = resultSet.getInt("id");
                addMeal(new Dish(
                        dishId,
                        resultSet.getString("title"),
                        Arrays.asList(resultSet.getString("summary").split("\\n")),
                        Integer.parseInt(resultSet.getString("slug")),
                        DishTypes.getDishType(resultSet.getInt("type")),
                        resultSet.getString("content")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void addMeal(Dish newDish){
        menu.add(newDish);
    }
    public static int getNewId(List<orderAble> menu){
        Random random = new Random();
        int id = 100+ random.nextInt(200);
        boolean haveAny = false;
        while(menu.size()>0){
            for(orderAble m:menu) if(m.getId()==id)haveAny = true;
            if(!haveAny) id = 100+random.nextInt(200);
            else break;
        }
        return id;
    }

    private final int id;
    private final String name;
    private final List<String> ingredients;
    private final int price;
    private final DishTypes dishClass;
    private final String url;
    public Dish(int id, String name, List<String> ingredients, int price, DishTypes dishClass, String url){
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.dishClass = dishClass;
        this.url = url;
    }

    public static List<orderAble> getMenu() {
        return List.copyOf(menu);
    }

    @Override
    public int getId() {
        return id;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public List<String> getIngredients() {
        return List.copyOf(ingredients);
    }
    @Override
    public int getPrice() {
        return price;
    }
    @Override
    public DishTypes getDishClass(){
        return dishClass;
    }
    public String getPicture(){
        return url;
    }

}
