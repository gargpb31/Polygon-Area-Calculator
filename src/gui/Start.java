package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Start extends JPanel {
    
    private JButton nextButton;
    private Timer timer;
    private JFrame parentFrame;
    private int scale;
    
    // Modern color scheme
    private Color primaryColor = new Color(52, 152, 219); // Blue
    private Color secondaryColor = new Color(41, 128, 185); // Darker blue
    private Color accentColor = new Color(46, 204, 113); // Green
    private Color backgroundColor = new Color(236, 240, 241); // Light gray
    private Color surfaceColor = new Color(255, 255, 255); // White
    private Color textColor = new Color(44, 62, 80); // Dark gray
    
    public Start(JFrame jFrame, int delay, int s) {
        this.parentFrame = jFrame;
        this.scale = s;
        
        setSize(1400, 800);
        setBackground(backgroundColor);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(40, 40, 40, 40));
        
        // Create main content panel
        JPanel contentPanel = createContentPanel();
        add(contentPanel, BorderLayout.CENTER);
        
        // Setup timer for auto-transition
        setupTimer(delay);
        
        // Setup manual transition button
        setupNextButton();
    }
    
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(surfaceColor);
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 2, true),
            new EmptyBorder(60, 80, 60, 80)
        ));
        
        // Title section
        JPanel titlePanel = createTitlePanel();
        contentPanel.add(titlePanel);
        
        contentPanel.add(Box.createVerticalStrut(40));
        
        // Team members section
        JPanel teamPanel = createTeamPanel();
        contentPanel.add(teamPanel);
        
        contentPanel.add(Box.createVerticalStrut(40));
        
        // Next button
        nextButton = createStyledButton("Get Started", accentColor);
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(nextButton);
        
        return contentPanel;
    }
    
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(surfaceColor);
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Main title
        JLabel mainTitle = new JLabel("Polygon Area Calculator");
        mainTitle.setFont(new Font("Segoe UI", Font.BOLD, 48));
        mainTitle.setForeground(primaryColor);
        mainTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Subtitle
        JLabel subtitle = new JLabel("Interactive Geometric Analysis Tool");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        subtitle.setForeground(textColor);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Project info
        JLabel projectInfo = new JLabel("Object-Oriented Modeling (C3) Project");
        projectInfo.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        projectInfo.setForeground(secondaryColor);
        projectInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        titlePanel.add(mainTitle);
        titlePanel.add(Box.createVerticalStrut(10));
        titlePanel.add(subtitle);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(projectInfo);
        
        return titlePanel;
    }
    
    private JPanel createTeamPanel() {
        JPanel teamPanel = new JPanel();
        teamPanel.setLayout(new BoxLayout(teamPanel, BoxLayout.Y_AXIS));
        teamPanel.setBackground(surfaceColor);
        teamPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // College title
        JLabel collegeTitle = new JLabel("College");
        collegeTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        collegeTitle.setForeground(primaryColor);
        collegeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        teamPanel.add(collegeTitle);
        teamPanel.add(Box.createVerticalStrut(20));
        
        // College name
        JLabel collegeName = new JLabel("INDIAN INSTITUTE OF INFORMATION TECHNOLOGY, ALLAHABAD");
        collegeName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        collegeName.setForeground(textColor);
        collegeName.setAlignmentX(Component.CENTER_ALIGNMENT);
        teamPanel.add(collegeName);
        teamPanel.add(Box.createVerticalStrut(15));
        
        // Student section
        JLabel studentTitle = new JLabel("Student");
        studentTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        studentTitle.setForeground(primaryColor);
        studentTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        teamPanel.add(studentTitle);
        teamPanel.add(Box.createVerticalStrut(10));
        
        // Student name
        JLabel studentName = new JLabel("Sunny Garg");
        studentName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        studentName.setForeground(textColor);
        studentName.setAlignmentX(Component.CENTER_ALIGNMENT);
        teamPanel.add(studentName);
        teamPanel.add(Box.createVerticalStrut(8));
        
        // Email addresses
        JLabel email1 = new JLabel("sunnygarg887@gmail.com");
        email1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        email1.setForeground(secondaryColor);
        email1.setAlignmentX(Component.CENTER_ALIGNMENT);
        teamPanel.add(email1);
        teamPanel.add(Box.createVerticalStrut(2));
        
        JLabel email2 = new JLabel("dev.sunny995@gmail.com");
        email2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        email2.setForeground(secondaryColor);
        email2.setAlignmentX(Component.CENTER_ALIGNMENT);
        teamPanel.add(email2);
        teamPanel.add(Box.createVerticalStrut(8));
        
        // Portfolio link
        JLabel portfolioLabel = new JLabel("Portfolio:");
        portfolioLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        portfolioLabel.setForeground(textColor);
        portfolioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        teamPanel.add(portfolioLabel);
        teamPanel.add(Box.createVerticalStrut(2));
        
        JLabel portfolioLink = new JLabel("portfolio-sunny-gargs-projects.vercel.app");
        portfolioLink.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        portfolioLink.setForeground(accentColor);
        portfolioLink.setAlignmentX(Component.CENTER_ALIGNMENT);
        teamPanel.add(portfolioLink);
        teamPanel.add(Box.createVerticalStrut(8));
        
        return teamPanel;
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                if (getModel().isPressed()) {
                    g2.setColor(color.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(color.brighter());
                } else {
                    g2.setColor(color);
                }
                
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.setColor(Color.WHITE);
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int textX = (getWidth() - fm.stringWidth(getText())) / 2;
                int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(getText(), textX, textY);
                g2.dispose();
            }
        };
        
        button.setPreferredSize(new Dimension(200, 50));
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    private void setupTimer(int delay) {
        int delayTime = delay * 100000000;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                transitionToMain();
            }
        };
        timer = new Timer(delayTime, taskPerformer);
        timer.start();
        timer.setRepeats(false);
    }
    
    private void setupNextButton() {
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (timer != null) {
                    timer.stop();
                }
                transitionToMain();
            }
        });
    }
    
    private void transitionToMain() {
        parentFrame.getContentPane().removeAll();
        Plot drawPanel = new Plot(parentFrame, scale);
        parentFrame.setSize(drawPanel.getSize());
        parentFrame.getContentPane().add(drawPanel);
        parentFrame.revalidate();
        parentFrame.repaint();
    }
    
    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        JFrame jFrame = new JFrame();
        jFrame.setTitle("Polygon Area Calculator");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Start info = new Start(jFrame, 4, 250);
        jFrame.setSize(info.getSize());
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        
        Container cPane = jFrame.getContentPane();
        cPane.add(info);
        jFrame.setVisible(true);
    }
}