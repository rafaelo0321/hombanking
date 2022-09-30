const app = Vue.createApp({
    data() {
        return {
            cardsData: [],
            cards: [],
            dataRecolector: [],
            cardFront: "#tarjeta",
            cardType: "",
            color: "",
            removeButton:true,
            clientAutentication: {},
            rd : []
            
        }

    },
    created() {
      
            axios.get('/api/clients/current')
            .then(res => {
                let respuestaDatos = res.data
                this.cardsData = respuestaDatos.cards
                this.clientAutentication = respuestaDatos
                this.dataRecolector = respuestaDatos
                this.rdata()
                
            }).catch(error => window.alert("Ha ocurrido un error: " + error.response.data))
        
        

    },
    methods: {
        dateFormat(formart) {
            formart = new Date(formart).toLocaleDateString();
            return formart;
        },
        //Funsiones que toman una fecha y la convierten a mes y año
        dataFormatCardsMonth(dateCards) {
            let newDateCards = new Date(dateCards).getMonth()
            return newDateCards
        },
        dataFormatCardsYear(dateCards) {
            let newDateCards = new Date(dateCards).getFullYear() - (2000)
            return newDateCards
        },
        logout() {
            axios.post('/api/logout').then(response => window.location.href = "http://localhost:8080/index.html")
        },
        buttonRemove() {
            if (this.cardsData.length == 6) {
                this.removeButton = false;
            }
        },
        closeCard(number){
            let numberCard = number;
            axios.patch('/api/cards',`number=${numberCard}`).then(response => window.location.href = "http://localhost:8080/web/cards.html",alert("Se eliminó la card #"+numberCard))
        },
        rdata(){
            
            this.cards = this.dataRecolector.accounts.map(card => card.card)
            
        }

    },
}).mount("#app")