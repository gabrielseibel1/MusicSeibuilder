/**
 * Created by gabri on 11/10/2016.
 */


public class Test_MusicalTextInterpreter {
    public static void main(String[] args){
        MusicalTextInterpreter interpreter = new MusicalTextInterpreter("abcdefghijklmnopqrstuvwxyz !0246813579?.\n;,*");
        interpreter.interpret();
        System.out.println(interpreter.toString());

        //abcdefghijklmnopqrstuvwxyz !0246813579?.\n;,*
    }


}
