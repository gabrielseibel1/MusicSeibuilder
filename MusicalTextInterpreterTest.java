import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;


public class MusicalTextInterpreterTest {

    /**
     * Tests basic string to be interpreted by MusicalTextInterpreter
     */
    @Test
    public void interpretBasics() {
        String text = "Aab A!BoC?D\nE;F,G";
        MusicalTextInterpreter mi = new MusicalTextInterpreter(text);
        MusicStructure music = mi.interpret();
        LinkedList<Sound> sounds = music.getSounds();

        //0 Reads A
        Sound sound = sounds.get(0);
        assertEquals('A', sound.getNote());
        assertEquals(Instrument.PAN_FLUTE, sound.getInstrument().getMidiValue());
        assertEquals(5, sound.getOctave());
        assertEquals(50, sound.getVolume());

        //1 Reads a. Repeats last note.
        sound = sounds.get(1);
        assertEquals('A', sound.getNote());
        assertEquals(Instrument.PAN_FLUTE, sound.getInstrument().getMidiValue());
        assertEquals(5, sound.getOctave());
        assertEquals(50, sound.getVolume());

        //2 Reads b. Plays pause, because last character wasn't a note.
        sound = sounds.get(2);
        assertEquals('R', sound.getNote());
        assertEquals(Instrument.PAN_FLUTE, sound.getInstrument().getMidiValue());
        assertEquals(5, sound.getOctave());
        assertEquals(50, sound.getVolume());

        //3 Reads ' ', doubles volume for next note.
        //4 Reads A, must have doubled volume since last note.
        sound = sounds.get(3);
        assertEquals('A', sound.getNote());
        assertEquals(Instrument.PAN_FLUTE, sound.getInstrument().getMidiValue());
        assertEquals(5, sound.getOctave());
        assertEquals(100, sound.getVolume());

        //5 Reads ! Next instrument must be Harpsichord
        //6 Reads B, must have Harpsichord as instrument.
        sound = sounds.get(4);
        assertEquals('B', sound.getNote());
        assertEquals(Instrument.HARPSICHORD, sound.getInstrument().getMidiValue());
        assertEquals(5, sound.getOctave());
        assertEquals(100, sound.getVolume());

        //7 Reads 'o', increases volume in 10%. Volume doesn't change, since 100 is the maximum volume.
        //8 Reads C.
        sound = sounds.get(5);
        assertEquals('C', sound.getNote());
        assertEquals(Instrument.HARPSICHORD, sound.getInstrument().getMidiValue());
        assertEquals(5, sound.getOctave());
        assertEquals(100, sound.getVolume());

        //9 Reads ? . Next sound should have octave equals 6
        //10 Reads D.
        sound = sounds.get(6);
        assertEquals('D', sound.getNote());
        assertEquals(Instrument.HARPSICHORD, sound.getInstrument().getMidiValue());
        assertEquals(6, sound.getOctave());
        assertEquals(100, sound.getVolume());

        //11 Reads '\n' . Next instrument ought to be Tubular Bells.
        //12 Reads E.
        sound = sounds.get(7);
        assertEquals('E', sound.getNote());
        assertEquals(Instrument.TUBULAR_BELLS, sound.getInstrument().getMidiValue());
        assertEquals(6, sound.getOctave());
        assertEquals(100, sound.getVolume());

        //13 Reads ; . Next instrument should be Pan Flute.
        //14 Reads F
        sound = sounds.get(8);
        assertEquals('F', sound.getNote());
        assertEquals(Instrument.PAN_FLUTE, sound.getInstrument().getMidiValue());
        assertEquals(6, sound.getOctave());
        assertEquals(100, sound.getVolume());

        //15 Reads , . Nextra instrument should be Church Organ.
        //16 Reads G.
        sound = sounds.get(9);
        assertEquals('G', sound.getNote());
        assertEquals(Instrument.CHURCH_ORGAN, sound.getInstrument().getMidiValue());
        assertEquals(6 ,sound.getOctave());
        assertEquals(100, sound.getVolume());
    }

    /**
     * Test more sensitive cases from the specification.
     */
    @Test
    public void interpretExtremes(){

        // Since two spaces doesn't define any note, list of sounds should be empty.
        String text = "  ";
        MusicalTextInterpreter mi = new MusicalTextInterpreter(text);
        MusicStructure music = mi.interpret();
        LinkedList<Sound> sounds = music.getSounds();
        assertEquals(true, sounds.isEmpty());

        // Increases the volume 10% three times. For the default value is 50, the new value must be 66. There must be only one note: A.
        mi.setText("iuOA");
        mi.resetInterpretation();
        music = mi.interpret();
        sounds = music.getSounds();
        assertEquals(1, sounds.size());
        assertEquals('A', sounds.get(0).getNote());
        assertEquals(66, sounds.get(0).getVolume());

        // Digits increase midi value of the instrument according to their own values. There must be only one note: B.
        mi.setText("123456789B");
        mi.resetInterpretation();
        music = mi.interpret();
        sounds = music.getSounds();
        assertEquals(1, sounds.size());
        assertEquals('B', sounds.get(0).getNote());
        assertEquals(Instrument.PAN_FLUTE + 45, sounds.get(0).getInstrument().getMidiValue());

        //Increases octave three times, reaching 8, and then 3 more times. Since 9 is the default maximum octave, the end value must be 6.
        mi.setText("???B");
        mi.resetInterpretation();
        music = mi.interpret();
        sounds = music.getSounds();
        assertEquals('B', sounds.get(0).getNote());
        assertEquals(8, sounds.get(0).getOctave());
        mi.setText("???B");
        music = mi.interpret();
        sounds = music.getSounds();
        assertEquals('B', sounds.get(1).getNote());
        assertEquals(6, sounds.get(1).getOctave());
    }
}