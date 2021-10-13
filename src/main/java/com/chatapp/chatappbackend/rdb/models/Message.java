package com.chatapp.chatappbackend.rdb.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    private String id;
    private String content;
    private User sentBy;
    private Chat chat;
    private Date sentAt;
    private List<Reaction> reactions;
    private List<String> seenBy;

    public static String listToString(List<String> list) {
        return list.toString();
    }

    public static List<String> stringToList(String string) {
        string = string.replace("[", "");
        string = string.replace("]", "");
        return Arrays.asList(string.split(","));
    }

}
