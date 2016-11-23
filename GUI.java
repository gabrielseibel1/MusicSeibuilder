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
    /**
     * @var Jpanel the main panel of the UI
     */
    private JPanel rootPanel;


    /**
     * @var Jbutton buttton to open text file
     */
    private JButton buttonArqText;

    /**
     * @var Jbutton button to export music generated to a .mid
     */
    private JButton buttonExport;

    /**
     * @var Jbutton button that opens the Help section
     */
    private JButton buttonHelp;

    /**
     * @var Jbutton button to play the generated song
     */
    private JButton buttonPlay;

    /**
     * @var JEditorPane text area to receive text input from the user
     * also where to show the text from an opened .txt
     */
    private JEditorPane userInput;
    private JButton buttonAbout;

    /**
     * Joins together the elements that compose the GUI
     * and sets up the listeners for the buttons
     */
    public GUI() {

        //listener for the play button
        buttonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MusicStructure musicStructure = getMusicStructure();
                JFuguePlayer player = new JFuguePlayer(musicStructure);
                player.play();
            }
        });

        //listener for the open .txt button
        buttonArqText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileText;

                JFileChooser chooser = new JFileChooser(); //declares a directories/file browser
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(rootPanel); //shows the browser to open a file
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();
                    String fileName = selectedFile.getAbsolutePath();
                    fileText = TextFileManager.readFromTextFile(fileName);
                    userInput.setText(fileText); //puts the read text on the text area
                }
            }
        });

        //listener for the export button
        buttonExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath;

                JFileChooser chooser = new JFileChooser(); //declares a directories/file browser
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showSaveDialog(rootPanel); //shows the browser to save a file
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    filePath = chooser.getSelectedFile().getPath();
                    MusicStructure musicStructure = getMusicStructure();
                    MidiExporter midiExporter = new MidiExporter(musicStructure);
                    midiExporter.export(filePath);
                }
            }
        });

        //listener for the help button
        buttonHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                help();
            }
        });

        //listener for the about button
        buttonAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                about();
            }
        });
    }

    /**
     * @return interpreted song
     */
    private MusicStructure getMusicStructure() {
        String textInput = userInput.getText();
        MusicalTextInterpreter interpreter = new MusicalTextInterpreter(textInput);
        return interpreter.interpret();
    }

    /**
     * Opens a help window
     */
    private void help(){
        String helpText =
                "Para usar o gerador de música, basta digitar notas músicais A-G na caixa de texto e pressionar Reproduzir.\n" +
                "Você também pode selecionar um arquivo texto (.txt) contendo seu texto usando o botão correspondente acima.\n" +
                "Para salvar sua música em um arquivo MIDI (.mid), use o botão de exportar para MIDI acima.\n\n" +
                "        Para mais informações e para conferir a tabela\n" +
                "        de correspondenciais entre letras e notas músicais,\n" +
                "        Acesse www.sitedaaplicacao.com/ajuda\n\n";
        JOptionPane.showMessageDialog(null, helpText);
    }

    /**
     * Opens an About window
     */
    private void about(){
        String aboutText =
                "O Music Seibuilder é um gerador de música a partir de texto!\n" +
                "\n          Desenvolvedores: \n" +
                "          Arthur Adolfo    (Aithui)\n" +
                "          Gabriel Seibel   (Seibão)\n" +
                "          Thomas Fontanari (Thimas)\n" +
                "\n          Professor orientador: \n" +
                "          Marcelo Pimenta  (Pimenta)\n";
        JOptionPane.showMessageDialog(null, aboutText);
    }


    /**
     * Launches the the GUI
     * @param args from a terminal --not used
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
