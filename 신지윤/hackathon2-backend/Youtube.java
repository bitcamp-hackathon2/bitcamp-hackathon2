package bitcamp.bootapp.vo;

public class Youtube {
// Youtube 입출력 값
	private int no;	// Youtube 게시물 번호 	// 입력 X 
	private String title;	// Youtube 게시물 제목
	private String content;	// Youtube 게시물 내용
	private String name;	// Youtube 작성자 닉네임
	private String id;	// Youtube 작성자 id
	// 작성자의 id와 Youtube 게시물 작성자 비밀번호와 같다면 수정 및 삭제 가능하도록 하기
	private String password;	// Youtube 게시물 작성자 비밀번호
	private String createdDate;	// Youtube 게시물 작성일
	private int viewCount;	// Youtue 게시물 조회수
	private String link;	// Youtube 링크
	private String thumbnail;	// Youtube 썸네일
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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

// 공지사항으로 작성법 만들기
/*
 * 썸네일 만드는 법 : https://kr.noxinfluencer.com/youtube/thumbnails-download에 들어가서 썸네일 다운받기
 * 
 * 
 * */

// 기본 게시물 목록
/*
 * [모아보기] 웬만한 악과 깡으로는 살아남기 힘들었던 90년대 레전드 영상｜크랩
 * https://www.youtube.com/watch?v=chI0nkP07-M
 * 
 * 
 * 그 많던 뺑뺑이와 정글짐이 놀이터에서 사라진 이유｜크랩
 * https://www.youtube.com/watch?v=yiyNC5dLMJ0
 * 
 * 
 * 개마저 떡실신시킨 80년대 여름
 * https://www.youtube.com/watch?v=bv6euol2IYk
 * 
 *  
 * 서울-대전 10시간;; 고속도로에서 밥 지어 먹던 90년대 귀성길 클라스 대방출 l 꿀잼 보장
 * https://www.youtube.com/watch?v=bMSSOzE0Q2A
 * 
 * 
 * 저 세상 스웩;; 90년대 압구정 오렌지족 하루 ㅎㄷㄷ;;
 * https://www.youtube.com/watch?v=0TCSqCmqzY4
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */

