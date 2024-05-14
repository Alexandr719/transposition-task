package com.task.utils;

import com.task.exeptions.PianoLimitException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TranspositionUtilTest {

    /**
     * @see TranspositionUtil#transposeNote(List, int)
     */
    @Test
    @DisplayName("Will return correct result will all octaves greater zero")
    void willReturnCorrectResultWillAllOctavesGreaterZero() {
        //given
        String input = "[" +
                "[2,1],[2,6],[2,1],[2,8],[2,1],[2,9],[2,1],[2,6],[2,1],[2,8],[2,1],[2,9],[2,1],[2,11],[2,1],[2,8],[2,1],[2,9],[2,1],[2,\n" +
                "11],[2,1],[3,1],[2,1],[2,9],[2,1],[2,11],[2,1],[3,1],[2,1],[3,2],[2,1],[2,11],[2,1],[3,1],[2,1],[2,9],[2,1],[2,11],[2,\n" +
                "1],[2,8],[2,1],[2,9],[2,1],[2,6],[2,1],[2,8],[2,1],[2,5],[2,1],[2,6],[2,1],[2,1],[2,1],[2,2],[2,1],[1,11],[2,1],[2,1],[\n" +
                "2,1],[1,9],[2,1],[1,11],[2,1],[1,8],[2,1],[1,9],[2,1],[1,6],[2,1],[1,11],[2,1],[1,8],[2,1],[1,9],[2,1],[1,6],[2,1],[1,\n" +
                "8],[2,1],[1,5],[2,1],[1,6]" +
                "]";
        List<int[]> notes = JsonArrayNotesUtils.mapJsonStringToNotesArray(input);
        List<int[]> transposedNotes = TranspositionUtil.transposeNote(notes, -3);
        String expectedResult = ("[[1,10],[2,3],[1,10],[2,5],[1,10],[2,6],[1,10],[2,3],[1,10],[2,5],[1,10],[2,6]" +
                ",[1,10],[2,8],[1,10],[2,5],[1,10],[2,\n" +
                "6],[1,10],[2,8],[1,10],[2,10],[1,10],[2,6],[1,10],[2,8],[1,10],[2,10],[1,10],[2,11],[1,10],[2,8],[1,10],[2,10],[1,\n" +
                "10],[2,6],[1,10],[2,8],[1,10],[2,5],[1,10],[2,6],[1,10],[2,3],[1,10],[2,5],[1,10],[2,2],[1,10],[2,3],[1,10],[1,10],\n" +
                "[1,10],[1,11],[1,10],[1,8],[1,10],[1,10],[1,10],[1,6],[1,10],[1,8],[1,10],[1,5],[1,10],[1,6],[1,10],[1,3],[1,10],[1\n" +
                ",8],[1,10],[1,5],[1,10],[1,6],[1,10],[1,3],[1,10],[1,5],[1,10],[1,2],[1,10],[1,3]]").replaceAll("\n", "");
        //when
        //then
        assertEquals(expectedResult, JsonArrayNotesUtils.mapNotesArrayToJsonString(transposedNotes));
    }

    /**
     * @see TranspositionUtil#transposeNote(List, int)
     */
    @Test
    @DisplayName("Will return correct result will add 3")
    void willReturnCorrectResultIfPositiveSemitones3() {
        //given
        String input = "[[1,2],[2,6],[3,10],[3,12],[0,1],[0,9],[-1,2],[-2,6],[-3,10],[-2,12],[0,9]]";
        List<int[]> notes = JsonArrayNotesUtils.mapJsonStringToNotesArray(input);
        List<int[]> transposedNotes = TranspositionUtil.transposeNote(notes, 3);
        String expectedResult = ("[[1,5],[2,9],[4,1],[4,3],[0,4],[0,12],[-1,5],[-2,9],[-2,1],[-1,3],[0,12]]");
        //when
        //then
        assertEquals(expectedResult, JsonArrayNotesUtils.mapNotesArrayToJsonString(transposedNotes));
    }

    /**
     * @see TranspositionUtil#transposeNote(List, int)
     */
    @Test
    @DisplayName("Will return correct result will add 12")
    void willReturnCorrectResultIfPositiveSemitones12() {
        //given
        String input = "[[1,2],[2,6],[3,10],[1,12],[0,1],[0,9],[-1,2],[-2,6],[-3,10],[-4,12],[0,9]]";
        List<int[]> notes = JsonArrayNotesUtils.mapJsonStringToNotesArray(input);
        List<int[]> transposedNotes = TranspositionUtil.transposeNote(notes, 12);
        String expectedResult = ("[[2,2],[3,6],[4,10],[2,12],[1,1],[1,9],[0,2],[-1,6],[-2,10],[-3,12],[1,9]]");
        //when
        //then
        assertEquals(expectedResult, JsonArrayNotesUtils.mapNotesArrayToJsonString(transposedNotes));
    }

    /**
     * @see TranspositionUtil#transposeNote(List, int)
     */
    @Test
    @DisplayName("Will return correct result will add 15")
    void willReturnCorrectResultIfPositiveSemitones15() {
        //given
        String input = "[[1,2],[2,6],[3,10],[2,12],[0,1],[0,9],[-1,2],[-2,6],[-3,10],[-4,12],[0,9]]";
        List<int[]> notes = JsonArrayNotesUtils.mapJsonStringToNotesArray(input);
        List<int[]> transposedNotes = TranspositionUtil.transposeNote(notes, 15);
        String expectedResult = ("[[2,5],[3,9],[5,1],[4,3],[1,4],[1,12],[0,5],[-1,9],[-1,1],[-2,3],[1,12]]");
        //when
        //then
        assertEquals(expectedResult, JsonArrayNotesUtils.mapNotesArrayToJsonString(transposedNotes));
    }

    /**
     * @see TranspositionUtil#transposeNote(List, int)
     */
    @Test
    @DisplayName("Will return correct result will add -3")
    void willReturnCorrectResultIfNegativeSemitones3() {
        //given
        String input = "[[1,2],[2,6],[3,10],[4,12],[0,1],[0,9],[-1,2],[-2,6],[-2,10],[-2,12],[0,9]]";
        List<int[]> notes = JsonArrayNotesUtils.mapJsonStringToNotesArray(input);
        List<int[]> transposedNotes = TranspositionUtil.transposeNote(notes, -3);
        String expectedResult = ("[[0,11],[2,3],[3,7],[4,9],[-1,10],[0,6],[-2,11],[-2,3],[-2,7],[-2,9],[0,6]]");
        //when
        //then
        assertEquals(expectedResult, JsonArrayNotesUtils.mapNotesArrayToJsonString(transposedNotes));
    }


    /**
     * @see TranspositionUtil#transposeNote(List, int)
     */
    @Test
    @DisplayName("Will return correct result will add -12")
    void willReturnCorrectResultIfNegativeSemitones12() {
        //given
        String input = "[[1,2],[2,6],[3,10],[4,12],[0,1],[0,9],[-1,2],[-1,6],[-2,10],[-2,12],[0,9]]";
        List<int[]> notes = JsonArrayNotesUtils.mapJsonStringToNotesArray(input);
        List<int[]> transposedNotes = TranspositionUtil.transposeNote(notes, -12);
        String expectedResult = ("[[0,2],[1,6],[2,10],[3,12],[-1,1],[-1,9],[-2,2],[-2,6],[-3,10],[-3,12],[-1,9]]");
        //when
        //then
        assertEquals(expectedResult, JsonArrayNotesUtils.mapNotesArrayToJsonString(transposedNotes));
    }

    /**
     * @see TranspositionUtil#transposeNote(List, int)
     */
    @Test
    @DisplayName("Will return correct result will add -15")
    void willReturnCorrectResultIfNegativeSemitones15() {
        //given
        String input = "[[1,2],[2,6],[3,10],[4,12],[0,1],[0,9],[-1,2],[-1,6],[-1,10],[-1,12],[0,9]]";
        List<int[]> notes = JsonArrayNotesUtils.mapJsonStringToNotesArray(input);
        List<int[]> transposedNotes = TranspositionUtil.transposeNote(notes, -15);
        String expectedResult = ("[[-1,11],[1,3],[2,7],[3,9],[-2,10],[-1,6],[-3,11],[-2,3],[-2,7],[-2,9],[-1,6]]");
        //when
        //then
        assertEquals(expectedResult, JsonArrayNotesUtils.mapNotesArrayToJsonString(transposedNotes));
    }

    /**
     * @see TranspositionUtil#transposeNote(List, int)
     */
    @Test
    @DisplayName("Will return correct result will add 0")
    void willReturnCorrectResultIfAdd0() {
        //given
        String input = "[[1,2],[2,6],[3,10],[4,12],[0,1],[0,9],[-1,2],[-2,6],[-3,10],[-4,12],[0,9]]";
        List<int[]> notes = JsonArrayNotesUtils.mapJsonStringToNotesArray(input);
        List<int[]> transposedNotes = TranspositionUtil.transposeNote(notes, 0);
        String expectedResult = ("[[1,2],[2,6],[3,10],[4,12],[0,1],[0,9],[-1,2],[-2,6],[-3,10],[-4,12],[0,9]]");
        //when
        //then
        assertEquals(expectedResult, JsonArrayNotesUtils.mapNotesArrayToJsonString(transposedNotes));
    }

    /**
     * @see TranspositionUtil#transposeNote(List, int)
     */
    @Test
    @DisplayName("Will return exception because resulting notes falls out of the keyboard")
    void willReturnExceptionBecauseTheResultingNotesFallsOutOfTheKeyboardRightRange() {
        //given
        String input = "[[5,1]]";
        List<int[]> notes = JsonArrayNotesUtils.mapJsonStringToNotesArray(input);
        //when
        //then
        assertThrows(PianoLimitException.class, () -> TranspositionUtil.transposeNote(notes, 12));
    }

    /**
     * @see TranspositionUtil#transposeNote(List, int)
     */
    @Test
    @DisplayName("Will return exception because resulting notes falls out of the keyboard")
    void willReturnExceptionBecauseTheResultingNotesFallsOutOfTheKeyboardLeftRange() {
        //given
        String input = "[[-2,9]]";
        List<int[]> notes = JsonArrayNotesUtils.mapJsonStringToNotesArray(input);
        //when
        //then
        assertThrows(PianoLimitException.class, () -> TranspositionUtil.transposeNote(notes, -12));
    }
}