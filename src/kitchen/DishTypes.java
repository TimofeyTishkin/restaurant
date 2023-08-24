package kitchen;

import com.sun.jdi.event.StepEvent;

import javax.swing.text.StyledEditorKit;

public enum DishTypes {
    FISH,
    STEAK,
    SOUP,
    DRINK,
    PIZZA,
    BURGER,
    BREAKFAST;


    public static DishTypes getDishType(int parseInt) {
        switch (parseInt){
            case 0: return PIZZA;
            case 1: return FISH;
            case 2: return STEAK;
            case 3: return SOUP;
            case 4: return DRINK;
            case 5: return BURGER;
            case 6: return BREAKFAST;
        }
        return null;
    }
}
