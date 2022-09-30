const { createApp } = Vue

createApp({
  data() {
    return {
      mostrarDatosCuenta: [],
      nombreCliente:{},
      arrayContainingLoans:[],
      removeButton:true,
    
    }
  },
  created() {
    axios.get('/api/clients/current')
    .then(res => {
    let respuestaDatos = res.data
    this.mostrarDatosCuenta = respuestaDatos.accounts
    this.nombreCliente = respuestaDatos
    this.arrayContainingLoans = respuestaDatos.loanClients;
    console.log("Estas son las cuentas ",this.mostrarDatosCuenta);
    console.log("Estas son las loans ",this.arrayContainingLoans);

      this.buttonRemove()
  }).catch(error => window.alert("Ha ocurrido un error: " + error.response.data)); 
  },
  methods: {
    dateFormat(dateInFormart){
        dateInFormart = new Date(dateInFormart).toLocaleDateString();
        return dateInFormart;
    },
    logout(){
      axios.post('/api/logout')
      .then(response => window.location.href="http://localhost:8080/index.html")
      .catch(error => window.alert("Ha ocurrido un error: " + error.message))
  },
  createAccount(){
    axios.post('/api/clients/current/accounts',{ headers: { 'content-type': 'application/x-www-form-urlencoded' } })
            .then(response => window.location.href="http://localhost:8080/web/accounts.html",alert("Su cuenta ha sido creada exitozamente..."))
            .catch(error => window.alert("Ha ocurrido un error: " + error.response.data))
  },
  buttonRemove(){
    if (this.mostrarDatosCuenta.length ==3) {
      this.removeButton = false;
    }  
},
closeAccount(number){
    let numberAccount = number;
    axios.patch('/api/accounts',`number=${numberAccount}`).then(response => window.location.href = "http://localhost:8080/web/accounts.html",alert("Se eliminó la card #"+numberAccount))

}
},
  computed: {
    
  },

}).mount('#app')