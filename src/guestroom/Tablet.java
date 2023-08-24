package guestroom;

import kitchen.Order;
import kitchen.Dish;

import javax.swing.*;
import java.awt.*;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;


public class Tablet {
    protected final ClientGUIView clientGUIView;
    private final int id;
    private final Basket basket;
    public Tablet(int id){
        this.id = id;
        clientGUIView = new ClientGUIView(this);
        basket = new Basket(this);
    }
    public Order createOrder(){
        Order order = new Order(this);
            showMenu();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return order;
    }
    public int getId() {
        return id;
    }
    public void showMenu() { // Отображаем меню ;)
        JPanel panel = new JPanel(new GridLayout(Dish.getMenu().size()/3, 3));

        GridLayout layout = new GridLayout();


        JPanel basketPanel = new JPanel(layout);
        JButton cancelButton = new JButton("Cancel order"); // кнопка отмены заказа
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(e -> {
            basket.clear();
            clientGUIView.getContentPane().removeAll();
            clientGUIView.initTablet(this);
            clientGUIView.setVisible(true);
        }); 
        basketPanel.add(cancelButton);
        JButton basketBut = new JButton("Basket"); // кнопка перехода в корзину
        basketBut.setFocusable(false);
        basketBut.addActionListener(e->basket.showBasket(clientGUIView));
        JButton endOrder = new JButton("Next step"); // следующий шаг (это та же корзина)
        endOrder.setFocusable(false);
        endOrder.addActionListener(e->basket.showBasket(clientGUIView));
        basketPanel.add(basketBut);
        basketPanel.add(endOrder);
        basketPanel.setBackground(Color.lightGray);

        for(kitchen.orderAble a: Dish.getMenu()) {   // выводим меню на экран
            JButton menu = new JButton(a.getName());
            menu.addActionListener(e -> showDish((Dish) a));
            menu.setFocusable(false);
            panel.add(menu);
        }

        ClientGUIView frame = clientGUIView;

        JScrollPane pane = new JScrollPane(panel);  //Создаем ScrollPane чтобы можно было листать меню вверх и вниз

        frame.getContentPane().removeAll(); // очищаем то, что былол до этого, добавляем все, что нужно и обновляем
        frame.add(basketPanel, BorderLayout.PAGE_START);
        frame.add(pane, BorderLayout.CENTER);
        frame.setVisible(true); // так фрейс еще и обновится
    }

    private void showDish(Dish dish){
        JPanel imagePanel = new JPanel(new GridLayout()); // изображение блюда
        JPanel panel = new JPanel(new GridLayout());
        JScrollPane pane = new JScrollPane(panel);  // описание блюда
        pane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        JLabel image = new JLabel();
        JLabel description = new JLabel();
        JFrame frame = clientGUIView;

        addPicture(dish, image);
        imagePanel.add(image, BorderLayout.CENTER);

        addDescription(dish, description);
        panel.add(description, BorderLayout.CENTER);

        GridLayout layout = new GridLayout();

        JPanel basketPanel = new JPanel(layout);
        JButton retBut = new JButton("Return"); // кнопка возврата
        retBut.setFocusable(false);
        retBut.addActionListener(e -> showMenu());
        basketPanel.add(retBut, BorderLayout.WEST);

        if (basket.getAmountOf(dish)!=0) {
            JPanel panelAmount = new JPanel();

            JLabel amount = new JLabel();

            JButton removeBut = new JButton("-");
            removeBut.addActionListener(k-> {
                basket.remove(dish);
                amount.setText(""+basket.getAmountOf(dish));
                if (basket.getAmountOf(dish)==0) showDish(dish);
            });
            removeBut.setFocusable(false);
            panelAmount.add(removeBut, BorderLayout.CENTER);
            panelAmount.setBackground(Color.lightGray);
            amount.setText(""+basket.getAmountOf(dish));
            amount.setHorizontalTextPosition(JLabel.CENTER);
            panelAmount.add(amount);

            JButton addBut = new JButton("+");
            addBut.addActionListener(k-> {
                basket.add(dish);
                amount.setText(""+basket.getAmountOf(dish));
            });
            addBut.setFocusable(false);
            panelAmount.add(addBut, BorderLayout.CENTER);

            JButton endOrder = new JButton("Next step");
            endOrder.setFocusable(false);
            endOrder.addActionListener(k->basket.showBasket(clientGUIView));
            basketPanel.add(panelAmount);
            basketPanel.add(endOrder, BorderLayout.EAST);
        } else {
            JButton basketBut = new JButton("<html>Add to basket<BR>" + dish.getPrice() + " rub</html>");
            basketBut.addActionListener(e -> {
                basket.add(dish);
                basketPanel.removeAll();
                showDish(dish);
                frame.setVisible(true);
            });
            basketBut.setFocusable(false);
            basketPanel.add(basketBut, BorderLayout.EAST);
            basketPanel.setBackground(Color.lightGray);
        }
        frame.getContentPane().removeAll();
        frame.add(imagePanel, BorderLayout.PAGE_START);
        frame.add(basketPanel, BorderLayout.PAGE_END);
        frame.add(pane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void addDescription(Dish dish, JLabel description) {
        description.setText("<html>"+dish.getName()+
                "<BR><BR>Class:<BR>"+ dish.getDishClass()+
                "<BR><BR>Ingredients:<BR>"+ dish.getIngredients()
                        .toString()
                        .replaceAll("\\[", "")
                        .replaceAll("\\]", "")
                        .replaceAll(",", "<BR>")+
                "<BR><BR>Price:<BR>"+dish.getPrice()+"rub"+"</html>");
        description.setBackground(Color.lightGray);
        description.setOpaque(true);
    }

    private void addPicture(Dish dish, JLabel image){
        image.setText("<html><head>\n" +
                "<style>\n" +
                "h2 {text-align: center;}\n" +
                "</style>\n" +
                "</head>" +
                "<body>" +
                "<h2>"+dish.getName()+"</h2>" +
                "<img src=\""+dish.getPicture()+"\" alt=\""+dish.getName()+"\" width=\"200\" height=\"170\"><BR>" +
                "</body>" +
                "</html>");
        image.setVerticalTextPosition(JLabel.TOP);
        image.setHorizontalTextPosition(JLabel.CENTER);
        image.setFont(new Font("MV Boli", Font.PLAIN, 20));
        image.setForeground(new Color(123, 50, 250));
        image.setBackground(Color.GREEN);
        image.setOpaque(true);
        image.setHorizontalAlignment(JLabel.CENTER);
    }
}

