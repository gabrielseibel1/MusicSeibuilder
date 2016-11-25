import org.testng.annotations.Test;

import static org.junit.Assert.*;

/**
 * Tests on the Sound class
 */
public class SoundTest {

    /**
     * Asserts octave is 5 when it is reset
     * @throws Exception when octave is not 5 when reset
     */
    @Test
    public void resetOctaveTest() throws Exception {
        Sound sound = new Sound();
        assertEquals(5, sound.getOctave());
        sound.incOctave();
        sound.resetOctave();
        assertEquals(5, sound.getOctave());
    }

    /**
     * Asserts octave is never bigger than 9
     * @throws Exception when octave is bigger than 9
     */
    @Test
    private void incOctaveTest() throws Exception{
        Sound sound = new Sound();
        assertEquals(5, sound.getOctave());
        for (int i = 0; i < 5; i++)
            sound.incOctave();
        assertEquals(9, sound.getOctave());
    }

    /**
     * Asserts octave is never negative
     * @throws Exception when octave is negative
     */
    @Test
    private void decOctaveTest() throws Exception{
        Sound sound = new Sound();
        assertEquals(5, sound.getOctave());
        for (int i = 0; i < 6; i++){
            sound.decOctave();
        }
        System.out.println(sound.getOctave());
        assertEquals(0, sound.getOctave());
    }

    /**
     * Asserts volume is always between 0 and 100
     * @throws Exception when volume is not valid or there is a division by 0
     */
    @Test
    public void volumeTest() throws Exception {
        Sound sound = new Sound();
        assertEquals(50, sound.getVolume());
        //make volume possibly equals 150 (above the maximum 100)
        sound.doubleVolume();
        sound.doubleVolume();
        assertEquals(100, sound.getVolume());
        for (int i = 0; i < 7 ; i++)
            sound.halveVolume();
        assertEquals(0, sound.getVolume());
    }

    /**
     * Asserts silence makes the note = R
     * @throws Exception when silence doesn't make note = R
     */
    @Test
    public void silence() throws Exception {
        Sound sound = new Sound();
        sound.setNote('A');
        assertEquals('A',sound.getNote());
        sound.silence();
        assertEquals('R',sound.getNote());
    }
/*  //not yet implemented
    @Test
    public void toStringTest() throws Exception{
        Sound sound = new Sound();
        System.out.println(sound.toString());

    }*/

}