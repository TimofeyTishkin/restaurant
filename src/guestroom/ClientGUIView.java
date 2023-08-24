package guestroom;

import javax.swing.*;
import java.awt.*;

public class ClientGUIView extends JFrame {
    ClientGUIView(Tablet tablet){
        super("Table №"+tablet.getId()); //создается фрейм с заголовком "Table №i", где i - номер стола
        this.setSize( 600, 600); //устанавливается размер фрейма
        this.setResizable(false);            //теперь размер окна нельзя поменять внутри программы
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //закрытие через кнопку крестика прячет фрейм, но не останавливает программу
        /*this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        */this.initTablet(tablet);
    }
    void initTablet(Tablet tablet){
        JButton makeOrder = new JButton("Make an order"); // создается кнопка "заказать"
        makeOrder.setFocusable(false); // кнопка не фокусируемая
        makeOrder.addActionListener(e ->tablet.showMenu()); // добавляем действие для нажатия на кнопку
        JPanel panel = new JPanel(); // размещаем панель
        panel.add(makeOrder,BorderLayout.CENTER); // добавляем на нее кнопку
        panel.setBackground(Color.lightGray); // цвет фона
        this.add(panel); // добавляем панель с кнопкой в наш фрейм
        this.setVisible(true); // делаем фрейм видимым (изначально значение Visible: false)
    }

    ClientGUIView(Director director){
        super("Director");
        this.setSize(600, 600);
        this.setResizable(false);
        this.initDirector(director);
    }
    void initDirector(Director director){
        JButton addMeal = new JButton("Add meal");
        addMeal.setFocusable(false);
        addMeal.addActionListener(e ->director.addMeal());

        JButton removeMeal = new JButton("Remove meal");
        removeMeal.setFocusable(false);
        removeMeal.addActionListener(e ->director.removeMeal());

        JButton statistic = new JButton("Statistics");
        statistic.setFocusable(false);
        statistic.addActionListener(e ->director.showStats());

        JPanel panel = new JPanel();
        panel.add(addMeal);
        panel.add(removeMeal);
        panel.add(statistic);
        panel.setBackground(Color.lightGray);
        this.add(panel);
        this.setVisible(true);
    }
}
