import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabri on 21/11/2016.
 */
public class TextFileManager {







    /*
    public static String pickFile(){
        String filePath;
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(comp);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            filePath = chooser.getSelectedFile().getAbsolutePath();
        return filePath;
    }
    */

    public static String readFromTextFile(String filename){
        List<String> fileText = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                fileText.add(line);
            }
            reader.close();
            return fileText.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
