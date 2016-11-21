import java.awt.*;

/**
 * Created by gabri on 11/10/2016.
 */
public class Test_Sound {
    public static void main(String[] args){
        Sound sound = new Sound();

        for(int i=0;i<5;i++){
            System.out.println(sound.toString());
            sound.decBpm();
            if(i%2 == 0)sound.halveVolume();
            sound.decOctave();
        }
        for(int i=0;i<3;i++){
            System.out.println(sound.toString());
            sound.incBpm();
            if(i%2 == 0)sound.doubleVolume();
            sound.incOctave();
        }

    }
}
