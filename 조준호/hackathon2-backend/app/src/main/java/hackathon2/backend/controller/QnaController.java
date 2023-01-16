package hackathon2.backend.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import hackathon2.backend.dao.QnaDao;
import hackathon2.backend.vo.Qna;

@RestController
public class QnaController {

  QnaDao qnaDao = new QnaDao();

  public QnaController(QnaDao qnaDao) {
    this.qnaDao = qnaDao;
  }

  @PostMapping("/qnas")
  public Object addQna(
      @RequestParam(required = false) String id,
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String content,
      @RequestParam(required = false) String qpassword,
      @RequestParam(required = false) String aid,
      @RequestParam(required = false) String apassword,
      @RequestParam(required = false) boolean answer) {

    Qna b = new Qna();
    b.setId(id);
    b.setTitle(title);
    b.setContent(content);
    b.setQpassword(qpassword);
    b.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    b.setAnswer(answer);
    b.setAid(aid);
    b.setApassword(apassword);

    this.qnaDao.insert(b);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");

    return contentMap;
  }


  @GetMapping("/qnas")
  public Object getQnas() {

    Qna[] qnas = this.qnaDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", qnas);

    return contentMap;
  }

  @GetMapping("/qnas/{qnaNo}")
  public Object getQna(@PathVariable int qnaNo) {

    Qna b = this.qnaDao.findByNo(qnaNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (b == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당 번호의 게시글이 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", b);
    }

    return contentMap;
  }

  @PutMapping("/qnas/{qnaNo}")
  public Object updateQna(
      @PathVariable int qnaNo,
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String id,
      @RequestParam(required = false) String content,
      @RequestParam(required = false) String qpassword) {

    Map<String,Object> contentMap = new HashMap<>();

    Qna old = this.qnaDao.findByNo(qnaNo);
    if (old == null || !old.getQpassword().equals(qpassword)) {
      contentMap.put("status", "failure");
      contentMap.put("data", "게시글이 없거나 암호가 맞지 않습니다.");
      return contentMap;
    }

    Qna b = new Qna();
    b.setNo(qnaNo);
    b.setTitle(title);
    b.setId(old.getId());
    b.setContent(content);
    b.setQpassword(qpassword);
    b.setCreatedDate(old.getCreatedDate());
    b.setViewCount(old.getViewCount()+1);

    this.qnaDao.update(b);

    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/qnas/{qnaNo}")
  public Object deleteQna(
      @PathVariable int qnaNo,
      @RequestParam(required = false) String qpassword) {

    Qna b = this.qnaDao.findByNo(qnaNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (b == null || !b.getQpassword().equals(qpassword)) {
      contentMap.put("status", "failure");
      contentMap.put("data", "게시글이 없거나 암호가 맞지 않습니다.");

    } else {
      this.qnaDao.delete(b);
      contentMap.put("status", "success");
    }

    return contentMap;
  }


}