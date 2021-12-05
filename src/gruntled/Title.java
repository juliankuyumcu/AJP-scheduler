package gruntled;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Title 
{
    int input = 0;
    JOptionPane dialog = new JOptionPane();
    
    void edit(JTabbedPane tab, int index)
    {
        JPanel edit = new JPanel();
        JLabel label = new JLabel("Edit Title:");
        JTextField title = new JTextField(10);
        edit.add(label);
        edit.add(title);
        
        input = dialog.showConfirmDialog
                (null, edit, "Edit Title",
                        JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE);
        
            if(input == dialog.OK_OPTION)
                if(!title.getText().equals(""))               
                    tab.setTitleAt(index, title.getText());
    }
}
