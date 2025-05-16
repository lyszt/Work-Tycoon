package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

public class Menu extends JFrame {
    int screenW;
    int screenH;

    public Menu(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenW = (int) screenSize.getWidth();
        this.screenH = (int) screenSize.getHeight();
    }

    public void Initialize(){
        StartMainFrame();

        JPanel container = new JPanel(new BorderLayout());
        container.setOpaque(false);
        container.add(MakeGameTitle(), BorderLayout.NORTH);
        container.add(AddMenuButtons(), BorderLayout.CENTER);

        this.add(container, BorderLayout.CENTER);
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
        this.getContentPane().setBackground(new Color(70, 70, 70));
    }

    public JPanel MakeGameTitle(){
        JLabel title = new JLabel();
        ImageIcon logo = new ImageIcon("resources/images/workonomia.png");

        int optimalWidth = (int) (this.getWidth() * 0.3);
        int optimalHeight = (int) (this.getHeight() * ((double) optimalWidth / logo.getIconWidth()) * 0.5);
        Image scaledLogo = logo.getImage().getScaledInstance(optimalWidth, optimalHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledLogo);

        System.out.println("FOUND LOGO: Width " + logo.getIconWidth());
        title.setIcon(scaledLogoIcon);
        title.setHorizontalTextPosition(SwingConstants.CENTER);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,0));
        titlePanel.add(title);
        titlePanel.setOpaque(false);
        return titlePanel;
    }

    public JPanel AddMenuButtons(){
        JButton start = new JButton("Iniciar");
        JButton options = new JButton("Opções");
        JButton quit = new JButton("Sair do jogo");
        quit.setHorizontalTextPosition(SwingConstants.CENTER);
        quit.addActionListener(ActionListener -> System.exit(0));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 40));
        wrapper.setOpaque(true);
        wrapper.setBackground(new Color(0,0,0));
        wrapper.setAlignmentY(Component.TOP_ALIGNMENT);
        wrapper.setPreferredSize(new Dimension((int) (getWidth() * 0.25), (int) (getHeight() * 0.5)));

        JButton[] buttons = {start, options, quit};
        Dimension maxSize = Arrays.stream(buttons)
                .map(AbstractButton::getPreferredSize)
                .max(Comparator.comparingInt(d -> d.width))
                .orElse(new Dimension(0, 0));

        Arrays.stream(buttons).forEach(b -> {
            b.setMaximumSize(new Dimension(maxSize.width, maxSize.height));
            b.setAlignmentX(Component.RIGHT_ALIGNMENT);
            wrapper.add(b);
            wrapper.add(Box.createVerticalStrut(30));
        });
        JPanel eastPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        eastPanel.setOpaque(false);
        eastPanel.add(wrapper);

        panel.add(eastPanel, BorderLayout.EAST);
        return panel;
    }
}
