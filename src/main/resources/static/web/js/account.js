const { createApp } = Vue
createApp({
  data() {
    return {
      mostrarDatosTransactions: [],
      mostrarDatosCuenta: [],
      cuentas: [],
      transacciones: [],
      datosRecolectadoDecuenta: [],
      clientAutentication:{}

    }
  },
  created() {
    axios.get('/api/clients/current')
      .then(res => {
        let respuestaDatos = res.data
        this.mostrarDatosTransactions = respuestaDatos.accounts
        this.clientAutentication = respuestaDatos
        this.obtenerDatosDeCuentaExpecifica()
        console.log(this.mostrarDatosTransactions);
      }).catch(error => window.alert("Ha ocurrido un error: " + error.response.data));
  },

  methods: {
    obtenerDatosDeCuentaExpecifica() {
      this.datosRecolectadoDecuenta.push(...this.mostrarDatosTransactions) //Se carga un arreglo con el arreglo de la data
      this.recolectorDeID = location.search.split('?id=').join('')//En esta variable se recolecta la el id de la URL
      this.mostrarDatosCuenta = this.datosRecolectadoDecuenta.filter(account => account.idAccount == this.recolectorDeID)
      
      //Se filtra por la cuenta que está	ingresando y se hace un mapeo que arroja las transacciones que hay en la cuenta
      //luego se recorrehaciendo una matrix a los obejetos y se llena un arrego con esas transacciones en el arreglo
      //cuenta, por ultimo se ordena de mayor a menor por fechas de ingreso
      this.transacciones = this.mostrarDatosCuenta.map(x => x.transactions).forEach(x => {
        this.cuentas
        x.forEach(x => {
          
          this.cuentas.push(x)
          
        }
        )
      })
      this.cuentas.sort((a, b) => {
        if (a.dateTransaction > b.dateTransaction) {
          return -1
        } else {
          return 1
        }
        return 0
      })
    },
    dateFormatAccounts(dateInFormart){
      dateInFormart = new Date(dateInFormart).toLocaleDateString();
      return dateInFormart;
    },
    logout(){
      axios.post('/api/logout').then(response => window.location.href="http://localhost:8080/index.html")
      .catch(error => window.alert("Ha ocurrido un error: " + error.response.data))
  }
    },
    computed: {

  },

}).mount('#app')