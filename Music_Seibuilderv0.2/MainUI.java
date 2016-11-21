import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class MainUI {

	private JFrame frmSeibuilder;
	private String input_text = "";
	private boolean is_playing = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frmSeibuilder.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*
		 * Defines frame for user interface.
		 */
		frmSeibuilder = new JFrame();
		frmSeibuilder.getContentPane().setBackground(SystemColor.control);
		frmSeibuilder.setTitle("Music Seibuilder");
		frmSeibuilder.setBounds(100, 100, 450, 300);
		frmSeibuilder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSeibuilder.getContentPane().setLayout(null);
		
		/*
		 * Editor pane. Contains text to be transformed in song.
		 */
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 434, 195);
		frmSeibuilder.getContentPane().add(scrollPane);
		JEditorPane editorPane = new JEditorPane();
		scrollPane.setViewportView(editorPane);

		
		
		/*
		 * Play button. When pressed, plays song written in the editor pane.
		 */
		JButton play_button = new JButton("");
		play_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isPlaying()){
					play_button.setIcon(new ImageIcon(this.getClass().getResource("/pause_icon.png")));
					setIsPlaying(true);
					//play()
				}else{
					play_button.setIcon(new ImageIcon(this.getClass().getResource("/play_icon.png")));
					setIsPlaying(false);
				}
				
			}
		});
		play_button.setForeground(SystemColor.control);
		play_button.setBackground(SystemColor.control);
		play_button.setBounds(199, 206, 29, 23);
		Border emptyBorder = BorderFactory.createEmptyBorder();
		play_button.setBorder(emptyBorder);
		play_button.setIcon(new ImageIcon(this.getClass().getResource("/play_icon.png")));
		frmSeibuilder.getContentPane().add(play_button);
		
		
		/*
		 * Defines Menu bar.
		 */
		JMenuBar menuBar = new JMenuBar();
		frmSeibuilder.setJMenuBar(menuBar);
		
		
		/*
		 * Menu item containing File related activities.
		 */
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		
		/*
		 * Button that opens windows explorer file chooser and sets new text for the editor pane.
		 */
		JMenuItem mntmAbrirTexto = new JMenuItem("Abrir Texto");
		mntmAbrirTexto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				open_txt_file(mntmAbrirTexto);
				editorPane.setText(input_text);
			}
		});
		mnArquivo.add(mntmAbrirTexto);
		
		
		/*
		 * Button that saves written text to txt file.
		 */
		JMenuItem mntmSalvarText = new JMenuItem("Salvar Texto");
		mntmSalvarText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input_text = editorPane.getText();
				save_txt_file(mntmSalvarText);
			}
		});
		mnArquivo.add(mntmSalvarText);
		
		
		/*
		 * Sub menu item related to exports.
		 */
		JMenu mnExportar = new JMenu("Exportar...");
		mnArquivo.add(mnExportar);
		
		/*
		 * Button that exports written text to MIDI file.
		 */
		JMenuItem mntmMidi = new JMenuItem("MIDI");
		mnExportar.add(mntmMidi);
		
		
		/*
		 * Button that exports written text to WAV file.
		 */
		JMenuItem mntmWav = new JMenuItem("WAV");
		mnExportar.add(mntmWav);
		
		
		/*
		 * Menu item related to runnable text.
		 */
		JMenu mnExecutar = new JMenu("Executar");
		menuBar.add(mnExecutar);

		/*
		 * Button that runs text written.
		 */
		JMenuItem mntmExecutarTexto = new JMenuItem("Executar Texto");
		mnExecutar.add(mntmExecutarTexto);
	}
	
	
	/**
	 * Shows windows explorer file chooser and opens selected TXT file in the text editor pane.
	 */
	private void open_txt_file(Component comp){
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Text files", "txt");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(comp);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	File selected_file = chooser.getSelectedFile();
	    	
	    	try {
				BufferedReader buf_read = new BufferedReader(new FileReader(selected_file));
				String current_line = "";
				
				input_text = "";
				while((current_line = buf_read.readLine()) != null){
					input_text += current_line;
					input_text += '\n';
				}
				
				buf_read.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    }
	}
	
	
	/**
	 * Shows windows explorer file chooser and saves text in editor pane to selected file.
	 */
	private void save_txt_file(Component comp){
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Text files", "txt");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showOpenDialog(comp);
	    if(returnVal == JFileChooser.APPROVE_OPTION){
	    	File selected_file = chooser.getSelectedFile();

	    	FileWriter fw;
			try {
				fw = new FileWriter(selected_file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(input_text);
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}
	
	
	/**
	 * Starts to play song written.
	 */
	private void start_music(){
		
	}
	
	/**
	 * Pauses music currently being played.
	 */
	private void pause_music(){
		
	}
	
	/**
	 * Informs state of player.
	 */
	private boolean isPlaying(){
		return is_playing;
	}
	
	/**
	 * Sets state of player.
	 */
	private void setIsPlaying(boolean value){
		is_playing = value;
	}
}
