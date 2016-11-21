/**
 * Created by Pc on 10/10/2016.
 */
public class MusicalTextInterpreter {
    private String text;
    private MusicStructure music;

    MusicalTextInterpreter(String text) {
        this.setText(text);
    }

    public MusicStructure interpretate(){
        music = new MusicStructure();
        Sound lastSound = new Sound();
        char lastNote = 'R';
        for(int i = 0; i<text.length(); i++){
            Sound currentSound = new Sound(lastSound);
            char note = Character.toUpperCase(text.charAt(i));

            switch(note){
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':   currentSound.setNote(note); break;//regular notes

                case ' ':   currentSound.silence(); break;

                case '!':   currentSound.doubleVolume(); break; //doubles volume

                case 'I':
                case 'O':
                case 'U':   currentSound.halveVolume(); break; //halves volume

                case 'H':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':   if('A' <= lastNote && lastNote <= 'G'){ //if last note was regular
                                currentSound.setNote(lastNote); //takes it
                            }else currentSound.silence(); //else silences
                            break;

                case '0':
                case '2':
                case '4':
                case '6':
                case '8':   currentSound.decOctave(); break;

                case '1':
                case '3':
                case '5':
                case '7':
                case '9':   currentSound.incOctave(); break;

                case '?':
                case '.':   currentSound.resetOctave(); break;

                case '\n':  currentSound.changeInstrument(); break;

                case ';':   currentSound.incBpm(); break;

                case ',':   currentSound.decBpm(); break;

                default :   currentSound = lastSound; break; //no operation
            }

            Sound dummySound = new Sound(currentSound);

            music.addSound(dummySound);
            lastSound = currentSound;
            lastNote = note;
        }
        return music;
    };

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MusicStructure getMusic() {
        return music;
    }

    public void setMusic(MusicStructure music) {
        this.music = music;
    }

    public String toString(){
        return music.toString();
    }
}
