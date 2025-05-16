package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Menu extends JFrame {
    int screenW;
    int screenH;
    public Menu(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenW = (int) screenSize.getWidth();
        this.screenH = (int) screenSize.getHeight();
    }
    public void Initialize(){
        // Gets the size dynamically, 70% of user screen
        StartMainFrame();
        MakeGameTitle();
        this.setVisible(true);
    }
    public void SetScreenSize(){
        this.setSize((int) (screenW * 0.4), (int) (screenH * 0.5));
    }
    public void StartMainFrame(){
        this.setTitle("Workonomia: Menu Principal");
        SetScreenSize();
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(206, 84, 84));

    }
    public void MakeGameTitle(){
        JLabel title = new JLabel();
        ImageIcon logo = new ImageIcon("resources/images/workonomia.png");

        int optimalWidth = (int) (this.getWidth() * 0.5);
        int optimalHeight = (int) (this.getHeight() * ((double) optimalWidth / logo.getIconWidth()) * 0.5);
        Image scaledLogo = logo.getImage().getScaledInstance(optimalWidth, optimalHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledLogo);

        System.out.println("FOUND LOGO: Width " + logo.getIconWidth()); // debugging
        title.setIcon(scaledLogoIcon);
        title.setHorizontalTextPosition(SwingConstants.CENTER);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30,0,30,0));
        titlePanel.add(title);
        titlePanel.setOpaque(false);
        this.add(titlePanel, BorderLayout.NORTH);

    }
}
