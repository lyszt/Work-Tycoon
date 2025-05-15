import javax.swing.*;
import java.awt.*;

public class GUI {
    public static void Initialize(){
        // Gets the size dynamically, 70% of user screen
        JFrame mainFrame = start_mainFrame();

    }
    private static void setScreenSize(JFrame frame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screen_w = (int) screenSize.getWidth();
        int screen_h = (int) screenSize.getHeight();
        frame.setSize((int) (screen_w * 0.5), (int) (screen_h * 0.5));
    }
    private static  JFrame start_mainFrame(){
        JFrame mainFrame = new JFrame("Workonomia");
        setScreenSize(mainFrame);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        return mainFrame;
    }
}
