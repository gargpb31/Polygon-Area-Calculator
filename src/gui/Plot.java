
package gui;

import ExtraFunctions.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public class Plot extends JPanel implements MouseMotionListener {
    private getAngles polygon = new getAngles();
    private int verticesSize = 16; // Reduced for better precision
    private Rectangle[] vertices = new Rectangle[4];
    private Polygon poly;
    private int currentVertexIndex = -1;
    
    // Store parent frame and scale for navigation
    private JFrame parentFrame;
    private int scale;
    
    // Modern color scheme
    public Color primaryColor = new Color(52, 152, 219); // Blue
    public Color secondaryColor = new Color(41, 128, 185); // Darker blue
    public Color accentColor = new Color(46, 204, 113); // Green
    public Color backgroundColor = new Color(236, 240, 241); // Light gray
    public Color surfaceColor = new Color(255, 255, 255); // White
    public Color textColor = new Color(44, 62, 80); // Dark gray
    public Color borderColor = new Color(189, 195, 199); // Light gray border
    
    private float polygonWidth = 3;
    private JPanel infoPanel;
    private JLabel areaLabel;
    private JLabel anglesLabel;
    private JTextField[] sideLengthFields; // Array for 4 side length fields
    private JButton resetButton;
    private JButton goBackButton;
    private JButton applyButton;
    private JPanel controlPanel;
    
    public Plot(JFrame jFrame, int scale) {
        this.parentFrame = jFrame;
        this.scale = scale;
        
        setSize(1400, 800); // Increased size for better layout
        setBackground(backgroundColor);
        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Initialize polygon
        initializePolygon(scale);
        
        // Create UI components
        createInfoPanel();
        createControlPanel();
        
        // Add components to layout
        add(infoPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.SOUTH);
        add(createSidePanel(), BorderLayout.EAST);
        
        // Add mouse listeners
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                int x = me.getX();
                int y = me.getY();
                currentVertexIndex = getVertexIndex(x, y);
                if (currentVertexIndex >= 0) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent me) {
                currentVertexIndex = -1;
                setCursor(Cursor.getDefaultCursor());
            }
        });
        
        addMouseMotionListener(this);
        
        updateDisplay();
    }
    
    private void initializePolygon(int scale) {
        if (scale % 2 == 0) {
            int xP[] = {(getWidth()/2-scale/2), (getWidth()/2+scale/2), (getWidth()/2+scale/2), (getWidth()/2-scale/2)};
            int yP[] = {(getHeight()/2-scale/2), (getHeight()/2-scale/2), (getHeight()/2+scale/2), (getHeight()/2+scale/2)};
            polygon.setXs(xP);
            polygon.setYs(yP);
        } else {
            int xP[] = {(getWidth()/2-scale/2), (getWidth()/2+scale/2+1), (getWidth()/2+scale/2+1), (getWidth()/2-scale/2)};
            int yP[] = {(getHeight()/2-scale/2-1), (getHeight()/2-scale/2-1), (getHeight()/2+scale/2), (getHeight()/2+scale/2)};
            polygon.setXs(xP);
            polygon.setYs(yP);
        }
        polygon.changePoint();
        poly = new Polygon(polygon.getXs(), polygon.getYs(), 4);
        
        // Initialize vertices
        for (int i = 0; i < 4; i++) {
            Rectangle r = new Rectangle();
            r.setBounds((int) (polygon.getX(i) - verticesSize * 0.5), 
                       (int) (polygon.getY(i) - verticesSize * 0.5),
                       verticesSize, verticesSize);
            vertices[i] = r;
        }
    }
    
    private void createInfoPanel() {
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(surfaceColor);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(borderColor, 1, true),
            new EmptyBorder(15, 20, 15, 20)
        ));
        
        // Title
        JLabel titleLabel = new JLabel("Polygon Area Calculator");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(primaryColor);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Area label
        areaLabel = new JLabel();
        areaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        areaLabel.setForeground(textColor);
        areaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Angles label
        anglesLabel = new JLabel();
        anglesLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        anglesLabel.setForeground(textColor);
        anglesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        infoPanel.add(titleLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(areaLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(anglesLabel);
    }
    
    private void createControlPanel() {
        controlPanel = new JPanel(new GridBagLayout());
        controlPanel.setBackground(surfaceColor);
        controlPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(borderColor, 1, true),
            new EmptyBorder(15, 20, 15, 20)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Create side length fields with labels
        sideLengthFields = new JTextField[4];
        String[] sideLabels = {"AB:", "BC:", "CD:", "DA:"};
        
        for (int i = 0; i < 4; i++) {
            // Label
            JLabel label = new JLabel(sideLabels[i]);
            label.setFont(new Font("Segoe UI", Font.BOLD, 14));
            label.setForeground(textColor);
            gbc.gridx = i * 2;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.CENTER;
            controlPanel.add(label, gbc);
            
            // Text field
            sideLengthFields[i] = new JTextField("Enter length", 8);
            styleTextField(sideLengthFields[i]);
            sideLengthFields[i].setToolTipText("Enter length for side " + sideLabels[i]);
            
            // Add Enter key listener
            final int index = i;
            sideLengthFields[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        applySideLengths();
                    }
                }
            });
            
            gbc.gridx = i * 2 + 1;
            gbc.gridy = 0;
            controlPanel.add(sideLengthFields[i], gbc);
        }
        
        // Buttons row
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        
        // Reset button
        resetButton = createStyledButton("Reset", accentColor);
        resetButton.setToolTipText("Reset polygon to original square shape");
        resetButton.addActionListener(e -> resetPolygon());
        controlPanel.add(resetButton, gbc);
        
        gbc.gridx = 2;
        gbc.gridwidth = 2;
        
        // Apply button
        applyButton = createStyledButton("Apply", primaryColor);
        applyButton.setToolTipText("Apply new side lengths to the polygon");
        applyButton.addActionListener(e -> applySideLengths());
        controlPanel.add(applyButton, gbc);
        
        gbc.gridx = 4;
        gbc.gridwidth = 2;
        
        // Go back button
        goBackButton = createStyledButton("Go Back", secondaryColor);
        goBackButton.setToolTipText("Return to the main menu");
        goBackButton.addActionListener(e -> {
            // Handle go back functionality
            parentFrame.getContentPane().removeAll();
            Start startPanel = new Start(parentFrame, 4, scale);
            parentFrame.setSize(startPanel.getSize());
            parentFrame.getContentPane().add(startPanel);
            parentFrame.revalidate();
            parentFrame.repaint();
        });
        controlPanel.add(goBackButton, gbc);
    }
    
    private JPanel createSidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(250, 0));
        sidePanel.setBackground(surfaceColor);
        sidePanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(borderColor, 1, true),
            new EmptyBorder(15, 15, 15, 15)
        ));
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        
        // Instructions
        JLabel instructionsLabel = new JLabel("<html><b>Instructions:</b><br>" +
            "• Drag vertices to reshape the polygon<br>" +
            "• Area and angles update automatically<br>" +
            "• Use Reset to return to square<br>" +
            "• Enter side length for specific measurements</html>");
        instructionsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        instructionsLabel.setForeground(textColor);
        instructionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        sidePanel.add(instructionsLabel);
        sidePanel.add(Box.createVerticalStrut(20));
        
        return sidePanel;
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
                
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.setColor(Color.WHITE);
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int textX = (getWidth() - fm.stringWidth(getText())) / 2;
                int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(getText(), textX, textY);
                g2.dispose();
            }
        };
        
        button.setPreferredSize(new Dimension(120, 40));
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return button;
    }
    
    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(borderColor, 1, true),
            new EmptyBorder(8, 12, 8, 12)
        ));
        textField.setPreferredSize(new Dimension(200, 35));
        
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals("Enter length")) {
                    textField.setText("");
                    textField.setForeground(textColor);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText("Enter length");
                    textField.setForeground(Color.GRAY);
                }
            }
        });
    }
    
    private void resetPolygon() {
        // Reset to original square
        int scale = 250;
        initializePolygon(scale);
        updateDisplay();
        repaint();
    }
    
    private void updateDisplay() {
        areaLabel.setText(String.format("Area: %.2f square units", polygon.getArea()));
        anglesLabel.setText(String.format("Angles: A=%.1f° B=%.1f° C=%.1f° D=%.1f°", 
            polygon.getAngle(0), polygon.getAngle(1), polygon.getAngle(2), polygon.getAngle(3)));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        // Draw polygon with gradient fill
        GradientPaint gradient = new GradientPaint(
            0, 0, new Color(primaryColor.getRed(), primaryColor.getGreen(), primaryColor.getBlue(), 30),
            getWidth(), getHeight(), new Color(primaryColor.getRed(), primaryColor.getGreen(), primaryColor.getBlue(), 80)
        );
        g2.setPaint(gradient);
        g2.fill(poly);
        
        // Draw polygon border
        g2.setColor(primaryColor);
        g2.setStroke(new BasicStroke(polygonWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.draw(poly);
        
        // Draw vertices with shadow effect
        for (int i = 0; i < 4; i++) {
            // Draw shadow
            g2.setColor(new Color(0, 0, 0, 50));
            g2.fillOval(vertices[i].x + 2, vertices[i].y + 2, vertices[i].width, vertices[i].height);
            
            // Draw vertex
            g2.setColor(accentColor);
            g2.fillOval(vertices[i].x, vertices[i].y, vertices[i].width, vertices[i].height);
            
            // Draw vertex border
            g2.setColor(accentColor.darker());
            g2.setStroke(new BasicStroke(1));
            g2.drawOval(vertices[i].x, vertices[i].y, vertices[i].width, vertices[i].height);
            
            // Draw vertex label
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
            String label = String.valueOf((char)('A' + i));
            FontMetrics fm = g2.getFontMetrics();
            int textX = vertices[i].x + (vertices[i].width - fm.stringWidth(label)) / 2;
            int textY = vertices[i].y + (vertices[i].height + fm.getAscent()) / 2;
            g2.drawString(label, textX, textY);
        }
        
        // Draw side lengths with background
        g2.setColor(textColor);
        g2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        for (int i = 0; i < 4; i++) {
            String length = String.format("%.1f", polygon.GetLength(i));
            int x = (int) polygon.getXMid(i);
            int y = (int) polygon.getYMid(i);
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(length);
            int textHeight = fm.getHeight();
            
            // Draw background for text
            g2.setColor(new Color(255, 255, 255, 200));
            g2.fillRoundRect(x - textWidth/2 - 3, y - textHeight/2 - 2, textWidth + 6, textHeight + 4, 5, 5);
            
            // Draw text
            g2.setColor(textColor);
            g2.drawString(length, x - textWidth/2, y + textHeight/2 - 2);
        }
        
        g2.dispose();
    }
    
    private int getVertexIndex(int x, int y) {
        for (int i = 0; i < 4; i++) {
            if (vertices[i].contains(x, y)) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public void mouseMoved(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        
        if (getVertexIndex(x, y) >= 0) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            setCursor(Cursor.getDefaultCursor());
        }
    }
    
    @Override
    public void mouseDragged(MouseEvent me) {
        int x = me.getX();
        int y = me.getY();
        
        if (getBounds().contains(x, y) && currentVertexIndex >= 0) {
            polygon.changePoint(x, y, currentVertexIndex);
            vertices[currentVertexIndex].x = (int) (polygon.getX(currentVertexIndex) - verticesSize * 0.5);
            vertices[currentVertexIndex].y = (int) (polygon.getY(currentVertexIndex) - verticesSize * 0.5);
            poly = new Polygon(polygon.getXs(), polygon.getYs(), 4);
            updateDisplay();
            repaint();
        }
    }

    private void applySideLengths() {
        try {
            double[] newSideLengths = new double[4];
            for (int i = 0; i < 4; i++) {
                String text = sideLengthFields[i].getText().trim();
                if (text.equals("Enter length") || text.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter values for all side lengths.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                newSideLengths[i] = Double.parseDouble(text);
                if (newSideLengths[i] <= 0) {
                    JOptionPane.showMessageDialog(this, "Side lengths must be positive numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            // Check if the side lengths can form a valid quadrilateral
            if (!isValidQuadrilateral(newSideLengths)) {
                JOptionPane.showMessageDialog(this, "The given side lengths cannot form a valid quadrilateral.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            polygon.setPolygonFromSideLengths(newSideLengths);
            poly = new Polygon(polygon.getXs(), polygon.getYs(), 4);
            
            // Update vertices
            for (int i = 0; i < 4; i++) {
                Rectangle r = new Rectangle();
                r.setBounds((int) (polygon.getX(i) - verticesSize * 0.5), 
                           (int) (polygon.getY(i) - verticesSize * 0.5),
                           verticesSize, verticesSize);
                vertices[i] = r;
            }
            
            updateDisplay();
            repaint();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for side lengths.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean isValidQuadrilateral(double[] sides) {
        // Check if any side is greater than the sum of the other three sides
        for (int i = 0; i < 4; i++) {
            double sum = 0;
            for (int j = 0; j < 4; j++) {
                if (j != i) {
                    sum += sides[j];
                }
            }
            if (sides[i] >= sum) {
                return false;
            }
        }
        return true;
    }
}
