import guestroom.Director;
import guestroom.Manager;
import guestroom.Waiter;
import kitchen.Cook;
import kitchen.Dish;
import javax.swing.*;
import java.awt.*;
import java.sql.DriverManager;

public class Restaurant {

    public static void main(String[] args) {
        userInitialization(new JFrame("Initialisation"));
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            System.out.println("Connection is successful!");
            Dish.initialize(DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/restaurant", "root", "TunTun12"));
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            ex.printStackTrace();
        }
    }

    private static void userInitialization(JFrame frame) {
        frame.setSize(400, 250);
        JPanel textPanel = new JPanel();
        JTextField username = new JTextField();
        username.setPreferredSize(new Dimension(150, 30));
        username.addActionListener(e->{
            String command = e.getActionCommand();
            parse(command, frame);
        });
        textPanel.add(username);
        frame.add(textPanel);
        frame.setVisible(true);
    }

    private static void parse(String command, JFrame frame) {
        frame.getContentPane().removeAll();
        userInitialization(frame);
        if (command.split(" ").length == 2)
            if (command.split(" ")[0].toLowerCase().equals("table")) {
                startTablet(Integer.parseInt(command.split(" ")[1]));
                frame.setVisible(false);
            } else dontMatch(frame);
        else if(command.toLowerCase().equals("director")) startDirector();
        else if(command.toLowerCase().equals("waiter")) startWaiter();
        else if(command.toLowerCase().equals("cook")) startCook();
        else dontMatch(frame);
    }

    private static void startTablet(int i){
        Manager.getManager().addTablet(i);
        Waiter waiterThread = new Waiter();
        Thread waiter = new Thread(waiterThread);
        Cook cookThread = new Cook();
        Thread cook = new Thread(cookThread);
        waiter.start();
        cook.start();
    }

    private static void startCook() {

    }

    private static void startWaiter() {

    }

    private static void startDirector() {
        Director.getDirector().run();
    }

    private static void dontMatch(JFrame frame) {
        JPanel massagePanel = new JPanel();
        JLabel hint = new JLabel("<html>" +
                "<h3 style=\"text-align:center\">HINT</h3>" +
                "<p style=\"text-align:justify\">Write your job title " +
                "<br>(director, waiter or cook)</br>" +
                "<br>or the number of this</br>" +
                "<br>tablet representing table</br>" +
                "<br>number (table N)</br></p>" +
                "</html>");
        hint.setForeground(new Color(60, 60, 120));
        massagePanel.add(hint);
        frame.add(massagePanel, BorderLayout.AFTER_LAST_LINE);
        frame.setVisible(true);
    }
}
