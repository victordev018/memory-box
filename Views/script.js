const personName = document.getElementById("personName");
const bottomSearch = document.getElementById("bt_search");
const name = document.getElementById("name");
const image = document.getElementById("img");
const description = document.getElementById("description");

const fetchApi = (name) => {
    const result = fetch(`http://localhost:8080/person/${name}`)
        .then((resp) => resp.json())
        .then((data) => {
            return data;
        });

    return result;
}

bottomSearch.addEventListener('click', async (event) => {
    event.preventDefault();
    const result = await fetchApi(personName.value);
    if (result){
        name.textContent = `${result.name}`;
        image.src = `${result.imageUrl}`;
        description.textContent = `${result.description}`;
    }
});