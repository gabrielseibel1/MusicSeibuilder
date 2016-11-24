/**
 * Created by Arthu on 21/11/2016.
 */

import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.theory.Note;

import java.util.ArrayList;

/**
 * Class that describes the way to play a MusicStructure using JFugue
 */
public class JFuguePlayer {

    private MusicStructure musicToBePlayed;

    /**
     * @var ArrayList<Note> List of musical notes to be played by Jfugue's RealTimePlayer
     */
    private ArrayList<Note> notesToBePlayed;

    /**
     * @var ArrayList<Integer> List of instruments that play the song's notes
     */
    private ArrayList<Integer> instrumentsToBePlayed;

    /**
     * @var int One minute in milliseconds
     */
    private static final int ONE_MINUTE = 60000;

    JFuguePlayer() {
        setMusicStructure(new MusicStructure());
        notesToBePlayed = new ArrayList<Note>();
        instrumentsToBePlayed = new ArrayList<Integer>();
    }

    JFuguePlayer(MusicStructure musicToBePlayed) {
        setMusicStructure(musicToBePlayed);
        notesToBePlayed = new ArrayList<Note>();
        instrumentsToBePlayed = new ArrayList<Integer>();
    }

    /**
     * Main function to start playing the song from the MusicStructure
     */
    public void play() {
        //converts the MusicStructure to a Notes Array and an Instruments Array
        configureNotes();
        try {
            RealtimePlayer player = new RealtimePlayer();
            //Takes off the notes of the array one by one, until it's empty
            while(!notesToBePlayed.isEmpty()) {
                Note note = notesToBePlayed.remove(0); //get's the next note to be played
                player.changeInstrument(instrumentsToBePlayed.remove(0)); //get's the instument to execute the note
                player.startNote(note);
                waitForDuration(note); //Wait for the notes's duration to start playing another one
                player.stopNote(note);
            }
            player.close();
        }
        catch(Exception e) {
            System.out.println("erro: " + e);
        }

    }

    /**
     * Method to sleep(wait) for the time of each note - based on the BPM
     * @param note the note on which the Sleep Time is based
     */
    public void waitForDuration(Note note) {
        try {
            Double d = new Double(note.getDuration());
            int bpm = d.intValue();
            Thread.sleep(ONE_MINUTE /bpm);
        }
        catch(Exception e) {
            System.out.println("erro: " + e);
        }
    }

    /**
     * Method that converts the MusicStructure to a Notes Array and an Instruments Array, recognizable by JFugue
     */
    public void configureNotes() {
        while(getMusicStructure().exists()) {
            Sound sound = getMusicStructure().popSound(); //Gets the first Sound out of the structure
            //Concatenates the parts to be intelligible by Jfugue
            String my_note = Character.toString(sound.getNote()).concat(Integer.toString(sound.getOctave())).concat("a").concat(Integer.toString(sound.getVolume()));
            Note note = new Note(my_note);
            note.setDuration(sound.getBpm());
            notesToBePlayed.add(note); //adds the note to the notes array
            instrumentsToBePlayed.add(sound.getInstrument().getMidiValue()); //adds the instrument to the instrument array
        }
    }

    /**
     * @param musicToBePlayed structure of the song to be set up
     */
    public void setMusicStructure(MusicStructure musicToBePlayed) {
        this.musicToBePlayed = musicToBePlayed;
    }

    /**
     * @return MusicStructure the structure of the song
     */
    public MusicStructure getMusicStructure() {
        return this.musicToBePlayed;
    }

    /**
     * @return ArrayList<Note> the structure of notes
     */
    public ArrayList<Note> getNotes() {
        return this.notesToBePlayed;
    }

    /**
     * @return ArrayList<Integer> the structure of notes
     */
    public ArrayList<Integer> getInstruments() {
        return this.instrumentsToBePlayed;
    }
}
