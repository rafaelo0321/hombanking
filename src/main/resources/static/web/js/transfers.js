const app = Vue.createApp({
    data() {
        return {
            transfersData: [],
            typeAccount: "",
            originNumber: "",
            amount: "",
            description:"",
            destinyNumber: "",
            accountsClients: [],
            transfersDataDos: [],
            clientAutentication: {}
        }
    },
    created() {
        axios.get('/api/clients/current')
            .then(res => {
                let respuestaDatos = res.data
                this.transfersData = respuestaDatos.accounts
                this.clientAutentication = respuestaDatos

            })
    },
    methods: {
        logout() {
            axios.post('/api/logout').then(response => window.location.href = "http://localhost:8080/index.html")
        },
        createTransfer() {
            axios.post('/api/transactions',
                `amount=${this.amount}&description=${this.description}&originNumber=${this.originNumber}&destinyNumber=${this.destinyNumber}`,
                { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => window.location.href = "http://localhost:8080/web/accounts.html", alert("Solicitud enviada"))
                .catch(error => window.alert("Ha ocurrido un error: " + error.response.data))
        },

    }
}).mount("#app")