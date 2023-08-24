package guestroom;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Director implements Runnable{
    private static Director singleton = new Director();
    private ClientGUIView clientGUIView;
    private Connection connection;
    private Director(){

    }
    @Override
    public void run() {
        clientGUIView = new ClientGUIView(this);
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/menu",
                    "root", "TunTun12");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void addMeal(){
        PreparedStatement statement;
        try {
            statement =
                    connection.prepareStatement("INSERT INTO dishes VALUES(?, ?, ?, ?, ?)");
            clientGUIView.getContentPane().removeAll();
            JTextField fieldName = new JTextField();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void removeMeal() {

    }

    public void showStats() {

    }

    public static Director getDirector() {
        return singleton;
    }
}
