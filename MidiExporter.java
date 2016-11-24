/**
 * Created by Arthu on 21/11/2016.
 */

import org.jfugue.pattern.Pattern;
import org.jfugue.midi.MidiFileManager;

import java.io.File;

/**
 * Class that works on exporting music to a .mid (MIDI) file
 */
public class MidiExporter {


    /**
     * @var MusicStructure the structure of the song to be saved as .mid (MIDI)
     */
    private MusicStructure musicToBeExported;

    /**
     * @var Pattern Jfugue uses a Pattern type to export a song to .mid (MIDI)
     */
    private Pattern patternToExport;

    MidiExporter() {
        setMusicStructure(new MusicStructure());
        patternToExport = new Pattern();
    }

    MidiExporter(MusicStructure musicToBeExported) {
        setMusicStructure(musicToBeExported);
        patternToExport = new Pattern();
    }

    /**
     * Main function to start playing music from the MusicStructure
     */
    public void export(String name) {
        //Converts MusicStructure to a Pattern
        configurePattern();
        try {
            MidiFileManager.savePatternToMidi(this.patternToExport, new File(name.concat(".mid")));
        }
        catch(Exception e) {
            System.out.println("erro: " + e);
        }
    }

    /**
     * Converts MusicStructure to a Pattern recognizable by JFugue
     */
    public void configurePattern() {
        Pattern pattern = new Pattern();
        while(getMusicStructure().exists()) {
            Sound sound = getMusicStructure().popSound(); //Gets the first Sound out of the structure
            //Concatenates the parts to be intelligible by Jfugue
            String my_note = "X[Volume]=".concat(Integer.toString(sound.getVolume())).concat(" ").concat(Character.toString(sound.getNote())).concat(Integer.toString(sound.getOctave()));
            pattern.add(my_note);
            pattern.setInstrument(sound.getInstrument().getMidiValue());
            pattern.setTempo(sound.getBpm());
        }
        this.patternToExport = pattern;
    }

    /**
     * @param musicToBeExported Structure of the song to be set up
     */
    public void setMusicStructure(MusicStructure musicToBeExported) {
        this.musicToBeExported = musicToBeExported;
    }

    /**
     * @return MusicStructure The song's structure
     */
    public MusicStructure getMusicStructure() {
        return this.musicToBeExported;
    }

    /**
     * @return Pattern The song's pattern
     */
    public Pattern getPattern() {
        return this.patternToExport;
    }
}
