const { createApp } = Vue

createApp({
  data() {
    return {
      nombreCliente:{},
      arrayContainingLoans:[],
      arrayContainingAccounts:[],
      typeLoans:[],
      loanInteres:[],
      paymentsLoansArray:[],
      form:{
          "id":"",
          "amount":"",
          "payments":"",
          "numberCuentaDestino":""
      },
      Mortgage:[],
      Personnel:[],
      Automotive:[],
      existLoanUno:"",
      existLoanDos:"",


    }
  },
  created() {
    this.getData()
    this.getDataLoan()
    },
  methods: {
    getData(){
        axios.get('/api/clients/current')
    .then(res => {
    let respuestaDatos = res.data
    this.nombreCliente = respuestaDatos
    this.arrayContainingLoans = respuestaDatos.loanClients;
    this.arrayContainingAccounts = respuestaDatos.accounts;
    
    this.existLoanU = this.arrayContainingLoans.map(l => l.loanClient).map(loanClient => loanClient.name)[0];
    this.existLoanD = this.arrayContainingLoans.map(l => l.loanClient).map(loanClient => loanClient.name)[1];
    this.existLoanUno = this.existLoanU.toString()
    this.existLoanDos = this.existLoanD.toString()
    
  }).catch(error => window.alert("Ha ocurrido un error: " + error.response.data));
    },
    getDataLoan(){
        axios.get('/api/loans')
    .then(res => {
        let respuestaDatos = res.data
        this.typeLoans = respuestaDatos
        console.log(this.typeLoans)
        this.paymentsLoansArray = this.typeLoans
        .filter(payments => payments.nameLoan === "Mortgage")
        .map(payments => payments.paymentsLoans)
        .forEach(payment =>{
            this.Mortgage
            payment.forEach(payment => {
                this.Mortgage.push(payment)
            })
        })
        this.paymentsLoansArray = this.typeLoans
        .filter(payments => payments.nameLoan === "Personnel")
        .map(payments => payments.paymentsLoans)
        .forEach(payment =>{
            this.Personnel
            payment.forEach(payment => {
                this.Personnel.push(payment)
            })
        })
        this.paymentsLoansArray = this.typeLoans
        .filter(payments => payments.nameLoan === "Automotive")
        .map(payments => payments.paymentsLoans)
        .forEach(payment =>{
            this.Automotive
            payment.forEach(payment => {
                this.Automotive.push(payment)
            })
        })

    })
    },
    logout(){
      axios.post('/api/logout')
      .then(response => window.location.href="http://localhost:8080/index.html")
      .catch(error => window.alert("Ha ocurrido un error: " + error.message))
  },
  applyForLoan(){

    axios.post('/api/loans',this.form)
            .then(response => window.location.href="http://localhost:8080/web/accounts.html",alert("processing your request..."))
            .catch(error => window.alert("An error has occurred: " + error.response.data))
  },
  formartData(){
    let d = new Date();
    let dataFormat =  d.getDate()+ "-" + (d.getMonth() + 2) + "-" + d.getFullYear();
    return dataFormat

  },
  interest(){
    this.form
    if (this.form.id === 1) {

      return Math.round((this.form.amount * 1.2) /this.form.payments)
    } if (this.form.id === 2) {

      return Math.round((this.form.amount * 1.1) /this.form.payments)
    }if (this.form.id === 3) {

      return Math.round((this.form.amount * 1.15) /this.form.payments)
    }

  },
  interestTotal(){
    this.form
    if (this.form.id === 1) {

      return Math.round(this.form.amount * 1.2)
    } if (this.form.id === 2) {

      return Math.round(this.form.amount * 1.1)
    }if (this.form.id === 3) {

      return Math.round(this.form.amount * 1.15)
    }

  },
  maxAmountLoan(){
    this.form
    if (this.form.id === 1 && this.form.amount > 500000 && this.form.amount <= 1) {

      return (alert("you cannot apply for a loan greater than $500,000"))
    } if (this.form.id === 2 && this.form.amount > 200000 && this.form.amount <= 1) {

      return alert("you cannot apply for a loan greater than $200,000")
    } if (this.form.id === 3 && this.form.amount > 300000 && this.form.amount <= 1) {

      return alert("you cannot apply for a loan greater than $300,000")
    }
  }
},
  computed: {
    
  },

}).mount('#app')