/**
 * Created by Arthu on 21/11/2016.
 */

import java.util.Scanner;
import javax.sound.midi.MidiUnavailableException;
import org.jfugue.player.Player;
import org.jfugue.rhythm.Rhythm;
import org.jfugue.pattern.Pattern;
import org.jfugue.theory.Chord;
import org.jfugue.theory.ChordProgression;
import org.jfugue.theory.Note;
import java.util.Map;
import java.util.*;
import org.staccato.ReplacementMapPreprocessor;


import java.util.Random;

import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.theory.Note;

public class Test_JFugue {
    public static void main(String[] args) {
        Player player = new Player();
        Pattern p1 = new Pattern();
        player.play(p1);
    }
}
