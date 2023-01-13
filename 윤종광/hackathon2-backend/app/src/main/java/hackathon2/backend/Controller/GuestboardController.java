package hackathon2.backend.Controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import hackathon2.backend.dao.GuestboardDao;
import hackathon2.backend.vo.Guestboard;

@RestController
public class GuestboardController {

  @Autowired GuestboardDao guestboardDao;

  @PostMapping("/guestboard")
  public Object addGuestboard(Guestboard guestboard) {

    guestboard.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    this.guestboardDao.insert(guestboard);

    Map<String, Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");

    return contentMap;
  }

  @GetMapping("/guestboard")
  public Object getGuestboards(Guestboard guestboard) {

    Guestboard[] guestboards = this.guestboardDao.findAll();

    Map<String, Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", guestboards);

    return contentMap;
  }

  @GetMapping("/guestboard/{no}")
  public Object getGuestboard(@PathVariable int no) {

    Guestboard guestboard = this.guestboardDao.findByNo(no);

    Map<String, Object> contentMap = new HashMap<>();

    if (guestboard == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당 번호의 게시글이 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", guestboard);
    }

    return contentMap;
  }

  @PutMapping("/guestboard/{no}")
  public Object updateGuestboard(Guestboard guestboard) {

    Map<String, Object> contentMap = new HashMap<>();

    Guestboard old = this.guestboardDao.findByNo(guestboard.getNo());

    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당 번호의 게시글이 없습니다.");
      return contentMap;
    } else if (!old.getPassword().equals(guestboard.getPassword())) {
      contentMap.put("status", "failure");
      contentMap.put("data", "암호가 맞지 않습니다.");
      return contentMap;
    }

    this.guestboardDao.update(guestboard);

    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/guestboard/{no}")
  public Object deleteGuestboard(@PathVariable int no, String password) {

    Guestboard guestboard = this.guestboardDao.findByNo(no);

    Map<String, Object> contentMap = new HashMap<>();

    if (guestboard == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당 번호의 게시글이 없습니다.");
      return contentMap;
    } else if (!guestboard.getPassword().equals(password)) {
      contentMap.put("status", "failure");
      contentMap.put("data", "암호가 맞지 않습니다.");
      return contentMap;
    } else {
      this.guestboardDao.delete(guestboard);
      contentMap.put("status", "success");
    }

    return contentMap;
  }


}
