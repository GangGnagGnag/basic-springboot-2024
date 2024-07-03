
export function formatDate(date) {  //2024-06-28 10:30:02.131783
    var result = date.replace('T' , ' '); //T를 공백으로 변경  
    var index = result.lastIndexOf(' '); //초 앞에 있는 : 위치값, ' ' 공백으로 하면 yyyy-mm-dd만 남김

    result = result.substr(0, index); //초 뒤로 삭제

    return result;
}