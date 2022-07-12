package com.eazyrooms.staff.chat.model;

public class ChatModel {

    public int ChatImage;
    public String ChatName;
    public String ChatMsg;
    public String ChatRoomNumber;
    public String ChatTime;

    public ChatModel(int chatImage, String chatName, String chatMsg, String chatRoomNumber, String chatTime) {
        ChatImage = chatImage;
        ChatName = chatName;
        ChatMsg = chatMsg;
        ChatRoomNumber = chatRoomNumber;
        ChatTime = chatTime;
    }

    public int getChatImage() {
        return ChatImage;
    }

    public void setChatImage(int chatImage) {
        ChatImage = chatImage;
    }

    public String getChatName() {
        return ChatName;
    }

    public void setChatName(String chatName) {
        ChatName = chatName;
    }

    public String getChatMsg() {
        return ChatMsg;
    }

    public void setChatMsg(String chatMsg) {
        ChatMsg = chatMsg;
    }

    public String getChatRoomNumber() {
        return ChatRoomNumber;
    }

    public void setChatRoomNumber(String chatRoomNumber) {
        ChatRoomNumber = chatRoomNumber;
    }

    public String getChatTime() {
        return ChatTime;
    }

    public void setChatTime(String chatTime) {
        ChatTime = chatTime;
    }

}
