function getRandomColor() {
    const r = Math.floor(Math.random() * (240 - 120 + 1)) + 120;
    const g = Math.floor(Math.random() * (240 - 120 + 1)) + 120;
    const b = Math.floor(Math.random() * (240 - 120 + 1)) + 120;
    return `rgb(${r}, ${g}, ${b})`;
}