package bitcamp.backend.chat.dao;

import java.util.Arrays;
import org.springframework.stereotype.Repository;
import bitcamp.backend.chat.vo.Board;

@Repository
public class BoardDao {
  private static final int SIZE = 100;

  private int no; // board 식별 번호
  private int count;
  private Board[] boards = new Board[SIZE];

  // 생성
  public void insert(bitcamp.backend.chat.vo.Board b) {
    b.setNo(++no);
    this.boards[this.count++] = b;
  }

  // 전체글, copyOf(복사할배열,복사할배열크기)
  public Board[] findAll() {
    return Arrays.copyOf(boards, count);
  }

  public Board findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.boards[i].getNo() == no) {
        return this.boards[i];
      }
    }
    return null;
  }

  public void update(Board board) {
    this.boards[this.indexOf(board)] = board;
  }

  public void delete(Board board) {
    for (int i = this.indexOf(board) + 1; i < this.count; i++) {
      this.boards[i - 1] = this.boards[i];
    }
    this.boards[--this.count] = null;
  }

  private int indexOf(Board b) {
    for (int i = 0; i < this.count; i++) {
      if (this.boards[i].getNo() == b.getNo()) {
        return i;
      }
    }
    return -1;
  }

}


