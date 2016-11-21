/**
 * Created by Pc on 10/10/2016.
 */
public enum Instrument {
    GUITAR("Guitar"),BASS("Bass"), PIANO("Piano");

    private String name;
    private Instrument(String name){
        this.setName(name);
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

