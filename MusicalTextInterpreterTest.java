/**
 * Created by thoma on 21/11/2016.
 */
public class MusicalTextInterpreterTest {
    @org.junit.Test
    public void interpret() throws Exception {
        String text = "AiBOBoBuBUD!B?GF;CD ?,A";
        MusicalTextInterpreter mi = new MusicalTextInterpreter(text);
        MusicStructure music = mi.interpret();
        for(Sound sound : music.getSounds()){
            System.out.print(sound.getNote());
            System.out.print(sound.getVolume());
            System.out.print(sound.getOctave());
            System.out.println(sound.getInstrument().getMidiValue());
        }
    }
}