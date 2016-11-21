import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pc on 10/10/2016.
 */

public class MusicStructure {
    private LinkedList<Sound> sounds;
    MusicStructure(){
        sounds = new LinkedList<Sound> ();
    };
    MusicStructure(Sound sound){
        sounds = new LinkedList<Sound> ();
        addSound(sound);
    }
    public boolean exists(){
        return !(this.sounds.isEmpty());
    };
    public void addSound(Sound sound){ //appends sound to end of the list
        this.sounds.add(sound);
    };
    public Sound[] toSoundArray(){
        return (Sound[]) this.sounds.toArray();
    };
    public LinkedList<Sound> getSounds(){
        return sounds;
    }
    public Sound popSound(){
        return this.sounds.remove(0);
    };
    public Sound getFirstSound(){
        return this.sounds.getFirst();
    }
    public Sound getLastSound() {return this.sounds.getLast();}
    public void clearSounds(){
        this.sounds.clear();
    }
    public String toString(){
        String string = "MS:";
        for(Sound sound : sounds){
            string = string+" "+sound.toString();
        }
        return string;
    }
}
