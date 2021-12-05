package gruntled;

import au.com.bytecode.opencsv.CSVReader;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

class Scheduler1
{
        public static void main(String[] args) 
        {
           GUI gui = new GUI();
           gui.create();
        }
}

public final class GUI extends WindowAdapter implements ActionListener
{
    public JFrame main = null;//main JFrame
    JTabbedPane tab = null;//main tabs
    JPanel header = null;//JPanel for buttons
    //JButtons
    JButton create = null;
    JButton open = null;
    JButton add = null;
    JButton remove = null;
    JButton delete = null;
    JButton editTab = null;
    JButton save = null;
    JButton print = null;
    //Menu Bar items
    MenuItem saveItem = null;
    MenuItem saveAsItem = null;
    MenuItem printItem = null;
    MenuItem exitItem = null;
    MenuItem deleteItem = null;
    MenuItem removeItem = null;
    MenuItem about = null;
    //Used in delete funtion
    FileReader fr = null;
    CSVReader csvfr = null;
    
        void create()
        {
            main = new JFrame("AJP Scheduler");
            main.setIconImage(new ImageIcon(getClass().getResource("/resources/favicon.png")).getImage());
            main.setSize(1600,900);
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main.setLocationRelativeTo(null);
            main.addWindowListener(this);
            main.setLayout(new BoxLayout(main.getContentPane(), BoxLayout.Y_AXIS));
            main.setVisible(true);
            
                try 
                {
                    UIManager.setLookAndFeel
                        (UIManager.getSystemLookAndFeelClassName());
                }catch (Exception ex) {}
                
            header = new JPanel();
            header.setLayout(new FlowLayout());
                
            MenuBar menubar = new MenuBar();
            Menu file = new Menu("File");
            Menu edit = new Menu("Edit");
            Menu help = new Menu("Help");
            
            saveItem = new MenuItem("Save");
            saveItem.addActionListener(this);
            saveAsItem = new MenuItem("Save As...");
            saveAsItem.addActionListener(this);
            printItem = new MenuItem("Print");
            printItem.addActionListener(this);
            exitItem = new MenuItem("Exit");
            exitItem.addActionListener(this);
            
            file.add(saveItem);
            file.add(saveAsItem);
            file.add(printItem);
            file.add(exitItem);
            
            removeItem = new MenuItem("Remove Shifts");
            removeItem.addActionListener(this);
            deleteItem = new MenuItem("Delete Schedule");
            deleteItem.addActionListener(this);
            
            edit.add(removeItem);
            edit.add(deleteItem);
            
            about = new MenuItem("About");
            about.addActionListener(this);
            
            help.add(about);
            
            menubar.add(file);
            menubar.add(edit);
            menubar.add(help);
            main.setMenuBar(menubar);
            
            create = new JButton("Create Schedule");
            create.addActionListener(this);
            create.setFocusPainted(false);
            header.add(create);
            
            open = new JButton("Open Schedule");
            open.addActionListener(this);
            open.setFocusPainted(false);
            header.add(open);
            
            add = new JButton("Add Shifts");
            add.addActionListener(this);
            add.setFocusPainted(false);
            header.add(add);
            
            remove = new JButton("Remove Shifts");
            remove.addActionListener(this);
            remove.setFocusPainted(false);
            header.add(remove);

            editTab = new JButton("Edit Title");
            editTab.addActionListener(this);
            editTab.setFocusPainted(false);
            header.add(editTab);
            
            delete = new JButton("Delete Schedule");
            delete.addActionListener(this);
            delete.setFocusPainted(false);
            header.add(delete);

            save = new JButton("Save Schedule");
            save.addActionListener(this);
            save.setFocusPainted(false);
            header.add(save);
            
            print = new JButton("Print");
            print.addActionListener(this);
            print.setFocusPainted(false);
            header.add(print);
 
            main.add(header);
            
            tab = new JTabbedPane();
            tab.setRequestFocusEnabled(false);
            tab.setPreferredSize(new Dimension(1600,800));
            main.add(tab);
            
            SwingUtilities.invokeLater(new Runnable()
            {
                @Override
                public void run()
                {
                    SwingUtilities.updateComponentTreeUI(main);
                }
            });
        }
        JTable getTable()
        {
            JScrollPane scrollPane = (JScrollPane)tab.getSelectedComponent(); 
            JViewport viewport = scrollPane.getViewport(); 
            return (JTable)viewport.getView();
        }
        void openAJP(File selected) throws FileNotFoundException, IOException
        {
            Table table = new Table();
            Object[] headerCSV;
            fr = new FileReader(selected.getPath());
            csvfr = new CSVReader(fr);
            ArrayList myEntries = (ArrayList) csvfr.readAll();
            headerCSV = (String[]) myEntries.get(0);
            DefaultTableModel tableModel;
            tableModel = new DefaultTableModel(headerCSV, myEntries.size()-1);
            int rowcount = tableModel.getRowCount();
                for (int x = 0; x<rowcount+1; x++)
                {
                    int columnnumber = 0;
                    // if x = 0 this is the first row...skip it... data used for columnnames
                    if (x>0)
                    {
                        for (String thiscellvalue : (String[])myEntries.get(x))
                        {
                            tableModel.setValueAt(thiscellvalue, x-1, columnnumber);
                            columnnumber++;
                        }
                    }
                }

            tab.add(table.create(tableModel), selected.getName().substring(0, selected.getName().lastIndexOf('.')));
        }
        void print()
        {
            JTable mytable = getTable(); 
                try 
                {
                    /* print the table */
                    boolean complete = mytable.print(JTable.PrintMode.FIT_WIDTH, 
                            null, null, true, null, false, null);

                        /* if printing completes */
                        if (complete)
                            /* show a success message */
                            JOptionPane.showMessageDialog(main,
                                    "Printing Complete", "Printing Result",
                                    JOptionPane.INFORMATION_MESSAGE);
                        else 
                            //show a message indicating that printing was cancelled
                            JOptionPane.showMessageDialog(main,
                                    "Printing Cancelled","Printing Result",
                                    JOptionPane.INFORMATION_MESSAGE);
                } 
                catch (PrinterException pe) 
                {
                    /* Printing failed, report to the user */
                    JOptionPane.showMessageDialog(main,
                            "Printing Failed: " + pe.getMessage(),
                            "Printing Result", JOptionPane.ERROR_MESSAGE);
                }
        }
        
