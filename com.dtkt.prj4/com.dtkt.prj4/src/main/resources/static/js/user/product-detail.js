const hiddenSizeInput =
    document.getElementById("selectedSize");
sizeButtons.forEach(button => {
    button.addEventListener("click", () => {
        sizeButtons.forEach(btn => {
            btn.classList.remove("active");
        });
        button.classList.add("active");
        hiddenSizeInput.value =
            button.innerText;
    });
});