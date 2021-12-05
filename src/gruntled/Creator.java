package gruntled;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class Creator extends WindowAdapter
{
    JFrame main = null;
    
        Creator(JFrame main)
        {
            main = this.main;
        }
        
        Creator()
        {
        }
        
        JFrame createFrame(int WIDTH, int HEIGHT, String title)
        {
            JFrame frame = null;
            JPanel buttonPane = new JPanel();

                frame = new JFrame(title); 
                frame.setSize(WIDTH, HEIGHT);
                frame.setResizable(true);

                    if(title.equals("Scheduler"))
                        frame.setLayout(new FlowLayout());
                    else
                        frame.setLayout(new BoxLayout(frame.getContentPane(), 
                                BoxLayout.Y_AXIS));

                frame.setFocusable(true);
                frame.addWindowListener(this);

                    if(title.equals("Scheduler"))
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    else
                        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                return frame;
        }

        void createButton(String title, JFrame frame)
        {
            JButton button = new JButton(title);

                button.setVisible(true);
                button.setFocusPainted(false);
                frame.add(button);
        }

        @Override
        public void windowClosing(WindowEvent e)
        {
            main.setEnabled(true);
        }
}
