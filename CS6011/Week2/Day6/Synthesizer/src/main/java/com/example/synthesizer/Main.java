package com.example.synthesizer;
import javax.sound.sampled.*;

public class Main {
    public static void main(String[] args) throws LineUnavailableException {

        Clip c = AudioSystem.getClip();
        AudioFormat format16 = new AudioFormat( 44100, 16, 1, true, false );

//        AudioComponent gen = new SineWave(440); // Your code
//        AudioClip clip = gen.getClip();

        AudioComponent gen1 = new SineWave(440); // Your code
        AudioComponent gen2 = new SineWave(493.883);
        AudioComponent gen3 = new SquareWave(440);
        AudioComponent gen4 = new WhiteNoise();
//        AudioComponent volumeAdjuster = new VolumeAdjuster(0.1);
//        volumeAdjuster.connectInput(gen);
        AudioComponent mixer = new Mixers();
        mixer.connectInput(gen1);
        mixer.connectInput(gen2);
        AudioClip clip = gen4.getClip();

        c.open( format16, clip.getData(), 0, clip.getData().length );
        System.out.println( "About to play..." );
        c.start(); // Plays it.
        c.loop( 2 );

        while( c.getFramePosition() < AudioClip.sampleRate || c.isActive() || c.isRunning() ){
            // Do nothing while we wait for the note to play.
        }

        System.out.println( "Done." );
        c.close();

    }
}
