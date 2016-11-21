/**
 * Created by Pc on 10/10/2016.
 */

public class Sound {
    private Instrument instrument;
    private char note;
    private int bpm;
    private int octave;
    private int volume;

    Sound(){
        this.setInstrument(Instrument.GUITAR);
        this.setNote('R');
        this.setBpm(120);
        this.resetOctave();
        this.setVolume(50);//supondo graduacao de 0 a 100
    }

    Sound(Sound toBeCopied){
        setInstrument(toBeCopied.getInstrument());
        setNote(toBeCopied.getNote());
        setBpm(toBeCopied.getBpm());
        setOctave(toBeCopied.getOctave());
        setVolume(toBeCopied.getVolume());
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public char getNote() {
        return note;
    }

    public void setNote(char note) {
        this.note = note;
    }

    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        if(bpm>0)
            this.bpm = bpm;
        else
            this.bpm = 60;
    }

    public int getOctave() {
        return octave;
    }

    public void setOctave(int octave) {
        if(octave>0)
            this.octave = octave;
        else
            this.octave = 1;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        if(volume>=0)
            this.volume = volume;
        else
            this.volume = 0;
    }

    public void changeInstrument(){
        switch(this.instrument){
            case GUITAR: this.setInstrument(Instrument.BASS); break;
            case BASS:   this.setInstrument(Instrument.PIANO); break;
            case PIANO:  this.setInstrument(Instrument.GUITAR); break;
        }
    }

    public void decBpm(){
        this.setBpm(this.getBpm()-50); //decreases bpm in 50
    }

    public void incBpm(){
        this.setBpm(this.getBpm()+50); //increases bpm in 50
    }

    public void resetOctave(){
        this.setOctave(5);
    }

    public void incOctave(){
        this.setOctave(this.getOctave()+1);
    }

    public void decOctave(){
        this.setOctave(this.getOctave()-1);
    }

    public void doubleVolume(){
        this.setVolume(this.getVolume()*2);
    }

    public void halveVolume(){
        this.setVolume(this.getVolume()/2);
    }

    public void silence(){
        this.setNote('R');
    }

    public String toString(){
        return "("+instrument+","+note+","+Integer.toString(bpm)+","+Integer.toString(octave)+","+Integer.toString(volume)+")";
    }
}