        void saveAs()
        {
            ExportToAJP run = new ExportToAJP();
            JTable mytable = getTable();
            JFileChooser chooser=new JFileChooser();
            chooser.setCurrentDirectory(new File("/home/me/Documents"));
            // above line is where the file is saved
            int retrival = chooser.showSaveDialog(null);// will pop a "save file" option
                //if user wants to save the file
                if(retrival == JFileChooser.APPROVE_OPTION)
                {
                    try
                    {
                        run.exportToAJP(mytable, tab.getTitleAt(tab.getSelectedIndex()), (chooser.getSelectedFile().getPath())+".ajp");
                    }
                    catch (IOException ex){}        
                }
        }
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Object action = e.getSource();         
            FileFilter filter = new FileNameExtensionFilter("AJP File", "ajp");
            JFileChooser chooser = new JFileChooser(System.getProperty("java.class.path"));
            ExportToAJP run = new ExportToAJP();
            
                chooser.addChoosableFileFilter(filter);
                chooser.setFileFilter(filter);
            
                    if(action==this.create) 
                    {
                        CreateSchedule createSchedule = new CreateSchedule();
                        createSchedule.create();
                            if(createSchedule.input == createSchedule.dialog.OK_OPTION)
                            {
                                tab.addTab(createSchedule.tab, createSchedule.table);
                            }
                    }
                    else if(action==this.open)
                    {
                        int input = chooser.showOpenDialog(main);
                            try 
                            {
                                if(input == JFileChooser.APPROVE_OPTION)
                                    this.openAJP(chooser.getSelectedFile());
                            } 
                            catch (IOException ex) {}
                    }    
                    else if(action==this.add) 
                    {
                        if(tab.getTabCount() != 0)
                        {
                            AddShift addShift = new AddShift();
                            int rows = addShift.create();
                            JTable myTable = getTable();
                            DefaultTableModel model = (DefaultTableModel)myTable.getModel();

                                for(int row = 0; row < rows; row++)
                                    model.addRow(new String[8]);
                        }
                    }  
                    else if(action==this.remove || action==this.removeItem)
                    { 
                        JTable table = getTable();
                        DefaultTableModel model = (DefaultTableModel)table.getModel();
                        int startRow = table.getSelectedRow();
                        int endRow = table.getSelectedRowCount();
                        
                            for(int row = startRow; row < startRow + endRow; row++)
                                model.removeRow(startRow);
                    }
                    else if(action==this.delete || action==this.deleteItem)
                    {
                        try{
                            Delete del = new Delete();
                            del.delete(tab, tab.getSelectedIndex(), fr, csvfr);
                        }catch(NullPointerException ex){}
                    }
                    else if(action == this.editTab)
                    {
                        Title title = new Title();
                        if(tab.getTabCount() != 0)
                            title.edit(tab, tab.getSelectedIndex());
                    }
                    else if(action==this.save)
                    {
                        try
                        {
                            JTable mytable = getTable();
                            try
                            {
                                run.exportToAJP(mytable, tab.getTitleAt(tab.getSelectedIndex()), null);
                            }
                            catch(IOException ex){} 
                        }catch(NullPointerException ex){} 
                    }
                    else if(action==this.print || action==this.printItem)
                        try{
                            print();
                        }catch(NullPointerException ex){}
                    else if(action==this.saveItem)
                    {
                        JTable mytable = getTable();
                            try{
                                run.exportToAJP(mytable, tab.getTitleAt(tab.getSelectedIndex()), null);
                            }catch(IOException ex){}  
                    }
                    else if(action == this.saveAsItem)
                        this.saveAs();
                    else if(action == this.exitItem)
                        System.exit(0);
                    else if(action == this.about)
                    {
                        About info = new About();
                        info.create(main);
                    }       
        }
}