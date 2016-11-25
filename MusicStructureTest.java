import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Pc on 11/24/2016.
 */
public class MusicStructureTest {

    /**
     * Asserts that the boolean exists() implies that the MusicStructure is not empty
     * @throws Exception when exists() is incorrect
     */
    @Test
    public void existsTest() throws Exception {
        MusicStructure musicStructure = new MusicStructure();
        assertFalse(musicStructure.exists());

        Sound sound = new Sound();
        musicStructure.addSound(sound);
        assertTrue(musicStructure.exists());
    }

    /**
     * Asserts that a sound can be added correctly to a MusicStructure
     * @throws Exception when sound was not added or was added wrong
     */
    @Test
    public void addSound() throws Exception {
        MusicStructure musicStructure = new MusicStructure();
        Sound soundTobeAdded = new Sound();

        //adds sound to music structure and saves it to compare later
        musicStructure.addSound(soundTobeAdded);
        Sound addedSound = musicStructure.getFirstSound();

        sound1EqualsSound2(addedSound, soundTobeAdded);
    }

    /**
     * Asserts that two sounds are equal to each other
     * @param sound1 first sound
     * @param sound2 second sound
     * @throws Exception when sound1 and sound2 are different
     */
    private void sound1EqualsSound2(Sound sound1, Sound sound2) throws Exception {
        //saves the sound characteristics to compare later
        int instrument = sound1.getInstrument().getMidiValue();
        char note = sound1.getNote();
        int bpm = sound1.getBpm();
        int octave = sound1.getOctave();
        int volume = sound1.getVolume();

        //compares sound to be added with added sound
        assertEquals(instrument, sound2.getInstrument().getMidiValue());
        assertEquals(note,sound2.getNote());
        assertEquals(bpm,sound2.getBpm());
        assertEquals(octave,sound2.getOctave());
        assertEquals(volume,sound2.getVolume());
    }


    //not yet implemented
/*    @Test
    public void toSoundArray() throws Exception {

    }

    @Test
    public void getSounds() throws Exception {

    }

    @Test
    public void popSound() throws Exception {

    }

    *//**
     * Asserts that getFirstSound gets no other sound than the first one
     * @throws Exception when getFirstSound() doesn't return the correct Sound
     *//*
    @Test
    public void getFirstSound() throws Exception {

    }

    @Test
    public void getLastSound() throws Exception {

    }

    @Test
    public void clearSounds() throws Exception {

    }

    @Test
    public void toStringTest() throws Exception {

    }*/

}