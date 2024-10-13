const menu = document.querySelector('.header__menu__button');
const nav = document.querySelector('.nav');
const elements = document.querySelectorAll('.nav__menu__list__name');
const menuState = sessionStorage.getItem('menuSwitcher');
var switcher = 1;
var menuStateBuffer;

// Выбор размера при загрузке старницы

if (window.innerWidth <= 1232) {
    changeStyle('50px', 'hidden');
} else if (menuState === 'collapsed') {
    changeStyle('50px', 'hidden');
} else {
    changeStyle('200px', 'visible');
}

// Сворачивание по клику

menu.addEventListener("click", function() {
    if (nav.style.width === '50px') {
        changeStyle('200px', 'visible');
        sessionStorage.setItem('menuSwitcher', 'expanded');
    } else {
        changeStyle('50px', 'hidden');
        sessionStorage.setItem('menuSwitcher', 'collapsed');
    }
});

// Сворачивание при изменении ширины экрана

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

// Функция применения стилей к элементам интерфейса

function changeStyle(size, visibility) {
    nav.style.width = size;
    elements.forEach(element => {
        element.style.visibility = visibility;
    });
}

// Изменение цвета текста в зависимости от данных

const taskState = document.querySelectorAll('.task__state');

taskState.forEach(element => {
    console.log(element);
    if (element.textContent === 'Новая') {
        element.style.color = 'var(--red-color)';
    } else {
        element.style.color = 'var(--green-color)';
    }
})

