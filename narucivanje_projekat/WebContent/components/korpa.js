Vue.component("korpa", {
    data: function(){
        return {
           korisnik : {korisnik : null, uloga : null},
           artikli : {artikal : null, kolicina : null},
           promenaArtikla : {idKorisnika : null, idArtikla : null, kolicina : null},
           cena : null,
           artikalZaUklanjanje : {idDostavljaca : null, idPorudzbine : null}
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

    computed : {
        azurirajCenu(){
            axios.get(`rest/porudzbine/dobaviUkupnuCenuKorpe/${this.korisnik.korisnik.idKorisnika}`).
        then(response => this.cena = response.data);
        return this.cena;
        }
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
                    <input type="text" v-bind:id="value.artikal.idArtikla" name="naziv" placeholder="value.cena">
                    <button class="btn btn-outline-success" v-on:click="promeniKolicinu(value.artikal.idArtikla)"  type="submit">Promeni Kolicinu</button>
                    <button class="btn btn-outline-success" v-on:click="ukloni(value.artikal.idArtikla)"  type="submit">Ukloni artikal</button>
                </ul>
            </div>
            </div>
            <p>Cena porudžbine je {{cena}}</p>
             <button class="btn btn-outline-success" v-on:click="kreirajPorudzbinu(korisnik.korisnik.idKorisnika)" id="kol" type="submit">Kreiraj porudzbinu</button>
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
        },
        promeniKolicinu : function(id){
            this.promenaArtikla.idKorisnika = this.korisnik.korisnik.idKorisnika;
            this.promenaArtikla.idArtikla = id;
            this.promenaArtikla.kolicina = document.getElementById(id).value;
            axios.post(`rest/porudzbine/promeniKolicinuArtikla`, this.promenaArtikla);
            axios.get(`rest/porudzbine/dobaviUkupnuCenuKorpe/${this.korisnik.korisnik.idKorisnika}`).
            then(response => this.cena = response.data);  
            axios.get(`rest/porudzbine/dobaviKorisnikovuKorpu/${this.korisnik.korisnik.idKorisnika}`).
            then(response => this.artikli = response.data);  
        },
        promeniCenu : function(){
            return azurirajCenu;
        },
        ukloni : function(id){
            this.artikalZaUklanjanje.idDostavljaca = this.korisnik.korisnik.idKorisnika;
            this.artikalZaUklanjanje.idPorudzbine = id;
            axios.post(`rest/porudzbine/ukloniArtikalIzKorpe`, this.artikalZaUklanjanje);
            axios.get(`rest/porudzbine/dobaviUkupnuCenuKorpe/${this.korisnik.korisnik.idKorisnika}`).
            then(response => this.cena = response.data);  
            axios.get(`rest/porudzbine/dobaviKorisnikovuKorpu/${this.korisnik.korisnik.idKorisnika}`).
            then(response => this.artikli = response.data);  
        }
    }
});