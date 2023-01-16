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
  public Object addYoutube(
		  @RequestParam(required = false) String title,
	      @RequestParam(required = false) String link,
	      @RequestParam(required = false) String thumbnail,
	      @RequestParam(required = false) String id) {

	  Youtube y = new Youtube();
	    
	    y.setTitle("[모아보기] 웬만한 악과 깡으로는 살아남기 힘들었던 90년대 레전드 영상｜크랩");
	    y.setLink("https://www.youtube.com/watch?v=chI0nkP07-M");
	    y.setThumbnail("https://i.ytimg.com/vi/chI0nkP07-M/maxresdefault.jpg");
	    y.setId("지윤");
	    y.setCreatedDate(new Date(System.currentTimeMillis()).toString());
	
	    this.youtubeDao.insert(y);
	    
	    Youtube o = new Youtube();
	    
	    o.setTitle("개마저 떡실신시킨 80년대 여름");
	    o.setLink("https://www.youtube.com/watch?v=bv6euol2IYk");
	    o.setThumbnail("https://i.ytimg.com/vi/bv6euol2IYk/maxresdefault.jpg");
	    o.setId("지윤");
	    o.setCreatedDate(new Date(System.currentTimeMillis()).toString());
	
	    this.youtubeDao.insert(o);
	    
	    Youtube u = new Youtube();
	    
	    u.setTitle("서울-대전 10시간;; 고속도로에서 밥 지어 먹던 90년대 귀성길 클라스 대방출 l 꿀잼 보장");
	    u.setLink("https://www.youtube.com/watch?v=bMSSOzE0Q2A");
	    u.setThumbnail("https://i.ytimg.com/vi/bMSSOzE0Q2A/maxresdefault.jpg");
	    u.setId("지윤");
	    u.setCreatedDate(new Date(System.currentTimeMillis()).toString());
	
	    this.youtubeDao.insert(u);

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
    y.setViewCount(y.getViewCount() + 1);
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
