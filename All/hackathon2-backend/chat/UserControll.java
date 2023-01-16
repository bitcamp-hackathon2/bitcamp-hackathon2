package bitcamp.backend.chat;

import java.util.HashMap;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@SpringBootApplication
@RestController
public class UserControll {
  static int idx = 0;
  static String name = null;
  {
    UserDAO.users.add(new User("admin1", "admin1234", "윤종광", "관리자 계정"));
    UserDAO.users.add(new User("admin2", "admin1234", "신지윤", "관리자 계정"));
    UserDAO.users.add(new User("admin3", "admin1234", "조준호", "관리자 계정"));
    UserDAO.users.add(new User("admin4", "admin1234", "윤솔", "관리자 계정"));
    UserDAO.users.add(new User("admin5", "admin1234", "조승현", "관리자 계정"));
  }

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

  @PostMapping("/checkid")
  public static Object checkId(String id) {
    HashMap<String, Object> map = new HashMap<>();
    if (UserDAO.checkId(id)) {
      map.put("status", "success");
    } else {
      map.put("status", "error");
    }
    return map;
  }

  @PostMapping("/checkname")
  public static Object checkname(String name) {
    HashMap<String, Object> map = new HashMap<>();
    if (UserDAO.checkName(name)) {
      map.put("status", "success");
    } else {
      map.put("status", "error");
    }
    return map;
  }

  @GetMapping("/userlist")
  public static Object userList() {

    HashMap<String, Object> map = new HashMap<>();
    if (UserDAO.users.size() > 0) {
      map.put("status", "success");
      map.put("userlist", UserDAO.users);
    } else {
      map.put("status", "error");
    }
    return map;
  }
}
