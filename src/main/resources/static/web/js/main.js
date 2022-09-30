const app = Vue.createApp({
    data() {
        return {
            emailInput:"",
            passwordInput:"",
            nameClient:"",
            lastNameClient:"",
            emailAddressClient:"",
            passwordClient:""
        }
    },
    created() {

    },
    methods: {
        loginSections() {

            axios.post('/api/login', `emailAddress=${this.emailInput}&password=${this.passwordInput}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
            .then(response => window.location.href="http://localhost:8080/web/accounts.html").catch(error => window.alert("Ha ocurrido un error: " + error.response.data))

        },
        userRegister(){
            axios.post('/api/clients',`name=${this.nameClient}&lastName=${this.lastNameClient}&emailAddress=${this.emailAddressClient}&password=${this.passwordClient}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => window.location.href="http://localhost:8080/web/accounts.html").catch(error => window.alert("Ha ocurrido un error: " + error.response.data))
        }
    },
}).mount("#app")