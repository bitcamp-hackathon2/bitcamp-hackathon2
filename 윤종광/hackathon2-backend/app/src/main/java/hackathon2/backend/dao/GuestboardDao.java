package hackathon2.backend.dao;

import java.sql.Date;
import java.util.Arrays;
import org.springframework.stereotype.Repository;
import hackathon2.backend.vo.Guestboard;

@Repository
public class GuestboardDao {
  private static final int SIZE = 100;

  private int no;
  private int count;
  private Guestboard[] guestboards = new Guestboard[SIZE];

  public void insert(Guestboard guestboard) {
    guestboard.setNo(++no);
    guestboard.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    guestboard.setCreatedTime(System.currentTimeMillis());
    this.guestboards[this.count++] = guestboard;
  }

  public Guestboard[] findAll() {
    return Arrays.copyOf(guestboards, count);
  }

  public Guestboard findByNo(int no) {
    for (Guestboard guestboard : guestboards) {
      return guestboard.getNo() == no ? guestboard : null;
    }
    return null;
  }

  public void update(Guestboard guestboard) {
    this.guestboards[this.indexOf(guestboard)] = guestboard;
  }

  public void delete(Guestboard guestboard) {
    for(int i = indexOf(guestboard); i < this.count - 1; i++) { // i = 3, count = 4
      this.guestboards[i] = this.guestboards[i + 1];
    }
    this.guestboards[--count] = null;
  }

  public int indexOf(Guestboard guestboard) {
    for (int i = 0; i < this.count; i++) {
      if (this.guestboards[i].getNo() == guestboard.getNo()) {
        return i;
      }
    }
    return -1;
  }

}
