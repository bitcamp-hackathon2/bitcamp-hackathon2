package hackathon2.backend.vo;

import java.util.ArrayList;

public class Guestboard {
  String id;
  String name;
  String password;
  String etc;
  int no;
  String createdDate;
  long createdTime;
  int like;
  ArrayList<String> likeId;
  String content;

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getEtc() {
    return etc;
  }
  public void setEtc(String etc) {
    this.etc = etc;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }
  public long getCreatedTime() {
    return createdTime;
  }
  public void setCreatedTime(long nowMSec) {
    this.createdTime = nowMSec;
  }
  public int getLike() {
    return like;
  }
  public void setLike(int like) {
    this.like = like;
  }
  public ArrayList<String> getLikeId() {
    return likeId;
  }
  public void setLikeId(ArrayList<String> likeId) {
    this.likeId = likeId;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }

}

