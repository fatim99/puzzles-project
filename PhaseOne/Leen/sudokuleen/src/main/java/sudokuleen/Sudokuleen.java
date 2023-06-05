/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sudokuleen;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.*;

public class Sudokuleen extends JFrame implements ActionListener {
    private JButton button1, button2, button3;
    private JLabel label1, label2;
    private ImageIcon image1, image2;
    
    public Sudokuleen() throws MalformedURLException {
        // Set up the GUI
        super("Image Display");
        setSize(1000, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        // Create the buttons
        button1 = new JButton(" unsolved ");
        button1.addActionListener(this);
        button1.setPreferredSize(new Dimension(200, 50));
        button1.setBounds(100, 350, 200, 50);
        button1.setFont(new Font("Arial", Font.PLAIN, 18));
        
        button2 = new JButton(" solved ");
        button2.addActionListener(this);
        button2.setPreferredSize(new Dimension(200, 50));
        button2.setBounds(350, 350, 200, 50);
        button2.setFont(new Font("Arial", Font.PLAIN, 18));
        
        button3 = new JButton("Show Both Images");
        button3.addActionListener(this);
        button3.setPreferredSize(new Dimension(200, 50));
        button3.setBounds(600, 350, 200, 50);
        button3.setFont(new Font("Arial", Font.PLAIN, 18));
        
        // Create the image icons
        image1 = new ImageIcon(new URL("http://localhost:8000/image1"));
        image1 = new ImageIcon(new URL("http://localhost:8000/image2"));
        // Create the labels
        label1 = new JLabel();
        label1.setBounds(35, 50, 290, 288);
        
        label2 = new JLabel();
        label2.setBounds(330, 50, 290, 288);
        
        // Add the buttons and labels to the GUI
        add(button1);
        add(button2);
        add(button3);
        add(label1);
        add(label2);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == button1) {
    try {
        Desktop.getDesktop().browse(new URI("http://localhost:8000/image1"));
    } catch (IOException | URISyntaxException ex) {
        ex.printStackTrace();
    }
} else if (e.getSource() == button2) {
    try {
        Desktop.getDesktop().browse(new URI("http://localhost:8000/image2"));
    } catch (IOException | URISyntaxException ex) {
        ex.printStackTrace();
    }
} else if (e.getSource() == button3) {
    try {
        Desktop.getDesktop().browse(new URI("http://localhost:8000/image1"));
        Thread.sleep(1000);
        Desktop.getDesktop().browse(new URI("http://localhost:8000/image2"));
    } catch (IOException | URISyntaxException | InterruptedException ex) {
        ex.printStackTrace();
    }
}

    }
    
    public static void main(String[] args) throws MalformedURLException {
        new Sudokuleen();
    }
}
