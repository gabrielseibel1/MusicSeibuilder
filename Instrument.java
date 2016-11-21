
public final class Instrument {

    /**
     * Most used instruments get their own constant associated
     */
    public static final int HARPSICHORD = 7;
    public static final int TUBULAR_BELLS = 15;
    public static final int PAN_FLUTE = 76;
    public static final int CHURCH_ORGAN = 20;

    private int midiValue;

    public Instrument(int midiValue){
        this.setMidiValue(midiValue);
    }

    public int getMidiValue() {
        return midiValue;
    }

    public void setMidiValue(int midiValue) {
        this.midiValue = midiValue;
    }
}

