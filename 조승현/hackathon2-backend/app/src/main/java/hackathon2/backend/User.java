package hackathon2.backend;

public class User {
  private String id;
  private String password;
  private String name;
  private String etc;

  public User(String id, String password, String name, String etc) {
    this.id = id;
    this.password = password;
    this.name = name;
    this.etc = etc;
  }

  public String getEtc() {
    return etc;
  }

  public void setEtc(String etc) {
    this.etc = etc;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
