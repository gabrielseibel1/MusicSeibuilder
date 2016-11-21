/**
 * Created by Arthu on 21/11/2016.
 */

import org.jfugue.player.Player;
import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.pattern.Pattern;
import org.jfugue.theory.Note;
import org.jfugue.theory.ChordProgression;

import java.util.ArrayList;

/**
 * Classe que trata a forma de tocar a MusicStructure com o JFugue
 */
public class JFuguePlayer {
    /**
     * @var MusicStructure Estrutura da música a ser tocada
     */
    private MusicStructure music_structure;

    /**
     * @var ArrayList<Note> Lista de notas a serem tocadas pelo RealtimePlayer do JFugue
     */
    private ArrayList<Note> notes;

    /**
     * @var ArrayList<String> Lista de instrumentos para trocar durante o player
     */
    private ArrayList<String> instruments;

    /**
     * @var int Um minuto em milissegundos
     */
    private static final int UM_MINUTO = 60000;

    /**
     * Construtor da classe. Seta os atributos iniciais da classe.
     */
    JFuguePlayer() {
        setMusicStructure(new MusicStructure());
        notes = new ArrayList<Note>();
        instruments = new ArrayList<String>();
    }

    JFuguePlayer(MusicStructure music_structure) {
        setMusicStructure(music_structure);
        notes = new ArrayList<Note>();
        instruments = new ArrayList<String>();
    }

    /**
     * Função principal para começar a tocar a música do MusicStructure
     * @throws Exception
     */
    public void play() {
        //Configura as notas, separando os sounds da MusicStructure em um array de Notes
        configureNotes();
        try {
            RealtimePlayer player = new RealtimePlayer();
            //Retira nota por nota do array, até ele ficar vazio
            while(!notes.isEmpty()) {
                Note note = notes.remove(0); //Pega a pr[oxima nota a ser tocada
                player.startNote(note);
                player.changeInstrument(instruments.remove(0)); //Pega o instrumento a ser trocado
                waitForDuration(note); //Espera duração de nota para tocar outra nota
            }
            player.close();
        }
        catch(Exception e) {
            System.out.println("erro: " + e);
        }

    }

    /**
     * Método para dar um sleep com o tempo (BPM) das notas
     * @param Note nota a ser definido o tempo de duração para Sleep
     * @throws Exception
     */
    public void waitForDuration(Note note) {
        try {
            Double d = new Double(note.getDuration());
            int bpm = d.intValue();
            Thread.sleep(UM_MINUTO/bpm);
        }
        catch(Exception e) {
            System.out.println("erro: " + e);
        }
    }

    /**
     * Método que converte a MusicStructure para um array de Notes e Instruments, reconhecido pelo JFugue
     */
    public void configureNotes() {
        while(getMusicStructure().exists()) {
            Sound sound = getMusicStructure().popSound(); //Retira o primeiro sound
            //Concatena as partes para ficar inteligível para o JFugue
            String my_note = Character.toString(sound.getNote()).concat(Integer.toString(sound.getOctave())).concat("a").concat(Integer.toString(sound.getVolume()));
            Note note = new Note(my_note);
            note.setDuration(sound.getBpm());
            notes.add(note); //Adiciona a nota no array de Notes
            instruments.add(sound.getInstrument().getName()); //Adiciona o instrumento no array de Instruments
        }
    }

    /**
     * @param MusicStructure Estrutura da música a ser setada
     */
    public void setMusicStructure(MusicStructure music_structure) {
        this.music_structure = music_structure;
    }

    /**
     * @return MusicStructure Estrutura da música
     */
    public MusicStructure getMusicStructure() {
        return this.music_structure;
    }
}
