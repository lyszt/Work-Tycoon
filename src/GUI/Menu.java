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
        container.add(MakeGameTitle(), BorderLayout.WEST);
        container.add(AddMenuButtons(), BorderLayout.CENTER);
        // Empty panel on top
        JPanel topPannel = new JPanel();
        topPannel.setOpaque(false);
        topPannel.setPreferredSize(new Dimension((int) (getWidth()), (int) (getHeight() * 0.2)));
        this.add(topPannel, BorderLayout.NORTH);
        this.add(container, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void SetScreenSize(){
        this.setSize(1024,600);
    }

    public void StartMainFrame(){
        this.setTitle("Workonomia: Menu Principal");
        SetScreenSize();
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(100, 255, 190));


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
        ImageIcon start_image = new ImageIcon("resources/buttons/start.png");
        ImageIcon options_image = new ImageIcon("resources/buttons/options.png");
        ImageIcon leave_image = new ImageIcon("resources/buttons/leave.png");

        ImageIcon start_pressed = new ImageIcon("resources/buttons/start_pressed.png");
        ImageIcon options_pressed = new ImageIcon("resources/buttons/options_pressed.png");
        ImageIcon leave_pressed = new ImageIcon("resources/buttons/leave_pressed.png");

        JButton start = new JButton(start_image);
        JButton options = new JButton(options_image);
        JButton quit = new JButton(leave_image);
        JButton[] buttons = {start, options, quit};

        Arrays.stream(buttons).toList().forEach(b -> b.setBounds(50, 50, start_image.getIconWidth(), start_image.getIconHeight()));
        Arrays.stream(buttons).toList().forEach(b -> b.setBorderPainted(false));
        Arrays.stream(buttons).toList().forEach(b -> b.setFocusPainted(false));
        Arrays.stream(buttons).toList().forEach(b -> b.setContentAreaFilled(false));


        quit.setHorizontalTextPosition(SwingConstants.CENTER);
        start.addActionListener(e -> {
            start.setIcon(start_pressed);

            Timer timer = new Timer(100, evt -> {
                start.setIcon(start_image);
            });
            timer.setRepeats(false);
            timer.start();
        });

        options.addActionListener(e -> {
            options.setIcon(options_pressed);

            Timer timer = new Timer(100, evt -> {
                options.setIcon(options_image);
            });
            timer.setRepeats(false);
            timer.start();
        });

        quit.addActionListener(e -> {
            System.exit(0);

        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JPanel wrapper = new JPanel() {
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                Color translucentBlack = new Color(0,0,0,100);
                g2d.setColor(translucentBlack);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 50));
        wrapper.setOpaque(true);
        wrapper.setAlignmentY(Component.TOP_ALIGNMENT);
        wrapper.setPreferredSize(new Dimension((int) (getWidth() * 0.2), (int) (getHeight() * 0.6)));

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
