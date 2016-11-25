import java.util.LinkedList;
import java.util.List;

/**
 * Class that describes the structure of a song, composed of Sound objects
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

    /**
     * Checks if a structure exists or not (is empty, in this case)
     * @return true if there are one ore more sounds on the structure
     */
    public boolean exists(){
        return !(this.sounds.isEmpty());
    };

    /**
     * Appends a Sound to the end of the structure
     * @param sound the sound to be added
     */
    public void addSound(Sound sound){ //appends sound to end of the list
        this.sounds.add(sound);
    };

    /**
     * Converts MusicStructure to an array of Sound objects
     * @return the array of sounds that compose the structure
     */
    public Sound[] toSoundArray(){
        return (Sound[]) this.sounds.toArray();
    };

    /**
     * Converts MusicStructure to a linked list of Sound objects
     * @return the linked list of sounds that compose the structure
     */
    public LinkedList<Sound> getSounds(){
        return sounds;
    }

    /**
     * Removes the first sound of the music structure, which is the return object of this method
     * @return the first sound of the structure
     */
    public Sound popSound(){
        return this.sounds.remove(0);
    };

    /**
     * Returns the first sound of the music structure, without removing it
     * @return the first sound of the structure
     */
    public Sound getFirstSound(){
        return this.sounds.getFirst();
    }

    /**
     * Returns the last sound of the music structure, without removing it
     * @return the last sound of the structure
     */
    public Sound getLastSound() {return this.sounds.getLast();}

    /**
     * Erases all the sounds of the music structure,
     * leaving it empty implies exists() = false
     */
    public void clearSounds(){
        this.sounds.clear();
    }

    /**
     * Converts the music structure to a string that is reading-friendly
     * @return a reading-friendly string
     */
    public String toString(){
        String string = "MS:";
        for(Sound sound : sounds){
            string = string+" "+sound.toString();
        }
        return string;
    }
}
