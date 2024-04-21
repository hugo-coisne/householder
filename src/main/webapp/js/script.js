const radioInputs = document.querySelectorAll('input[type="radio"]');
const tables = document.querySelectorAll('#recipes>table');

radioInputs.forEach(radio => {
    radio.addEventListener('change', function () {
        const selectedValue = this.id;
        tables.forEach(div => {
            div.hidden = div.id === selectedValue;
        });
    });
});

const tds = Array.from(document.querySelectorAll('tr[editable="true"]>td'));
tds.forEach(td => {
    td.addEventListener('click', () => {
        let value = td.innerText;
        const inputText = document.createElement("input");
        inputText.type = "text";
        inputText.placeholder = value;
        inputText.addEventListener('focusout',()=>{
            td.removeChild(inputText);
            const inputValue = inputText.value;
            td.innerText = inputValue!=="" ? inputValue : td.initialValue;
        })
        td.innerText = "";
        td.appendChild(inputText);
        inputText.focus();
    });
});