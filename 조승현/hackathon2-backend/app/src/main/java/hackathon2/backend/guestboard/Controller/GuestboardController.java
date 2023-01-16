package hackathon2.backend.guestboard.Controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import hackathon2.backend.guestboard.dao.GuestboardDao;
import hackathon2.backend.guestboard.vo.Guestboard;

@RestController
public class GuestboardController {

  // 초기 데이터 생성을 위한 변수
  boolean isFirstLoad = true;

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

    // 초기 데이터 5개 생성
    if (isFirstLoad) {
      Guestboard g1 = new Guestboard();
      g1.setId("admin1");
      g1.setName("광이");
      g1.setContent("우와아아!! 일빠!!");
      this.guestboardDao.insert(g1);

      Guestboard g2 = new Guestboard();
      g2.setId("admin2");
      g2.setName("지윤");
      g2.setContent("포기하면 그 순간 지는거다");
      this.guestboardDao.insert(g2);

      Guestboard g3 = new Guestboard();
      g3.setId("admin3");
      g3.setName("준호");
      g3.setContent("하하하");
      this.guestboardDao.insert(g3);

      Guestboard g4 = new Guestboard();
      g4.setId("admin4");
      g4.setName("솔");
      g4.setContent("엥?");
      this.guestboardDao.insert(g4);

      Guestboard g5 = new Guestboard();
      g5.setId("admin5");
      g5.setName("승현");
      g5.setContent("난 포기를 모른다네");
      this.guestboardDao.insert(g5);

      isFirstLoad = false;
    }

    Guestboard[] guestboards = this.guestboardDao.findAll();

    Map<String, Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", guestboards);

    return contentMap;
  }

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
