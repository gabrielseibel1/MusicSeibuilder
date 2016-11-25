import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by Arthu on 24/11/2016.
 */
public class JFuguePlayerTest {

    /**
     * Asserts notes are configutred correctly
     * @throws Exception when one note is setup incorrectly
     */
    @Test
    public void configureNotes() throws Exception {
        String text = "AB!A D?CoA\nE;F,G";
        MusicalTextInterpreter mi = new MusicalTextInterpreter(text);
        MusicStructure music = mi.interpret();
        JFuguePlayer player = new JFuguePlayer(music);
        player.configureNotes();

        //0 Reads note "A" to be played
        assertEquals("A5w120a50", player.getNotes().remove(0).toString());
        assertEquals("76", player.getInstruments().remove(0).toString());

        //1 Reads note "B" to be played
        assertEquals("B5w120a50", player.getNotes().remove(0).toString());
        assertEquals("76", player.getInstruments().remove(0).toString());

        //2 Reads "!A" to be played (change instrument plays next song)
        assertEquals("A5w120a50", player.getNotes().remove(0).toString());
        assertEquals("7", player.getInstruments().remove(0).toString());

        //3 Reads " D" to be played (doubles volume for next note. plays next song.)
        assertEquals("D5w120a100", player.getNotes().remove(0).toString());
        assertEquals("7", player.getInstruments().remove(0).toString());

        //4 Reads "?C" to be played (Next note should have octave equals 6)
        assertEquals("C6w120a100", player.getNotes().remove(0).toString());
        assertEquals("7", player.getInstruments().remove(0).toString());

        //5 Reads "oA", increases volume in 10%. Volume doesn't change, since 100 is the maximum volume.
        assertEquals("A6w120a100", player.getNotes().remove(0).toString());
        assertEquals("7", player.getInstruments().remove(0).toString());

        //6 Reads "\nE" . Next instrument ought to be Tubular Bells.
        assertEquals("E6w120a100", player.getNotes().remove(0).toString());
        assertEquals("15", player.getInstruments().remove(0).toString());

        //7 Reads ";F" . Next instrument should be Pan Flute.
        assertEquals("F6w120a100", player.getNotes().remove(0).toString());
        assertEquals("76", player.getInstruments().remove(0).toString());

        //8 Reads ",G" . Nextra instrument should be Church Organ.
        assertEquals("G6w120a100", player.getNotes().remove(0).toString());
        assertEquals("20", player.getInstruments().remove(0).toString());
    }

}