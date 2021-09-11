Vue.component("korpa", {
    data: function(){
        return {
           korisnik : {korisnik : null, uloga : null},
           artikli : {artikal : null, kolicina : null},
           cena : null
        }
    },

    mounted: function(){
        axios.get(`rest/currentUser`).
        then(response =>{ this.korisnik = response.data;
            axios.get(`rest/porudzbine/dobaviKorisnikovuKorpu/${this.korisnik.korisnik.idKorisnika}`).
        then(response => this.artikli = response.data);
            axios.get(`rest/porudzbine/dobaviUkupnuCenuKorpe/${this.korisnik.korisnik.idKorisnika}`).
        then(response => this.cena = response.data)});
        
    },

    template: `
    <form>
        <div v-if="Object.entries(artikli).length">
            <div class="card-deck" style="display:inline-block;">
            <div class="card" v-for="value in this.artikli"  style="width: 18rem; display:inline-block; margin:0.3%;">
                <ul class="list-group list-group-flush" >
                    <li class="list-group-item">Naziv: {{value.artikal.naziv}}</li>
                    <li class="list-group-item">Cena: {{value.artikal.cena}}</li>
                    <li class="list-group-item">Količina: {{value.cena}}</li>
                </ul>
            </div>
            </div>
            <p>Cena porudžbine je {{cena}}</p>
             <button class="btn btn-outline-success" v-on:click="kreirajPorudzbinu(korisnik.korisnik.idKorisnika)"  type="submit">Kreiraj porudzbinu</button>
             </div>
        <div v-else>
            korpa je prazna
        </div>
    </form>
    `,
     methods : {
        kreirajPorudzbinu : function(korisnikId){
           event.preventDefault();
           axios.post(`/narucivanje/rest/porudzbine/${korisnikId}`);
        }
    }
});