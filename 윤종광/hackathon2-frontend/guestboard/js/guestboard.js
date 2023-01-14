const writeGuestBoard = document.querySelector('#writeGuestBoard');
const writeDiv = document.querySelector('#writeDiv');
const writeCloseBtn = document.querySelector('#writeCloseBtn');
const writeBG = document.querySelector('#writeBG');
const writeArea = document.querySelector('#writeArea');
const charCount = document.querySelector('#charCount');
const writeBtn = document.querySelector('#writeBtn');


writeGuestBoard.addEventListener('click', () => {
  writeAreaCalcLen();
  writeDiv.classList.remove("writeDivHide");
})

writeCloseBtn.addEventListener('click', () => {
  writeDiv.classList.add("writeDivHide");
})

writeBG.addEventListener('click', () => {
  const clickWriteCloseBtn = new MouseEvent('click');
  writeCloseBtn.dispatchEvent(clickWriteCloseBtn);
})

writeArea.addEventListener('keyup', writeAreaCalcLen);


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

function writeAreaCalcLen() {
  const textLen = writeArea.value.length;
  let calcLen = 0;
  for (let i = 0; i < textLen; i++) {
    calcLen += writeArea.value.charCodeAt(i) >= 0 && writeArea.value.charCodeAt(i) <= 127 ? 0.5 : 1;
  }
  charCount.innerText = 140 - Math.ceil(calcLen);
  if (140 - Math.ceil(calcLen) < 0) {
    if (!charCount.classList.contains('charCountRed')) {
      charCount.classList.add('charCountRed');
    }
    if (!writeBtn.classList.contains('charCountRed')) {
      writeBtn.classList.add('charCountRed');
    }

    // [남기기] 링크 제거
    if (writeBtn.classList.contains('clickable')) {
      writeBtn.classList.remove('clickable');
      writeBtn.removeEventListener('click', createGuestboard);
    }
    if (writeBtn.classList.contains('clickable-green')) {
      writeBtn.classList.remove('clickable-green');
    }
    
  } else {
    if (charCount.classList.contains('charCountRed')) {
      charCount.classList.remove('charCountRed');
    }
    if (writeBtn.classList.contains('charCountRed')) {
      writeBtn.classList.remove('charCountRed');
    }

    // [남기기] 링크 활성화
    if (!writeBtn.classList.contains('clickable')) {
      writeBtn.classList.add('clickable');
      writeBtn.addEventListener('click', createGuestboard);
    }
    if (!writeBtn.classList.contains('clickable-green')) {
      writeBtn.classList.add('clickable-green');
    }
  }
}

function createGuestboard() {
  requestPost();
}