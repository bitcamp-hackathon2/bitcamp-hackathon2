package hackathon2.backend.dao;

import java.util.Arrays;
import org.springframework.stereotype.Repository;
import hackathon2.backend.vo.Qna;

@Repository
public class QnaDao {
  private static final int SIZE = 100;

  private int no; // qna 식별 번호
  private int count;
  private Qna[] qnas = new Qna[SIZE];

  public void insert(Qna qna) {
    qna.setNo(++no);
    this.qnas[this.count++] = qna;
  }

  public Qna[] findAll() {
    // 배열의 값 복제
    //    Qna[] arr = new Qna[this.count];
    //    for (int i = 0; i < this.count; i++) {
    //      arr[i] = this.qnas[i];
    //    }
    //    return arr;

    // 위와 같다!
    return Arrays.copyOf(qnas, count);
  }

  public Qna findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.qnas[i].getNo() == no) {
        return this.qnas[i];
      }
    }
    return null;
  }

  public void update(Qna qna) {
    this.qnas[this.indexOf(qna)] = qna;
  }

  public void delete(Qna qna) {
    for (int i = this.indexOf(qna) + 1; i < this.count; i++) {
      this.qnas[i - 1] = this.qnas[i];
    }
    this.qnas[--this.count] = null; // 레퍼런스 카운트를 줄인다.
  }

  private int indexOf(Qna b) {
    for (int i = 0; i < this.count; i++) {
      if (this.qnas[i].getNo() == b.getNo()) {
        return i;
      }
    }
    return -1;
  }
}







