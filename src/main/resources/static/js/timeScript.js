const timeOutput = document.querySelector('.main__content__info__time');

timeDateFunc();
setInterval(timeDateFunc, 1000);

function timeDateFunc() {
    var dateNow = new Date();
    var date = dateNow.getDate();
    var month = dateNow.getMonth() + 1;
    var year = dateNow.getFullYear();
    var hours = dateNow.getHours();
    var minutes = dateNow.getMinutes();
    if (date < 10) date = "0" + date;
    if (month < 10) month = "0" + month;
    if (minutes < 10) minutes = "0" + minutes;
    if (hours < 10) hours = "0" + hours;
    timeOutput.innerHTML = date + "." + month + "." + year + ", " + hours + ":" + minutes;
}