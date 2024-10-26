const listElements = document.querySelectorAll('.main__content__categories__current__element');

listElements.forEach((categoryElement) => {
    categoryElement.style.backgroundColor = getRandomColor();
});