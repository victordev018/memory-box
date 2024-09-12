const personName = document.getElementById("personName");
const bottomSearch = document.getElementById("bt_search");
const listPerson = document.querySelector(".list-person");
const bottomShowAll = document.querySelector(".show-all");
const URL = `http://localhost:8080/person`

const fetchApi = async (rout) => {
    try {
        const response = await fetch(rout);

        // verificando se a resposta não foi ok
        if (!response.ok) {
            // verificando se o status que chegou foi um 404 -> NOT_FOUND
            if (response.status === 404) {
                throw new Error("Pessoa não encontrada!")
            }
        }

        // se a busca resultar em dados
        const data = await response.json();
        return data;

    } catch (error) {
        alert(error.message);
    }
}

// função para listar um post por vez, fazendo a busca pelo nome
bottomSearch.addEventListener('click', async (event) => {
    event.preventDefault();
    const result = await fetchApi(URL+"/"+personName.value);
    personName.value = "";
    const html = `
    <div class="person">
        <h2 id="name">${result.name}</h2>
        <img id="img" src="${result.imageUrl}" alt="">
        <p id="description">${result.description}</p>
    </div>`;

    listPerson.innerHTML = html;
});

// evento para botão de mostrar todos os posts da api
bottomShowAll.addEventListener('click', async (event) => {
    event.preventDefault();
    const result = await fetchApi(URL);
    let html = ``;
    result.forEach((element) => {
        html += `
        <div class="person">
            <h2 id="name">${element.name}</h2>
            <img id="img" src="${element.imageUrl}" alt="">
            <p id="description">${element.description}</p>
        </div>`
    })

    listPerson.innerHTML = html;
})