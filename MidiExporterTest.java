import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Arthu on 24/11/2016.
 */
public class MidiExporterTest {
    @Test
    public void configurePattern() throws Exception {
        String text = "AB!A D?CoA\nE;F,G";
        MusicalTextInterpreter mi = new MusicalTextInterpreter(text);
        MusicStructure music = mi.interpret();
        MidiExporter midi = new MidiExporter(music);
        midi.configurePattern();

        //0 Reads all the strings and make the things that MusicalTextInterpreter do in interpret() method. So, it should have this sintax for the string given.
        assertEquals("T120 I[Reed_Organ] X[Volume]=50 A5 X[Volume]=50 B5 X[Volume]=50 A5 X[Volume]=100 D5 X[Volume]=100 C6 X[Volume]=100 A6 X[Volume]=100 E6 X[Volume]=100 F6 X[Volume]=100 G6", midi.getPattern().toString());
    }

}