const app = Vue.createApp({
    data() {
        return {
            cardsData: [],
            cards: [],
            dataRecolector: [],
            cardFront: "#tarjeta",
            cardType: "",
            color: "",
            removeButton: true,
            ArrayDebito: [],
            ArrayCredito: [],
            remDeb: true,
            remCred: true,
            cardsDataDos: [],
            cardCreate: {}
            
        }

    },
    created() {
        axios.get('/api/clients/current')
            .then(res => {
                let respuestaDatos = res.data
                this.cardsData = respuestaDatos.accounts
                this.cardCreate = respuestaDatos
                this.forTypeCard();
                this.creditRemove()
                this.buttonRemove()
                this.debitRemove()
                console.log(this.cardsData) 
            }).catch(error => window.alert("Ha ocurrido un error: " + error.response.data))
            
    },
    methods: {
        logout() {
            axios.post('/api/logout').then(response => window.location.href = "http://localhost:8080/index.html")
            .catch(error => window.alert("Ha ocurrido un error: " + error.response.data))
        },
        createCards() {
            axios.post('/api/clients/current/cards',
                `cardType=${this.cardType}&color=${this.color}`,
                { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => window.location.href = "http://localhost:8080/web/cards.html", alert("request sent"))
                .catch(error => window.alert("You have created more than one card of the same type and color"),window.location.href = "http://localhost:8080/web/create-cards.html")/*  */
        },
        forTypeCard() {
            this.ArrayDebito = this.cardsData.filter(card => card.typeCard === "DEBITO");
            this.ArrayCredito = this.cardsData.filter(card => card.typeCard === "CREDITO");

        },

        buttonRemove() {
            if (this.cardsData.length == 6) {
                this.removeButton = false;
            }
        },
        creditRemove() {
            if (this.ArrayCredito.length == 3) {
                this.remCred = false;
            }
        }, debitRemove() {
            if (this.ArrayDebito.length == 3) {
                this.remDeb = false;
            }
        }

    }
}).mount("#app")