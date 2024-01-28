function populateYearsSelect() {
    var select = document.getElementById("year");
    var currentYear = new Date().getFullYear();
    var minYear = 1900;

    for (var year = currentYear; year >= minYear; year--) {
        var option = document.createElement("option");
        option.value = year;
        option.textContent = year;
        select.appendChild(option);
    }
}

populateYearsSelect();