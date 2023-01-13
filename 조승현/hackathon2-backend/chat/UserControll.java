package bitcamp.backend.chat;

import java.util.HashMap;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@SpringBootApplication
@RestController
public class UserControll {
  static int idx = 0;
  static String name = null;

  @PostMapping("/insert")
  public static Object insertUser(String name, String id, String password, String etc) {
    HashMap<String, Object> map = new HashMap<>();
    try {
      UserDAO.insertUser(id, password, name, etc);
      map.put("status", "success");
      System.out.println(etc);
    } catch (Exception e) {
      map.put("status", "error");
    }
    return map;
  }



  @PostMapping("/login")
  public static Object login(String id, String password) {
    HashMap<String, Object> map = new HashMap<>();
    String use = UserDAO.selectUser(id, password);
    if (use.length() > 0) {
      map.put("name", use.split(",")[0]);
      map.put("id", use.split(",")[1]);
      map.put("status", "success");
    } else {
      map.put("status", "error");
    }
    return map;
  }

  @PostMapping("/afterlogin")
  public static Object afterLogin(String id, String password) {
    HashMap<String, Object> map = new HashMap<>();
    String use = UserDAO.changeSelectUser(id, password);
    if (use.length() > 0) {
      map.put("etc", use.split(",")[0]);
      map.put("name", use.split(",")[1]);
      map.put("status", "success");
    } else {
      map.put("status", "error");
    }
    return map;
  }

  @PostMapping("/changeinfo")
  public static Object changeInfo(String id, String password, String name, String etc) {
    HashMap<String, Object> map = new HashMap<>();
    if (UserDAO.changeInfo(id, password, name, etc)) {
      map.put("status", "success");
    } else {
      map.put("status", "error");
    }
    return map;
  }

  @PostMapping("/removeuser")
  public static Object removeUser(String id, String password) {
    HashMap<String, Object> map = new HashMap<>();
    if (UserDAO.removeUser(id, password)) {
      map.put("status", "success");
    } else {
      map.put("status", "error");
    }
    return map;
  }

}
