package gruntled;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class About 
{
    void create(JFrame main)
    {
        JFrame frame = new JFrame("About");
        JPanel about = new JPanel();
        
        frame.setIconImage(new ImageIcon(getClass().getResource("/resources/favicon.png")).getImage());
        about.setLayout(new BoxLayout(about, BoxLayout.Y_AXIS));
        
        about.add(Box.createRigidArea(new Dimension(0,5)));
        about.add(new JLabel("Developed by: Gruntled Inc."));
        about.add(new JLabel("Developed for: Eagle's Nest Golf Club"));
        about.add(Box.createRigidArea(new Dimension(0,20)));
        about.add(new JLabel("Developers:"));
        about.add(Box.createRigidArea(new Dimension(0,3)));
        about.add(new JLabel("Philip Cappello | philip.cappello18@ycdsbk12.ca"));
        about.add(new JLabel("Julian Kuyumcu | julian.kuyumcu18@ycdsbk12.ca"));
        about.add(new JLabel("Adam Sorrenti | adam.sorrenti18@ycdsbk12.ca"));
        
        ImageIcon eagle = new ImageIcon(getClass().getResource("/resources/5295.png"));
        
        frame.add(about);
        frame.add(new JLabel(eagle));

        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(main);
        frame.pack();
    }
}