package bitcamp.bootapp.vo;

public class Youtube {
// Youtube 입출력 값
	private int no;	// Youtube 게시물 번호 	// 입력 X 
	private String title;	// Youtube 게시물 제목
	private String name;	// Youtube 작성자 닉네임
	private String id;	// Youtube 작성자 id
	// 작성자의 id와 Youtube 게시물 작성자 비밀번호와 같다면 수정 및 삭제 가능하도록 하기
	private String password;	// Youtube 게시물 작성자 비밀번호
	private String createdDate;	// Youtube 게시물 작성일
	private int viewCount;	// Youtue 게시물 조회수
	private String link;	// Youtube 링크
	private String thumbnail;	// Youtube 썸네일 링크
	
	// Youtube 썸네일
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}

