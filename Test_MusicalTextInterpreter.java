/**
 * Created by gabri on 11/10/2016.
 */


public class Test_MusicalTextInterpreter {
    public static void main(String[] args){
        MusicalTextInterpreter interpreter = new MusicalTextInterpreter("!!FABFAB\nFABFAB");
        interpreter.interpretate();
        System.out.println(interpreter.toString());
        JFuguePlayer player = new JFuguePlayer(interpreter.getMusic());
        player.play();

        //abcdefghijklmnopqrstuvwxyz !0246813579?.\n;,*
    }


}
