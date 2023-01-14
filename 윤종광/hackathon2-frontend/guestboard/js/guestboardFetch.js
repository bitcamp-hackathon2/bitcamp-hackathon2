fetch("http://localhost:8080/guestboard")
.then((response) => response.json())
.then((obj) => {
  let lis = "";
  for (let e of obj.data) {
    lis +=
      `<li class="board">
        <div>
          <span id="name">${e.name}</span>
          <span id="id">@${e.id}</span>
          <span id="elapsedTime">${getElapsedTime(e.createdTime)}</span>
          <span id="likeSpan">
            <span id="likeIcon" class="clickable"></span>
            <span id="like"> ${e.like}</span>
          </span>
        </div>
        <hr>
        <div id="contentMain">
          <div id="content">${e.content}</div>
          <div id="del-up-btn">
            <span id="delete-btn" class="clickable clickable-green">[삭제]</span>
            <span id="update-btn" class="clickable clickable-green">[수정]</span>
          </div>
        </div>
      </li>`;
    }
    document.querySelector('#guestbook-ul').innerHTML = lis;

  })
  .catch((err) => {
    alert('서버 요청 오류!');
    console.log(err);
  })