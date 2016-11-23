import sun.rmi.runtime.Log;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Graphical user interface
 */
public class GUI {
    private JPanel rootPanel;
    private JButton buttonArqText;
    private JButton buttonExport;
    private JButton buttonHelp;
    private JButton buttonPlay;
    private JEditorPane userInput;

    public GUI() {
        buttonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MusicStructure musicStructure = getMusicStructure();
                JFuguePlayer player = new JFuguePlayer(musicStructure);
                player.play();
            }
        });
        buttonArqText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileText;

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(rootPanel);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();
                    String fileName = selectedFile.getAbsolutePath();
                    fileText = TextFileManager.readFromTextFile(fileName);
                    userInput.setText(fileText);
                }
            }
        });
        buttonExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath;

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showSaveDialog(rootPanel);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    filePath = chooser.getSelectedFile().getPath();
                    MusicStructure musicStructure = getMusicStructure();
                    MidiExporter midiExporter = new MidiExporter(musicStructure);
                    midiExporter.export(filePath);
                }
            }
        });
        buttonHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private MusicStructure getMusicStructure() {
        String textInput = userInput.getText();
        MusicalTextInterpreter interpreter = new MusicalTextInterpreter(textInput);
        return interpreter.interpret();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
