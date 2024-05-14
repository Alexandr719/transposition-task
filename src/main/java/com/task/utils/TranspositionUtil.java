package com.task.utils;

import com.task.constants.PianoConstants;
import com.task.exeptions.PianoLimitException;

import java.util.ArrayList;
import java.util.List;

public class TranspositionUtil {

    private static final int MIN_SUM_VALUE = PianoConstants.MIN_OCTAVE_NUMBER * PianoConstants.OCTAVE_SIZE
            + PianoConstants.MIN_NOTE_NUMBER;

    private static final int MAX_SUM_VALUE = PianoConstants.MAX_OCTAVE_NUMBER * PianoConstants.OCTAVE_SIZE
            + PianoConstants.MAX_NOTE_NUMBER;

    private TranspositionUtil() {
        //Empty
    }

    public static List<int[]> transposeNote(List<int[]> notes, int numberOfSemitones) {

        if (numberOfSemitones == 0) {
            return notes;
        }

        ArrayList<int[]> result = new ArrayList<>();
        for (int[] note : notes) {
            int octaveNumber = note[0];
            int noteNumber = note[1];
            int newNoteNumber = noteNumber + numberOfSemitones;
            int sum;
            if (newNoteNumber > 0) {
                newNoteNumber = newNoteNumber % PianoConstants.OCTAVE_SIZE == 0 ? PianoConstants.OCTAVE_SIZE :
                        newNoteNumber % PianoConstants.OCTAVE_SIZE;
                sum = PianoConstants.OCTAVE_SIZE * octaveNumber + noteNumber + numberOfSemitones - newNoteNumber;
            } else {
                sum = PianoConstants.OCTAVE_SIZE * octaveNumber - PianoConstants.OCTAVE_SIZE;
                if (Math.abs(newNoteNumber / PianoConstants.OCTAVE_SIZE) > 0) {
                    sum += (newNoteNumber / PianoConstants.OCTAVE_SIZE) * PianoConstants.OCTAVE_SIZE;
                    newNoteNumber = PianoConstants.OCTAVE_SIZE + newNoteNumber % PianoConstants.OCTAVE_SIZE;
                } else {
                    newNoteNumber = PianoConstants.OCTAVE_SIZE + newNoteNumber;
                }
            }
            int newOctaveNumber = sum / PianoConstants.OCTAVE_SIZE;
            int[] newNote = {newOctaveNumber, newNoteNumber};

            checkFallsOut(newNoteNumber, newOctaveNumber, newNote);
            result.add(newNote);

        }
        return result;
    }

    private static void checkFallsOut(int newNoteNumber, int newOctaveNumber, int[] newNote) {
        //Check Falls Out Of The Keyboard
        if (newOctaveNumber < 0 && newOctaveNumber * 12 + newNoteNumber < MIN_SUM_VALUE) {
            throw new PianoLimitException(newNote[0] + " " + newNote[1]);
        }
        if (newOctaveNumber > 0 && newOctaveNumber * 12 + newNoteNumber > MAX_SUM_VALUE) {
            throw new PianoLimitException(newNote[0] + " " + newNote[1]);
        }
    }
}
