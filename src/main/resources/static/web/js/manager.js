const {createApp} = Vue.createApp({
  data() {
      return {
      cargaDatos: [],
      form: {
        "name": "",
        "lastName": "",
        "emailAddress": "",
        "password": ""
      },
        formLoan:{
          "name":"",
          "maxAmount":"",
          "paymentsLoans":[],
          "loanInterest":""
      },
      count: 0,

    }
  },
  created() {
    axios.get('/rest/clients/')
    .then(res => {
      let respuestaDatos = res.data
      this.cargaDatos = respuestaDatos._embedded.clients
      console.log(this.cargaDatos);
    }).catch(error => window.alert("An error has occurred: " + error));
  },
  methods: {
  
    addClient() {
      axios.post('/rest/clients/', this.form)
        .then(res => {
          console.log(res);
        });

    },
    editClient(clientToEdit) {
      let clientNew = {
        name: prompt("name"),
        lastName: prompt("last name"),
        email: prompt("email")

      }
      axios.put(clientToEdit, clientNew)
    }
    , deleteClient(clientToDelete) {
      axios.delete(clientToDelete).then(response => alert(response));
    },
    createLoan(){
      axios.post("/api/admin/crateLoan",
        this.formLoan
      ).then(response =>{
          alert("Created the new loan!");
      }).catch(error =>{
          console.log(error);
      })
  },
  logout(){
    axios.post('/api/logout')
    .then(response => window.location.href="http://localhost:8080/index.html")
    .catch(error => window.alert("Ha ocurrido un error: " + error.message))
}
  },
}).mount('#app')