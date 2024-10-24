const buttonPlus = document.querySelector('.main__content__categories__button');
const listElements = document.querySelectorAll('.main__content__categories__list__element');
const buttonSelect = document.querySelector('.main__content__categories__button__select');
const currentCategory = document.querySelector('.main__content__categories__current');

// Создаем элементы списка на основе элементов select

console.log(document.querySelector('.main__content__categories__list'));

listElements.forEach((option) => {
    const li = document.createElement('li');
    li.className = 'main__content__categories__button__select__element';
    li.textContent = option.textContent;
    li.dataset.value = option.value;
    buttonSelect.appendChild(li);
});

// Показ-скрытие выпадающего списка

buttonPlus.addEventListener('click', function() {
    buttonSelect.style.visibility = buttonSelect.style.visibility === 'visible' ? 'hidden' : 'visible';
});

// Закрытие списка при уходе курсора мыши

buttonSelect.onmouseleave = function() {
    buttonSelect.style.visibility = 'hidden';
};

// Получаем все элементы списка, созданные динамически

const buttonSelectElements = document.querySelectorAll('.main__content__categories__button__select__element');

// Функция вывода списка категорий по данным из БД

buttonSelectElements.forEach((categoryElement) => {
    const associatedOption = document.querySelector(`.main__content__categories__list__element[value="${categoryElement.dataset.value}"]`);
    if (associatedOption.selected === true) {
        const li = document.createElement('li');
        li.className = 'main__content__categories__current__element';

        li.style.backgroundColor = getRandomColor();

        const span = document.createElement('span');
        span.textContent = categoryElement.textContent;

        const button = document.createElement('button');
        button.className = 'main__content__categories__current__button';
        button.type = 'button';

        const img = document.createElement('img');
        img.src = '/img/close.svg';

        button.addEventListener('click', function() {
            currentCategory.removeChild(li);
            categoryElement.style.display = 'block';
        });

        button.appendChild(img);
        li.appendChild(span);
        li.appendChild(button);
        currentCategory.appendChild(li);

        categoryElement.style.display = 'none';
    }
});

// Функция добавления и удаления из списка

buttonSelectElements.forEach((categoryElement) => {
    categoryElement.addEventListener('click', function() {
        const li = document.createElement('li');
        li.className = 'main__content__categories__current__element';

        li.style.backgroundColor = getRandomColor();

        const span = document.createElement('span');
        span.textContent = categoryElement.textContent;

        const button = document.createElement('button');
        button.className = 'main__content__categories__current__button';
        button.type = 'button';

        const img = document.createElement('img');
        img.src = '/img/close.svg';

        button.addEventListener('click', function() {
            currentCategory.removeChild(li);
            categoryElement.style.display = 'block';
        });

        button.appendChild(img);
        li.appendChild(span);
        li.appendChild(button);
        currentCategory.appendChild(li);

        const associatedOption = document.querySelector(`.main__content__categories__list__element[value="${categoryElement.dataset.value}"]`);
        if (associatedOption) {
            associatedOption.selected = true;
        }

        categoryElement.style.display = 'none';
    });
});

// Функция создания случайного RGB цвета в числовом диапазоне от 80 до 200

function getRandomColor() {
    const r = Math.floor(Math.random() * (200 - 80 + 1)) + 80;
    const g = Math.floor(Math.random() * (200 - 80 + 1)) + 80;
    const b = Math.floor(Math.random() * (200 - 80 + 1)) + 80;
    return `rgb(${r}, ${g}, ${b})`;
}