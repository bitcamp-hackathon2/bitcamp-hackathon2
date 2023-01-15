package bitcamp.bootapp.dao;

import java.sql.Date;
import java.util.Arrays;

import org.springframework.stereotype.Repository;

import bitcamp.bootapp.vo.Youtube;



// @Component
@Repository	// DAO 역할을 수행하는 객체에 대해서 붙인다.
public class YoutubeDao {
  private static final int SIZE = 100;

  private int no;
  private int count;
  private Youtube[] youtubes = new Youtube[SIZE];


  
  // Youtube 게시물 생성
  public void insert(Youtube youtube) {
    youtube.setNo(++no);
    youtube.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    this.youtubes[this.count++] = youtube;
  }

  // Youtube 게시물 전체 목록
  public Youtube[] findAll() {
    return Arrays.copyOf(youtubes, count);
  }

  // Youtube 게시물 추출하기 ( 번호로 )
  public Youtube findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.youtubes[i].getNo() == no) {
        return this.youtubes[i];
      }
    }
    return null;
  }

  // Youtube 게시물 수정하기
  public void update(Youtube youtube) {
    this.youtubes[this.indexOf(youtube)] = youtube;
  }

  // Youtube 게시물 삭제하기
  public void delete(Youtube youtube) {
    for (int i = this.indexOf(youtube) + 1; i < this.count; i++) {
      this.youtubes[i - 1] = this.youtubes[i];
    }
    this.youtubes[--this.count] = null; // 레퍼런스 카운트를 줄인다.
  }
  
  // 유튜브 게시물 추가하기
  private int indexOf(Youtube youtube) {
    for (int i = 0; i < this.count; i++) {
      if (this.youtubes[i].getNo() == youtube.getNo()) {
        return i;
      }
    }
    return -1;
  }
}







