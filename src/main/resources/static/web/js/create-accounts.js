const { createApp } = Vue

createApp({
  data() {
    return {
      mostrarDatosCuenta: [],
      nombreCliente:{},
      arrayContainingLoans:[],
      removeButton:true,
      type_account:"",
      cardColor:""
    
    }
  },
  created() {
    axios.get('/api/clients/current')
    .then(res => {
    let respuestaDatos = res.data
    this.mostrarDatosCuenta = respuestaDatos.accounts
    this.nombreCliente = respuestaDatos
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
    axios.post('/api/clients/current/accounts',`accountType=${this.type_account}&cardColor=${this.cardColor}`,{ headers: { 'content-type': 'application/x-www-form-urlencoded' } })
            .then(response => window.location.href="http://localhost:8080/web/accounts.html",alert("Su cuenta ha sido creada exitozamente..."))
            .catch(error => window.alert("Ha ocurrido un error: " + error.response.data))
  },
  buttonRemove(){
    if (this.mostrarDatosCuenta.length ==3) {
      this.removeButton = false;
    }
}
},
  computed: {
    
  },

}).mount('#app')