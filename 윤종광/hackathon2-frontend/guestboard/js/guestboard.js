const writeGuestBoard = document.querySelector('#writeGuestBoard');
const writeDiv = document.querySelector('#writeDiv');
const writeCloseBtn = document.querySelector('#writeCloseBtn');

function getElapsedTime(createdTime) {
  const nowTime = new Date().getTime();
  const elapsedTime = nowTime - createdTime;
  const elapsedSecond = Math.floor(elapsedTime / 1000);
  const elapsedMinute = Math.floor(elapsedSecond / 60);
  const elapsedHour = Math.floor(elapsedMinute / 60);
  const elapsedDay = Math.floor(elapsedHour / 24);
  const elapsedMonth = Math.floor(elapsedDay / 30);
  const elapsedYear = Math.floor(elapsedDay / 365);

  if (elapsedSecond < 60) {
    return `${elapsedSecond}초 전`;
  } else if (elapsedMinute < 60) {
    return `${elapsedMinute}분 전`;
  } else if (elapsedHour < 24) {
    return `${elapsedHour}시간 전`;
  } else if (elapsedDay < 30) {
    return `${elapsedDay}일 전`;
  } else if (elapsedYear < 1) {
    return `${elapsedMonth}개월 전`;
  } else {
    return `${elapsedYear}년 전`;
  }
}

writeGuestBoard.addEventListener('click', () => {
  writeDiv.classList.remove("writeDivHide");
})

writeCloseBtn.addEventListener('click', () => {
  writeDiv.classList.add("writeDivHide");
})