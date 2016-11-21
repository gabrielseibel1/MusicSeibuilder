/**
 * Created by gabri on 11/10/2016.
 */


public class Test_MusicalTextInterpreter {
    public static void main(String[] args){
        MusicalTextInterpreterOld interpreter = new MusicalTextInterpreterOld("abcdefghijklmnopqrstuvwxyz !0246813579?.\n;,*");
        interpreter.interpretate();
        System.out.println(interpreter.toString());

        //abcdefghijklmnopqrstuvwxyz !0246813579?.\n;,*
    }


}
