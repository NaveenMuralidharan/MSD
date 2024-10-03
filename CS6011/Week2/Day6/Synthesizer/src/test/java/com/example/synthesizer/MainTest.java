package com.example.synthesizer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    public void testGetAndSet(){
        byte[] testBytes = {-10,20,30,45,56,67};
        AudioClip audioClip = new AudioClip(testBytes);
        audioClip.setSample(0,-32768);
        assertEquals(audioClip.getSample(0), -32768);
        audioClip.setSample(0,32767);
        assertEquals(audioClip.getSample(0), 32767);

    }

}