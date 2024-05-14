package com.task.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.exeptions.JsonArrayNotesException;

import java.util.List;

public class JsonArrayNotesUtils {

    private JsonArrayNotesUtils() {
        //Empty
    }

    public static List<int[]> mapJsonStringToNotesArray(String str) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(str, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new JsonArrayNotesException("Got error during mapping json string to notes array");
        }
    }

    public static String mapNotesArrayToJsonString(List<int[]> notes) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(notes);
        } catch (JsonProcessingException e) {
            throw new JsonArrayNotesException("Got error during mapping notes array to json string");
        }
    }
}
