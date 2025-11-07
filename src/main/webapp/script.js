function testarConexao() {
  fetch("http://localhost:8080/ddd-facilitareabijavaweb/api/hello")
    .then(response => response.json())
    .then(data => {
      alert(data.mensagem);
    })
    .catch(error => {
      console.error("Erro:", error);
    });
}
