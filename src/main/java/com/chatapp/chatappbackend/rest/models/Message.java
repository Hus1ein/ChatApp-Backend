package com.chatapp.chatappbackend.rest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    private String id;
    private String content;
    private String sentBy;
    private String chat;
    private Date sentAt;
    private List<Reaction> reactions;
    private List<String> seenBy;

    public static String listToString(List<String> list) {
        return list.toString();
    }

    public static List<String> stringToList(String string) {
        string = string.replace("[", "");
        string = string.replace("]", "");
        String[] array = string.split(",");
        return Arrays.asList(array.length > 0 && Objects.equals(array[0], "") ? new String[0] : array);
    }

}
