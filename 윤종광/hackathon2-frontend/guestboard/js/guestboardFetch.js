// let all = location.href.split("?")[1];
// let name = all.split("=")[1].split(",")[0];
// let id = all.split("=")[1].split(",")[1];



    // ${(id === e.id) ? loginIdDelUpBtn : ""}
const id = 0; // 임시 id
      

fetch("http://localhost:8080/guestboard")
.then((response) => response.json())
.then((obj) => {
  let lis = "";
  for (let e of obj.data) {
    lis +=
      `<li class="board">
        <div>
          <span class="name">${e.name}</span>
          <span class="id">@${e.id}</span>
          <span class="elapsedTime">${getElapsedTime(e.createdTime)}</span>
          <span class="likeSpan">
            <span class="likeIcon" class="clickable"></span>
            <span class="like"> ${e.like}</span>
          </span>
        </div>
        <hr>
        <div class="contentMain">
          <div class="content">${e.content}</div>
          <div class="del-up-btn">
            ${loginIdDelUpBtn(e.no)}
          </div>
        </div>
      </li>`;
    }
    document.querySelector('#guestbook-ul').innerHTML = lis;
  
    workAfterLoad();

  })
  .catch((err) => {
    // alert('서버 요청 오류!');
    console.log(err);
  })
  

function requestPost() {
  const content = document.querySelector('#writeArea').value;

  fetch('http://localhost:8080/guestboard', {
    method: 'POST',
    headers: {
      'content-type': 'application/x-www-form-urlencoded'
    },
    body: `id=${id}&content=${content}`
  })
    .then((response) => response.json())
    .then((obj) => {
      location.href = 'guestboard.html';
    })
    .catch((err) => {
      alert('글쓰기 요청 오류!');
      console.log(err);
  })
}

function requestUpdate(dataNo) {
  const content = document.querySelector('#writeArea').value;
  console.log(dataNo);/*
  fetch('http://localhost:8080/guestboard', {
    method: 'PUT',
    headers: {
      'content-type': 'application/x-www-form-urlencoded'
    },
    body: `no=${dataNo}&content=${content}`
  })
    .then((response) => response.json())
    .then((obj) => {
      location.href = 'guestboard.html';
    })
    .catch((err) => {
      alert('수정 요청 오류!');
      console.log(err);
  })*/
}

function requestDelete(dataNo) {
  fetch('http://localhost:8080/guestboard', {
    method: 'DELETE',
    headers: {
      'content-type': 'application/x-www-form-urlencoded'
    },
    body: `no=${dataNo}`
  })
    .then((response) => response.json())
    .then((obj) => {
      location.href = 'guestboard.html';
    })
    .catch((err) => {
      alert('삭제 요청 오류!');
      console.log(err);
  })
}

const loginIdDelUpBtn = function (GuestboardNo) {
return  `<span id="update-btn" class="clickable clickable-green contentUpdBtn" data-no="${GuestboardNo}">[수정]</span>
  <span id="delete-btn" class="clickable clickable-green contentDelBtn" data-no="${GuestboardNo}">[삭제]</span>`;
}

function workAfterLoad() {
  const contentUpdBtn = document.querySelectorAll('.contentUpdBtn');
  const contentDelBtn = document.querySelectorAll('.contentDelBtn');
  

  // 게시물 수정 버튼 클릭 처리
  contentUpdBtn.forEach(e => e.addEventListener('click', () => {
    const oldContent = e.parentElement.previousElementSibling.innerText;
    const dataNo = e.getAttribute('data-no');
    
    if (writeDiv.classList.contains('writeDivHide')) {
      writeArea.innerText = oldContent;
      writeAreaCalcLen();
      writeDiv.classList.remove('writeDivHide');
      writeBtn.addEventListener('click', (e) => {
        updateGuestboard(e, dataNo)
      });
    }
  }))

  // 게시물 삭제 버튼 클릭 처리
  contentDelBtn.forEach(e => e.addEventListener('click', () => {
    if (confirm('정말 삭제 하시겠습니까?')) {
      const dataNo = e.getAttribute('data-no');
      requestDelete(dataNo);
    } 
  }))

}

