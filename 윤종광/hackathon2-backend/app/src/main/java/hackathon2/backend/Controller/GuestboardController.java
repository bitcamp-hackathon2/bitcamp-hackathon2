package hackathon2.backend.Controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

  //  @GetMapping("/guestboard/{no}")
  //  public Object getGuestboard(@PathVariable int no) {
  //
  //    Guestboard guestboard = this.guestboardDao.findByNo(no);
  //
  //    Map<String, Object> contentMap = new HashMap<>();
  //
  //    if (guestboard == null) {
  //      contentMap.put("status", "failure");
  //      contentMap.put("data", "해당 번호의 게시글이 없습니다.");
  //    } else {
  //      contentMap.put("status", "success");
  //      contentMap.put("data", guestboard);
  //    }
  //
  //    return contentMap;
  //  }

  @PutMapping("/guestboard")
  public Object updateGuestboard(String id, int no, String content) {

    Guestboard guestboard = this.guestboardDao.findByNo(no);

    Map<String, Object> contentMap = new HashMap<>();

    if (guestboard == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당 번호의 게시글이 없습니다.");
      return contentMap;
    } 

    // 좋아요 눌렀을 때
    if (id != null) {
      this.guestboardDao.handleClickLike(id, no, guestboard);

      // 수정 버튼으로 수정했을 때 
    } else {
      guestboard.setContent(content);
      this.guestboardDao.update(guestboard);

    }

    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/guestboard")
  public Object deleteGuestboard(int no) {

    Guestboard guestboard = this.guestboardDao.findByNo(no);

    Map<String, Object> contentMap = new HashMap<>();

    if (guestboard == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당 번호의 게시글이 없습니다.");
      return contentMap;
    } else {
      this.guestboardDao.delete(guestboard);
      contentMap.put("status", "success");
    }

    return contentMap;
  }

}
