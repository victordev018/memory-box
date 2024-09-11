const personName = document.getElementById("personName");
const bottomSearch = document.getElementById("bt_search");
const body = document.body;
const listPerson = document.querySelector(".list-person")

const fetchApi = async (name) => {
    try {
        const response = await fetch(`http://localhost:8080/person/${name}`);

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

bottomSearch.addEventListener('click', async (event) => {
    event.preventDefault();
    const result = await fetchApi(personName.value);
    personName.value = "";
    const html = `
    <div class="person">
        <h2 id="name">${result.name}</h2>
        <img id="img" src="${result.imageUrl}" alt="">
        <p id="description">${result.description}</p>
    </div>`;

    listPerson.innerHTML = html;
});