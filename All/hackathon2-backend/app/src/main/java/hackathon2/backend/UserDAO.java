package bitcamp.backend.chat;

import java.util.ArrayList;

public class UserDAO {
  static ArrayList<User> users = new ArrayList<>();

  public static void insertUser(String id, String password, String name, String etc) {
    users.add(new User(id, password, name, etc));
  }

  public static String selectUser(String id, String password) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getId().equals(id) && users.get(i).getPassword().equals(password)) {
        return users.get(i).getName() + "," + users.get(i).getId();
      }
    }
    return "";
  }

  public static String changeSelectUser(String id, String password) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getId().equals(id) && users.get(i).getPassword().equals(password)) {
        return users.get(i).getEtc() + "," + users.get(i).getName();
      }
    }
    return "";
  }

  public static boolean changeInfo(String id, String password, String name, String etc) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getId().equals(id) && users.get(i).getPassword().equals(password)) {
        if (users.get(i).getName().equals(name)) {
          users.get(i).setEtc(etc);
          return true;
        }
        for (int a = 0; a < users.size(); a++) {
          if (users.get(a).getName().equals(name)) {
            return false;
          }
        }
        users.get(i).setName(name);
        users.get(i).setEtc(etc);
        return true;
      }
    }
    return false;
  }

  public static boolean removeUser(String id, String password) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getId().equals(id) && users.get(i).getPassword().equals(password)) {
        users.remove(users.get(i));
        return true;
      }
    }
    return false;
  }

  public static boolean checkId(String id) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getId().equals(id)) {
        return false;
      }
    }
    return true;
  }

  public static boolean checkName(String name) {
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getName().equals(name)) {
        return false;
      }
    }
    return true;
  }

  public static Object userList() {
    return users;
  }
}
