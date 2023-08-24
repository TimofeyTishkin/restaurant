package guestroom;

import kitchen.Dish;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Basket extends ArrayList<Dish> {
    Tablet tablet;
    Basket(Tablet tablet){
        this.tablet = tablet;
    }

    @Override
    public boolean add(Dish element) {
        return super.add(element);
    }

    public int getAmountOf(Dish dish) {
        return (int) this.stream().filter(e->e.getName().equals(dish.getName())).count();
    }

    public void showBasket(ClientGUIView clientGUIView) {
        clientGUIView.getContentPane().removeAll();


        JPanel bPanel = new JPanel();
        JLabel basket = new JLabel("Basket");
        basket.setText(this.getText(clientGUIView));
        bPanel.add(basket);
        JScrollPane pane = new JScrollPane(bPanel);


        JPanel rePanel = new JPanel(new GridLayout());
        JButton retBut = new JButton("Return");
        retBut.setFocusable(false);
        retBut.addActionListener(e -> this.tablet.showMenu());
        rePanel.add(retBut, BorderLayout.WEST);

        if(this.size()!=0) {
            JButton endBut = new JButton("Pay for the order");
            endBut.setFocusable(false);
            endBut.addActionListener(e -> this.transaction());
            rePanel.add(endBut, BorderLayout.EAST);
        }
        clientGUIView.add(pane);
        clientGUIView.add(rePanel, BorderLayout.SOUTH);
        clientGUIView.setVisible(true);
    }

    private void transaction() {
        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(tablet.clientGUIView);
        JPanel end = new JPanel();
        JLabel label = new JLabel();
        JButton okButton = new JButton("Ok");

        okButton.addActionListener(e -> {
            frame.dispose();
            clear();
            tablet.clientGUIView.getContentPane().removeAll();
            tablet.clientGUIView.initTablet(tablet);
            tablet.clientGUIView.setVisible(true);
        });

        okButton.setFocusable(false);
        label.setText("<html>" +
                "<p>" +
                "your order will be ready in " +
                getTime()+ " minutes."+
                "<p>" +
                "</html>");
        end.add(label, BorderLayout.NORTH);
        end.add(okButton, BorderLayout.AFTER_LAST_LINE);
        frame.add(end);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

    private String getText(ClientGUIView clientGUIView){
        if(this.size()==0) return "Basket is empty";
        StringBuilder builder = new StringBuilder("<html>");
        for(Dish dish : new HashSet<>(this)){
            int amount = getAmountOf(dish);
            builder.append("\"")
                    .append(dish.getName())
                    .append("\"x")
                    .append(amount)
                    .append(".".repeat((clientGUIView.getSize().width/4-dish.getName().length()-6)))
                    .append(dish.getPrice()*amount)
                    .append("rub<BR>");
        }
        return builder.append("</html>").toString();
    }
    private int getTime(){
        int i = 0;
        for(Dish dish : new HashSet<>(this)){
            switch(dish.getDishClass()){
                case PIZZA:
                case STEAK:
                case SOUP: {
                    i+=15;
                    break;
                }
                case FISH:
                case BURGER:
                case BREAKFAST: {
                    i+=10;
                    break;
                }
                case DRINK: {
                    i+=5;
                    break;
                }
            }
        }
        return i;
    }
}
