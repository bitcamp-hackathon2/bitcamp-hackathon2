package bitcamp.backend.chat;

import java.util.ArrayList;

public class ChatControll {
  public static ArrayList<String> liveChat = new ArrayList<>();
  static int idx = 0;

  public static void chatAdd(String str) {
    liveChat.add((idx++) + "," + str);
  }

  public static Object printChat() {
    return liveChat;
  }
}
