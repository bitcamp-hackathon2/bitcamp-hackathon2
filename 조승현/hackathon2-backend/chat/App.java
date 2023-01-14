package bitcamp.backend.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@SpringBootApplication
@RestController
public class App {
  static int count = 0;

  public static void main(String args[]) {
    SpringApplication.run(App.class, args);
  }

  @GetMapping("/view")
  public Object view() {
    return ChatControll.liveChat;
  }

  @GetMapping("/add")
  public void add(String content, String user) {
    for (int i = 0; i < UserDAO.users.size(); i++) {
      if (UserDAO.users.get(i).getId().equals(user.split(",")[1])) {
        ChatControll.chatAdd(UserDAO.users.get(i).getName() + ": " + content);
      }
    }
  }
}
