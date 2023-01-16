// let all = location.href.split("?")[1];
// let name = all.split("=")[1].split(",")[0];
// let id = all.split("=")[1].split(",")[1];



    // ${(id === e.id) ? loginIdDelUpBtn : ""}
let all = location.href.split("?")[1];
let username = decodeURI(all.split("=")[1].split(",")[0]);
let id  = all.split("=")[1].split(",")[1]


fetch("http://localhost:8080/guestboard")
.then((response) => response.json())
.then((obj) => {

  for (let i = 0; i < obj.data.length; i++) {
      setTimeout(() => {
        const e = obj.data[i];
        const ul = document.querySelector('#guestbook-ul');
        const li = document.createElement('li');
        li.classList.add('board');
        const liContent =
          `<div>
              <span class="name">${e.name}</span>
              <span class="id">@${e.id}</span>
              <span class="elapsedTime">${getElapsedTime(e.createdTime)}</span>
              <span class="likeSpan">
                <span class="likeIcon clickable ${isLiked(id, e.likeId)}" data-no="${e.no}"></span>
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
          `;
        
        li.innerHTML = liContent;
        ul.appendChild(li);
      }, -(i-obj.data.length)*500);
    
  };
  
setTimeout(() => {
  workAfterLoad();
}, obj.data.length*500+500);
  })
  .catch((err) => {
    // alert('서버 요청 오류!');
    console.log(err);
  })
// 

function isLiked(userId, likeId) {
  return likeId?.includes(userId) ? 'liked' : '';
}

function requestPost() {
  const content = document.querySelector('#writeArea').value;

  fetch('http://localhost:8080/guestboard', {
    method: 'POST',
    headers: {
      'content-type': 'application/x-www-form-urlencoded'
    },
    body: `id=${id}&content=${content}&name=${username}`
  })
    .then((response) => response.json())
    .then((obj) => {
      location.href = 'guestboard.html?'+all;
    })
    .catch((err) => {
      alert('글쓰기 요청 오류!');
      console.log(err);
  })
}

function requestUpdate(dataNo) {
  const content = document.querySelector('#writeArea').value;
  
  fetch('http://localhost:8080/guestboard', {
    method: 'PUT',
    headers: {
      'content-type': 'application/x-www-form-urlencoded'
    },
    body: `no=${dataNo}&content=${content}`
  })
    .then((response) => response.json())
    .then((obj) => {
      location.href = 'guestboard.html?'+all;
    })
    .catch((err) => {
      alert('수정 요청 오류!');
      console.log(err);
  })
}

function requestUpdateLike(userId, dataNo) {
  
  fetch('http://localhost:8080/guestboard', {
    method: 'PUT',
    headers: {
      'content-type': 'application/x-www-form-urlencoded'
    },
    body: `id=${userId}&no=${dataNo}`
  })
    .then((response) => response.json())
    .then((obj) => {
      
    })
    .catch((err) => {
      alert('수정 요청 오류!');
      console.log(err);
  })
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
      location.href = 'guestboard.html?'+all;
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
  const likeIcon = document.querySelectorAll('.likeIcon');
  
  // 좋아요 버튼 클릭 처리
  likeIcon.forEach(item => item.addEventListener('click', (e) => {
    let oldLikeCnt = e.target.parentElement.children[1].innerText;
    let minusLikeCnt = parseInt(oldLikeCnt) - 1;
    let plusLikeCnt = parseInt(oldLikeCnt) + 1;
    
    if (e.target.classList.contains('liked')) {
      // 좋아요 누른 게시물 처리
      e.target.classList.remove('liked');
      e.target.parentElement.children[1].innerText = minusLikeCnt;
    
    } else {
      // 좋아요 안 누른 게시물 처리
      e.target.classList.add('liked');
      e.target.parentElement.children[1].innerText = plusLikeCnt;
    }
    requestUpdateLike(id, e.target.getAttribute('data-no'));
  }))

  // 게시물 수정 버튼 클릭 처리
  contentUpdBtn.forEach(item => item.addEventListener('click', () => {
    console.log(14)
    const oldContent = item.parentElement.previousElementSibling.innerText;
    const dataNo = item.getAttribute('data-no');
    
    if (writeDiv.classList.contains('writeDivHide')) {
      writeArea.innerText = oldContent;
      writeAreaCalcLen();
      writeDiv.classList.remove('writeDivHide');
      writeBtn.onclick = (e) => {
        updateGuestboard(e, dataNo)
      }
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

