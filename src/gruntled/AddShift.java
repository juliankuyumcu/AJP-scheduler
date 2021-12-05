package gruntled;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddShift
{
    int input = 0;
    JOptionPane dialog = new JOptionPane();
    
    int create()
    {
        boolean digit = true;
        JPanel add = new JPanel();
        add.setLayout(new FlowLayout());

        add.add(new JLabel("Quantity:"));
        JTextField number = new JTextField(3);
        add.add(number);

        input = dialog.showConfirmDialog
                (null, add, "Add Shifts",
                        JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE);

        char[] shifts = number.getText().toCharArray();

            for(char c : shifts)
                if(!Character.isDigit(c))
                {
                    digit = false;
                    break;
                }

            if(input == dialog.OK_OPTION && digit)
                return Integer.parseInt(number.getText());
            else
                return 0;
    }
}