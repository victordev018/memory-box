// campos de entrada de de textos do formulário
const name = document.getElementById("name");
const imageUrl = document.getElementById("image");
const description = document.getElementById("description");
const btCreatePost = document.getElementById("bt_create_post");

// método para enviar uma requisição de post para a a API
const methodPost = async () => {

    // url da api para requisição de post
    const URL = `http://localhost:8080/person/create`;

    // criando corpo json com dados para enviar
    const requestBody = {
        "name" : name.value,
        "description" : description.value,
        "imageUrl" : imageUrl.value
    }

    fetch(URL, {
        method : "POST",
        headers : {
            "Content-Type" : "application/json"
        },
        body : JSON.stringify(requestBody),
    })
        .then(response => {
            if (response.ok){
                alert("Post criado com sucesso!")
            }
            else {
                alert("Falha ao tentar criar o post")
            }
        })
}

btCreatePost.addEventListener('click',async (event) => {
    event.preventDefault();
    await methodPost();
    window.location.href = "index.html"
})