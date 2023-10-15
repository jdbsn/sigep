fetch("https://servicodados.ibge.gov.br/api/v1/localidades/estados?orderBy=nome").then(function(response) {
  return response.json();
}).then(function(data) {
    let select = document.getElementById("state")
    data.forEach(d => {
      const option = document.createElement('option');

      option.setAttribute('value', d.sigla);
      option.textContent = d.sigla;

      select.appendChild(option);
    })

}).catch(function(err) {
  console.log('Fetch Error :-S', err);
});