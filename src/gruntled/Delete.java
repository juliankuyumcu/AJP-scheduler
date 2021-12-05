package gruntled;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import au.com.bytecode.opencsv.CSVReader;

public class Delete
{
    int input = 0;
    JOptionPane dialog = new JOptionPane();

    void delete(JTabbedPane tab, int index, FileReader fr, CSVReader csvfr) throws ArrayIndexOutOfBoundsException
    {
        final String title = tab.getTitleAt(index);
        System.out.println(title);

        JPanel delete = new JPanel();
        delete.add(new JLabel("Delete source file?"));

        input = dialog.showConfirmDialog
                (null, delete, "Delete Schedule",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE);

            if(input != dialog.CANCEL_OPTION)
            {
                tab.remove(tab.getSelectedComponent());
            }

            if(input == dialog.YES_OPTION)
            {
                File file = new File("D:\\Dropbox\\ICS 4U\\Desktop\\Adam\\Gruntled\\src\\resources\\" + title);
                    try 
                    {
                        fr.close();
                        csvfr.close();
                    } 
                    catch (IOException ex) 
                    {
                        Logger.getLogger(Delete.class.getName()).log(Level.SEVERE, null, ex);
                    }
                file.delete();
            }
    }
}
