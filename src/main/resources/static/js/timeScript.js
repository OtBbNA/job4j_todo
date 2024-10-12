const timeOutput = document.querySelector('.main__content__info__time');
var dateNow = new Date();

timeDateFunc();
setInterval(timeDateFunc, 1000);

function timeDateFunc() {
    var date = dateNow.getDate();
    var month = dateNow.getMonth() + 1;
    var year = dateNow.getFullYear();
    var hours = dateNow.getHours();
    var minutes = dateNow.getMinutes();
    if (date < 10) date = "0" + date;
    if (month < 10) month = "0" + month;
    if (minutes < 10) minutes = "0" + minutes;
    timeOutput.innerHTML = date + "." + month + "." + year + ", " + hours + ":" + minutes;
}