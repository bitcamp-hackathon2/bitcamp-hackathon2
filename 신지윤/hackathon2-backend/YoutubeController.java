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
	/*
	public static void main(String[] args) {
		
		// Youtube 게시물 기본 목록
		int no = 1;	// Youtube 게시물 번호 	// 입력 X 
		String title = "[모아보기] 웬만한 악과 깡으로는 살아남기 힘들었던 90년대 레전드 영상｜크랩";	// Youtube 게시물 제목
		String content = "[설 연휴 크랩 모아보기 1탄!]" + 
				"크랩 효자 콘텐츠인 90년대 뉴트로 콘텐츠! 많고 많은 레전드 영상 중 찐 레전드만 모아봤습니다. 핸드폰과 유튜브만 준비해 주시죠. 제대로 모시겠습니다.";	// Youtube 게시물 내용
		String id = "admin";	// Youtube 작성자 id
		// 작성자의 id와 Youtube 게시물 작성자 비밀번호와 같다면 수정 및 삭제 가능하도록 하기
		String password = "admin";	// Youtube 게시물 작성자 비밀번호
		String createdDate = "2023-01-14";	// Youtube 게시물 작성일
		String link ="https://www.youtube.com/watch?v=chI0nkP07-M&t=1s";	// Youtube 링크
	}
*/

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
