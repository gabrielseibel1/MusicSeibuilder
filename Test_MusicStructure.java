/**
 * Created by gabri on 11/10/2016.
 */
public class Test_MusicStructure {
    public static void main(String[] args){
        MusicStructure musicStructure = new MusicStructure();
        Sound sound = new Sound();
        System.out.println("*-*-* Sounds Insertion *-*-* ");
        for(int i=0;i<7;i++){
            System.out.println(musicStructure.toString());

            Sound dummySound = new Sound(sound);
            sound.setNote('C');
            musicStructure.addSound(dummySound);
            sound.decOctave();
        }
        JFuguePlayer player = new JFuguePlayer(musicStructure);
        player.play();
        /*System.out.println("\n\n*-*-* Sounds Poppage *-*-* ");
        for(int i=0;i<7;i++){
            sound = musicStructure.popSound();
            System.out.println("\n[Popped "+sound.toString()+"]");
            System.out.println(musicStructure.toString());
        }*/
    }

}
