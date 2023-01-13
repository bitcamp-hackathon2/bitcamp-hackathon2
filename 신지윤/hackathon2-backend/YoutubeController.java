package bitcamp.bootapp.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.bootapp.dao.YoutubeDao;
import bitcamp.bootapp.vo.Youtube;

@RestController	// JSON

public class YoutubeController {

  @Autowired YoutubeDao youtubeDao;	// 인스턴스 필드에만  오토와일드 가능

  // Youtube 게시물 생성
  @PostMapping("/youtubes")
  public Object addYoutube(Youtube youtube) {

    youtube.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    this.youtubeDao.insert(youtube);

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");

    return contentMap;
  }

  // 모든 Youtube 게시물 정보 가져오기
  @GetMapping("/youtubes")
  public Object getYoutubes() {

    Youtube[] youtubes = this.youtubeDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", youtubes);

    return contentMap;
  }

  // 특정 Youtube 게시물 정보 가져오기
  @GetMapping("/youtubes/{no}")
  public Object getYoutube(@PathVariable int no) {

    Youtube y = this.youtubeDao.findByNo(no);

    Map<String,Object> contentMap = new HashMap<>();

    if (y == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "검색어가 포함된 게시물이 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", y);
    }

    return contentMap;
  }
 
  @PutMapping("/youtubes/{no}")
  public Object updateYoutube(Youtube youtube) {

    Map<String,Object> contentMap = new HashMap<>();

    Youtube old = this.youtubeDao.findByNo(youtube.getNo());
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "검색어가 포함된 게시물이 없습니다.");
      return contentMap;
    }

    youtube.setCreatedDate(old.getCreatedDate());

    this.youtubeDao.update(youtube);

    contentMap.put("status", "success");

    return contentMap;
  }
  
  // Youtube 게시물 삭제하기
  @DeleteMapping("/youtubes/{no}")
  public Object deleteYoutube(
		  @PathVariable int no,
		  @RequestParam(required = false) String password) {

    Youtube y = this.youtubeDao.findByNo(no);

    Map<String,Object> contentMap = new HashMap<>();

    if (y == null) {	// 삭제 시 글쓴이 확인 기능 확인하기
      contentMap.put("status", "failure");
      contentMap.put("data", "게시물이 없습니다.");

    } else {
      this.youtubeDao.delete(y);
      contentMap.put("status", "success");
    }

    return contentMap;
  }

}
