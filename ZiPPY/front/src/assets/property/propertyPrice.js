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
  let result = "";

  if(price.length > 4) {
    result += price.substr(0, price.length-4) + '억';
    price = price.substr(price.length-4, price.length);
    if(price == '0000') return result;
  }
  price = price.substr(price.length-4, price.length-3) + ',' + price.substr(price.length-3, price.length);
  result += price;
  
  return result;
}