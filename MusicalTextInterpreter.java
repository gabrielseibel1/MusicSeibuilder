/**
 * Interprets input text.
 */
public class MusicalTextInterpreter {

    /**
     * Stores maximum octave supported.
     */
    private static final int maxOctave = 9;



    /**
     * String where input text to be interpreted is stored.
     */
    private String text;


    /**
     * Musical structure containing result from input text interpretation
     */
    private MusicStructure interpretedMusic = new MusicStructure();

    /**
     * Current octave read from text.
     */
    private int currentOctave = 5;


    /**
     * Current volume read from text.
     */
    private int currentVolume = 50;


    /**
     * Current instrument read from text.
     */
    private Instrument currentInstrument = new Instrument(Instrument.PAN_FLUTE);

    /**
     * Creates musical interpreter for converting given text in MusicStructure.
     * @param text string storing text to be interpreted.
     */
    public MusicalTextInterpreter(String text){
        this.setText(text);
    }


    /**
     * Reads text and converts it to MusicStructure.
     */
    public MusicStructure interpret(){
        int textLength = text.length();
        char lastChar = 'R';
        char currentChar;
        for(int textPosition = 0; textPosition < textLength; textPosition++){
            currentChar = text.charAt(textPosition);
            InterpretedChar currentInterpretation = new InterpretedChar(currentChar, lastChar);
            currentInterpretation.applyChangeTo(interpretedMusic);
            lastChar = currentChar;
        }

        return interpretedMusic;
    }

    /**
     * Super class describing char functionality.
     */
    private class InterpretedChar {

        private char inputChar;

        private char lastChar;

        public InterpretedChar(char inputChar, char lastChar){
            this.setInputChar(inputChar);
            this.setLastChar(lastChar);
        }

        public void applyChangeTo(MusicStructure musicStructure){
            char inputChar = this.getInputChar();
            char lastChar = this.getLastChar();

            if(isPureNote(inputChar)){
                Sound newSound = new Sound();
                newSound.setNote(inputChar);
                newSound.setVolume(getCurrentVolume());
                newSound.setOctave(getCurrentOctave());
                newSound.setInstrument(getCurrentInstrument());
                musicStructure.addSound(newSound);

            }else if(isTechnicalChange(inputChar)){
                if(isOctaveChange(inputChar) && getCurrentOctave() < maxOctave){
                    setCurrentOctave(getCurrentOctave()+1);
                }else if(isVolumeChange(inputChar)){
                    int currentVolume = getCurrentVolume();
                    if(inputChar == ' ') setCurrentVolume(2*currentVolume);
                    else setCurrentVolume(currentVolume/10 + currentVolume);
                }else if(isInstrumentChange(inputChar)){
                    if(inputChar == '!') setCurrentInstrument(new Instrument(Instrument.HARPSICHORD));
                    else if(inputChar == '\n') setCurrentInstrument(new Instrument(Instrument.TUBULAR_BELLS));
                    else if(inputChar == ';') setCurrentInstrument(new Instrument(Instrument.PAN_FLUTE));
                    else if(inputChar == ',') setCurrentInstrument(new Instrument(Instrument.CHURCH_ORGAN));
                    else if(isNumber(inputChar)){
                        int midiInstrumentDiff = Integer.valueOf(Character.toString(inputChar));
                        Instrument newInstrument = new Instrument(getCurrentInstrument().getMidiValue() + midiInstrumentDiff);
                        setCurrentInstrument(newInstrument); // instrument should be number
                    }
                }
            }else{
                if(isPureNote(lastChar)){
                    Sound repeatedSound = musicStructure.getLastSound();
                    musicStructure.addSound(repeatedSound);
                }else{
                    Sound pause = new Sound();
                    pause = musicStructure.getLastSound();
                    pause.setNote('R');
                    musicStructure.addSound(pause);
                }
            }
        }

        private boolean isNumber(char inputChar){
            return inputChar == '0' || inputChar == '1' || inputChar == '2' || inputChar == '3' ||inputChar == '4' || inputChar == '5' ||
                    inputChar == '6' || inputChar == '7' || inputChar == '8' || inputChar == '9';
        }

        private boolean isInstrumentChange(char inputChar){
            return inputChar == '!' || inputChar == '\n' || inputChar == ';' || inputChar == ',' || isNumber(inputChar);
        }

        private boolean isVolumeChange(char inputChar){
            return inputChar == ' ' || inputChar == 'o' || inputChar == 'O' || inputChar == 'u' || inputChar == 'U' || inputChar == 'i'
                    || inputChar == 'I';
        }

        private boolean isOctaveChange(char inputChar){
            return inputChar == '?' || inputChar == '.';
        }


        private boolean isPureNote(char inputChar){
            return inputChar == 'A' || inputChar == 'B' || inputChar == 'C' || inputChar == 'D' || inputChar == 'E' || inputChar == 'F'
                    || inputChar == 'G';
        }

        private boolean isTechnicalChange(char inputChar) {
            return isInstrumentChange(inputChar)  || isOctaveChange(inputChar) || isVolumeChange(inputChar);
        }

        public char getInputChar() {
            return inputChar;
        }

        public void setInputChar(char inputChar) {
            this.inputChar = inputChar;
        }

        public char getLastChar() {
            return lastChar;
        }

        public void setLastChar(char lastChar) {
            this.lastChar = lastChar;
        }
    }

    public int getCurrentOctave() {
        return currentOctave;
    }

    public void setCurrentOctave(int currentOctave) {
        this.currentOctave = currentOctave;
    }

    public int getCurrentVolume() {
        return currentVolume;
    }

    public void setCurrentVolume(int currentVolume) {
        this.currentVolume = currentVolume;
    }

    public Instrument getCurrentInstrument() {
        return currentInstrument;
    }

    public void setCurrentInstrument(Instrument currentInstrument) {
        this.currentInstrument = currentInstrument;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
