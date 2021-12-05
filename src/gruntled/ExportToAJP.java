package gruntled;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class ExportToAJP 
{
    void exportToAJP(JTable tableToExport, String tabName, String pathToExportTo) throws IOException 
        {
            BufferedWriter out;
            TableModel model = tableToExport.getModel();
            File f = new File(System.getProperty("java.class.path"));
                if (pathToExportTo==null)
                {
                    pathToExportTo = URLDecoder.decode(f.getAbsoluteFile().getParentFile().getPath(), "UTF-8");
                    out = new BufferedWriter(new FileWriter(pathToExportTo+"\\saves\\"+tabName+".ajp"));
                }
                else
                    out = new BufferedWriter(new FileWriter(pathToExportTo));           
                try 
                {
                        for(int i=0; i < model.getColumnCount(); i++) {
                            if (i!=model.getColumnCount()-1)
                                out.write(model.getColumnName(i) + ",");
                            else
                                out.write(model.getColumnName(i));
                        }
                    out.write("\n");
                        for(int i=0; i< model.getRowCount(); i++) {
                                for(int j=0; j < model.getColumnCount(); j++) {
                                    if (model.getValueAt(i,j)!= null)
                                    {
                                        if (j!=model.getColumnCount()-1)
                                            out.write(model.getValueAt(i,j).toString()+",");
                                        else
                                            out.write(model.getValueAt(i,j).toString());
                                    }
                                    else if (j!=model.getColumnCount()-1)
                                        out.write(",");
                                }
                            out.write("\n");
                        }
                } catch (IOException e) {}
                finally 
                {
                    try {
                        if(out != null){
                            out.close();
                        } else {
                            System.out.println("Buffer has not been initialized!");
                        }
                    } catch (IOException e) {}
                }
        }
}
