export{getPrice, oneHundredMillion}

function getPrice(price) {
  let result = '';
  let quotient = Math.floor(price.length / 3);
  let remainder = price.length % 3;

  for (let i = 0; i < remainder; i++) {
    result += price[i];
  }
  result += ',';
  for (let i = 0; i < quotient - 1; i++) {
    for (let j = 0; j < 3; j++) {
      result += price[remainder++];
    }
    result += ',';
  }
  for (let j = 0; j < 3; j++) {
    result += price[remainder++];
  }
  console.log('콤마 추가: ', result);

  return result;
}

function oneHundredMillion(price) {
  let result = getPrice(price);

  result = result.substring(0, result.length - 5) + '억' + result.substring(result.length - 5, result.length);
  console.log('억 추가: ', result);
  
  return result;
}