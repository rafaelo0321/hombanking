const app = Vue.createApp({
    data() {
        return {
            emailInput:"",
            passwordInput:"",
            nameClient:"",
            lastNameClient:"",
            emailAddressClient:"",
            passwordClient:"",
            showRegisterAndLogin:true,
        }
    },
    created() {

    },
    methods: {
        loginSections() {

            axios.post('/api/login', `emailAddress=${this.emailInput}&password=${this.passwordInput}`, 
            { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
            .then(response =>{
                if(this.emailInput.includes("admin")){
                
                    window.location.href = "http://localhost:8080/web/manager.html"
                }
              else{
                
                window.location.href="http://localhost:8080/web/accounts.html"
                }
            } )
        },
        userRegister(){
            axios.post('/api/clients',`name=${this.nameClient}&lastName=${this.lastNameClient}&emailAddress=${this.emailAddressClient}&password=${this.passwordClient}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => axios.post('/api/login', `emailAddress=${this.emailAddressClient}&password=${this.passwordClient}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
            .then(response => window.location.href="http://localhost:8080/web/accounts.html"))
        },
        showRegister(){
            this.showRegisterAndLogin =!this.showRegisterAndLogin;
        }
    },
}).mount("#app")