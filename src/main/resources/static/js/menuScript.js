const menu = document.querySelector('.header__registration__button');
const nav = document.querySelector('.nav');
const elements = document.querySelectorAll('.nav__menu__list__name');
const menuState = sessionStorage.getItem('menuSwitcher');
var switcher = 1;
var menuStateBuffer;

if (menuState === 'collapsed') {
    changeStyle('50px', 'hidden');
} else {
    changeStyle('200px', 'visible');
}

menu.addEventListener("click", function() {
    resize()
});

function resize() {
    if (nav.style.width === '50px') {
        changeStyle('200px', 'visible');
        sessionStorage.setItem('menuSwitcher', 'expanded');
    } else {
        changeStyle('50px', 'hidden');
        sessionStorage.setItem('menuSwitcher', 'collapsed');
    }
}

window.addEventListener('resize', () => {
    if (window.innerWidth <= 1232 && switcher) {
        changeStyle('50px', 'hidden');
        switcher = 0;
        menuStateBuffer = sessionStorage.getItem('menuSwitcher');
        sessionStorage.setItem('menuSwitcher', 'collapsed');
    } else if (window.innerWidth >= 1232 && !switcher) {
        if (menuStateBuffer == 'expanded') {
            changeStyle('200px', 'visible');
            switcher = 1;
            sessionStorage.setItem('menuSwitcher', 'expanded');
        } else {
            changeStyle('50px', 'hidden');
            switcher = 1;
            sessionStorage.setItem('menuSwitcher', 'collapsed');
        }
    }
});

function changeStyle(size, visibility) {
    nav.style.width = size;
    elements.forEach(element => {
        element.style.visibility = visibility;
    });
}

