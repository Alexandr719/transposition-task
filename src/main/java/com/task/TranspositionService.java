package com.task;

import com.task.utils.FileHelperUtils;
import com.task.utils.JsonArrayNotesUtils;
import com.task.utils.TranspositionUtil;

import java.util.List;

public class TranspositionService {

    public static final String INPUT_FILE = "input.json";
    public static final String OUTPUT_FILE = "src/main/resources/output.json";

    public static void main(String[] args) {

        //String input = FileHelperUtils.readFileBySysPath("/Users/alexandrfilatov/Desktop/input.json");

        String input = FileHelperUtils.readFromResourcesByName(INPUT_FILE);
        List<int[]> inputData = JsonArrayNotesUtils.mapJsonStringToNotesArray(input);
        List<int[]> outputData = TranspositionUtil.transposeNote(inputData, -3);
        FileHelperUtils.writeFile(OUTPUT_FILE, JsonArrayNotesUtils.mapNotesArrayToJsonString(outputData));
    }

}
