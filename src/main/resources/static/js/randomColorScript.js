const listElements = document.querySelectorAll('.main__content__categories__current__element');

listElements.forEach((categoryElement) => {
    categoryElement.style.backgroundColor = getRandomColor();
});

function getRandomColor() {
    const r = Math.floor(Math.random() * (200 - 80 + 1)) + 80;
    const g = Math.floor(Math.random() * (200 - 80 + 1)) + 80;
    const b = Math.floor(Math.random() * (200 - 80 + 1)) + 80;
    return `rgb(${r}, ${g}, ${b})`;
}