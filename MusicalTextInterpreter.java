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
     * Current instrument read from text. Sets the Pan Flute as the default instrument.
     *
     */
    private Instrument currentInstrument = new Instrument(Instrument.PAN_FLUTE);



    /**
     * Creates musical interpreter for converting given text in MusicStructure.
     * @param text string storing text to be interpreted and converted to a music structure.
     */
    public MusicalTextInterpreter(String text){
        this.setText(text);
    }


    /**
     * Reads text stored in text attribute from class and converts it to MusicStructure.
     * @return returns MusicStructure containing interpretation from given text.
     */
    MusicStructure interpret(){
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
     * Class describing char functionality.
     */
    private class InterpretedChar {

        /**
         * Stores char read from text in main class MusicalTextInterpreter
         */
        private char inputChar;


        /**
         * Stores last char read, before the input char, from text in main class MusicalTextInterpreter
         */
        private char lastChar;

        /**
         * Constructs instance of InterpretedChar to be used in MusicalTextInterpreter
         * @param inputChar currently char read from the text.
         * @param lastChar char that was read before inputChar. This information is important because the inputChar
         *                 may have different meanings depending on what was read before.
         */
        InterpretedChar(char inputChar, char lastChar){
            this.setInputChar(inputChar);
            this.setLastChar(lastChar);
        }

        /**
         * Modifies given MusicStructure and the text interpretation current values according to the chars that were stored in the class attributes
         * inputChar and lastChar.
         * @param musicStructure instance of MusicStructure that will be modified.
         */
        void applyChangeTo(MusicStructure musicStructure){
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
                    Sound pause;
                    pause = musicStructure.getLastSound();
                    pause.setNote('R');
                    musicStructure.addSound(pause);
                }
            }
        }


        /**
         * Checks if char represents a digit.
         * @param inputChar variable containing char to be said whether is a number or not.
         * @return returns true if inputChar is a number and false otherwise.
         */
        private boolean isNumber(char inputChar){
            return inputChar == '0' || inputChar == '1' || inputChar == '2' || inputChar == '3' ||inputChar == '4' || inputChar == '5' ||
                    inputChar == '6' || inputChar == '7' || inputChar == '8' || inputChar == '9';
        }


        /**
         * Checks if char represents a change in instrument, according to the specification.
         * @param inputChar variable containing char to be said whether is an instrument change or not.
         * @return returns true if inputChar is an instrument change and false otherwise.
         */
        private boolean isInstrumentChange(char inputChar){
            return inputChar == '!' || inputChar == '\n' || inputChar == ';' || inputChar == ',' || isNumber(inputChar);
        }

        /**
         * Checks if char represents a change in volume, according to the specification.
         * @param inputChar variable containing char to be said whether is a volume change or not.
         * @return returns true if inputChar is a volume change and false otherwise.
         */
        private boolean isVolumeChange(char inputChar){
            return inputChar == ' ' || inputChar == 'o' || inputChar == 'O' || inputChar == 'u' || inputChar == 'U' || inputChar == 'i'
                    || inputChar == 'I';
        }

        /**
         * Checks if char represents a change in octave, according to the specification.
         * @param inputChar variable containing char to be said whether is an octave change or not.
         * @return returns true if inputChar is an octave change and false otherwise.
         */
        private boolean isOctaveChange(char inputChar){
            return inputChar == '?' || inputChar == '.';
        }

        /**
         * Checks if char represents a pure note, according to the specification.
         * @param inputChar variable containing char to be said whether is a pure note or not.
         * @return returns true if inputChar is a pure note and false otherwise.
         */
        private boolean isPureNote(char inputChar){
            return inputChar == 'A' || inputChar == 'B' || inputChar == 'C' || inputChar == 'D' || inputChar == 'E' || inputChar == 'F'
                    || inputChar == 'G';
        }

        /**
         * Checks if char represents a change in the current values of the text interpretation, according to the specification.
         * @param inputChar variable containing char to be said whether is a technical change or not.
         * @return returns true if inputChar is a technical change and false otherwise.
         */
        private boolean isTechnicalChange(char inputChar) {
            return isInstrumentChange(inputChar)  || isOctaveChange(inputChar) || isVolumeChange(inputChar);
        }


        char getInputChar() {
            return inputChar;
        }

        void setInputChar(char inputChar) {
            this.inputChar = inputChar;
        }

        char getLastChar() {
            return lastChar;
        }

        void setLastChar(char lastChar) {
            this.lastChar = lastChar;
        }
    }

    private int getCurrentOctave() {
        return currentOctave;
    }

    private void setCurrentOctave(int currentOctave) {
        this.currentOctave = currentOctave;
    }

    private int getCurrentVolume() {
        return currentVolume;
    }

    private void setCurrentVolume(int currentVolume) {
        this.currentVolume = currentVolume;
    }

    private Instrument getCurrentInstrument() {
        return currentInstrument;
    }

    private void setCurrentInstrument(Instrument currentInstrument) {
        this.currentInstrument = currentInstrument;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
