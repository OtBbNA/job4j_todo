const menu = document.querySelector('.header__registration__button');
const nav = document.querySelector('.nav');
const elements = document.querySelectorAll('.nav__menu__list__name');
const menuState = sessionStorage.getItem('menuSwitcher');

if (menuState === 'collapsed') {
    elements.forEach(element => {
        nav.style.width = '3.5%';
        element.style.visibility = 'hidden';
    });
} else {
    elements.forEach(element => {
        nav.style.width = '20%';
        element.style.visibility = 'visible';
    });
}

menu.addEventListener("click", function() {
    if (nav.style.width === '3.5%') {
        elements.forEach(element => {
            nav.style.width = '20%';
            element.style.visibility = 'visible';
        });
        sessionStorage.setItem('menuSwitcher', 'expanded');
    } else {
        elements.forEach(element => {
            nav.style.width = '3.5%';
            element.style.visibility = 'hidden';
        });
        sessionStorage.setItem('menuSwitcher', 'collapsed');
    }
});