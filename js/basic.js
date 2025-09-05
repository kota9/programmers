// 20250905
// 카운트 다운
// 정수 start_num와 end_num가 주어질 때, start_num에서 end_num까지 1씩 감소하는 수들을 차례로 담은 리스트를 return하도록 solution 함수를 완성해주세요.
function solution1(start, end) {
    let answer = [];

    for (let i = start; i >= end; i--) {
        answer.push(i);
    }

    return answer;
}
// console.log(solution1(10, 3));

// 대소문자 바꿔서 출력하기
// 영어 알파벳으로 이루어진 문자열 str이 주어집니다. 각 알파벳을 대문자는 소문자로 소문자는 대문자로 변환해서 출력하는 코드를 작성해 보세요.
function swapCase(str) {
  let result = ''; // 결과를 담을 빈 문자열
  for (let i = 0; i < str.length; i++) {
    let char = str[i];
    // 현재 문자가 대문자인지 확인
    if (char === char.toUpperCase()) {
      // 대문자라면 소문자로 바꿔서 결과에 추가
      result += char.toLowerCase();
    } else {
      // 소문자라면 대문자로 바꿔서 결과에 추가
      result += char.toUpperCase();
    }
  }
  return result;
}

let myString = "Hello, World!";
let swappedString = swapCase(myString);
console.log(swappedString); // 결과: "hELLO, wORLD!"

let anotherString = "jAVAsCRIPT is fUN";
console.log(swapCase(anotherString)); // 결과: "Javascript IS Fun"

