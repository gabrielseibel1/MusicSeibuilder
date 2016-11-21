/**
 * Created by Arthu on 21/11/2016.
 */

import org.jfugue.player.Player;
import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.pattern.Pattern;
import org.jfugue.theory.Note;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.theory.ChordProgression;
import java.io.File;

import java.util.ArrayList;

/**
 * Classe que trata a forma de exportar a música para Midi File
 */
public class MidiExporter {
    /**
     * @var MusicStructure Estrutura da música a ser tocada
     */
    private MusicStructure music_structure;

    /**
     * @var Pattern padrão a ser exportado
     */
    private Pattern pattern;

    /**
     * Construtor da classe. Seta os atributos iniciais da classe.
     */
    MidiExporter() {
        setMusicStructure(new MusicStructure());
        pattern = new Pattern();
    }

    MidiExporter(MusicStructure music_structure) {
        setMusicStructure(music_structure);
        pattern = new Pattern();
    }

    /**
     * Função principal para começar a tocar a música do MusicStructure
     * @throws Exception
     */
    public void export(String name) {
        //Configura as notas, separando os sounds da MusicStructure em um array de Notes
        configurePattern();
        try {
            MidiFileManager.savePatternToMidi(this.pattern, new File(name.concat(".mid")));
        }
        catch(Exception e) {
            System.out.println("erro: " + e);
        }
    }

    /**
     * Método que converte a MusicStructure para um array de Notes e Instruments, reconhecido pelo JFugue
     */
    public void configurePattern() {
        Pattern pattern = new Pattern();
        while(getMusicStructure().exists()) {
            Sound sound = getMusicStructure().popSound(); //Retira o primeiro sound
            //Concatena as partes para ficar inteligível para o JFugue
            String my_note = "X[Volume]=".concat(Integer.toString(sound.getVolume())).concat(" ").concat(Character.toString(sound.getNote())).concat(Integer.toString(sound.getOctave()));
            pattern.add(my_note);
            pattern.setInstrument(sound.getInstrument().getMidiValue());
            pattern.setTempo(sound.getBpm());
        }
        this.pattern = pattern;
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
