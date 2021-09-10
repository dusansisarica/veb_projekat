Vue.component("pocetna-dostavljac", {
    data: function () {
        return {
          korisnik : {korisnik : null, uloga : null},
          porudzbine : {artikalKolicina : null, idNarudzbine : null},
          porudzbineDostavljac : {idDostavljaca : null, idPorudzbine : null},
          dostavljacevePorudzbine : {artikalKolicina : [], idNarudzbine : null},
          odobrenaPorudzbina : {idDostavljaca : null, idPorudzbine : null}
        }
    },

    mounted: function() {
            axios.get(`/narucivanje/rest/currentUser`).
            then(response => {this.korisnik = response.data;
            axios.get(`/narucivanje/rest/porudzbine/dobaviSvePorudzbineKojeNemajuDostavljaca`).
            then(response =>{
                 this.porudzbine = response.data;
                 axios.get(`/narucivanje/rest/porudzbine/dobaviPorudzbineZaKojeJeZaduzenDostavljac/${this.korisnik.korisnik.idKorisnika}`).
                 then(response => this.dostavljacevePorudzbine = response.data)
             })});
    },

    computed : {

    },

    template: `
    <form>
        <nav class="navbar navbar-light bg-dark">
                <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a> 
                <div class="ml-auto ">
                    <label class="offset-md-0.2" style="color:white">Dobrodosli, {{korisnik.korisnik.ime}}</label>
                    <button class="btn btn-outline-success" type="submit" v-on:click="odjava" >Odjavite se</button>
                </div>
        </nav>
        <div>
        <h3>Porudžbine za koje je korisnik zadužen:</h3>
        </div>

        <div v-for="p in this.dostavljacevePorudzbine">
        <div>
            <ol class="list-group list-group-numbered" style="width: 18rem; display:inline-block; margin:0.3%;" v-for="a in p.artikalKolicina" syle="width : 100px;">
                <li class="list-group-item">Naziv artikla: {{a.artikal.naziv}}</li>
                <li class="list-group-item">Količina artikla: {{a.cena}}</li>
            </ol>
        </div>
            <button class="btn btn-outline-success" type="submit" v-on:click="dostavljenaPorudzbina(p.idNarudzbine)">Porudžbina je dostavljena</button>
            <hr>
        </div>


        <div>
        <h3>Porudžbine koje mogu da se prihvate:</h3>
        </div>
        <div v-for="narudzbina in this.porudzbine">
        <div>
            <ol class="list-group list-group-numbered" style="width: 18rem; display:inline-block; margin:0.3%;" v-for="porudzbina in narudzbina.artikalKolicina" syle="width : 100px;">
                <li class="list-group-item">{{porudzbina.artikal.naziv}}</li>
                <li class="list-group-item">{{porudzbina.artikal.cena}}</li>
                <li class="list-group-item">{{porudzbina.cena}}</li>
            </ol>
        </div>
            <button class="btn btn-outline-success" type="submit" v-on:click="prihvatiPorudzbinu(narudzbina.idNarudzbine)">Prihvati porudžbinu</button>
        </div>
        
    </form>
    `,

    methods: {
        odjava : function(){
            axios.post(`rest/logout`).
            then(router.push('/'));
        },
        prihvatiPorudzbinu : function(id){
            this.porudzbineDostavljac.idDostavljaca = this.korisnik.korisnik.idKorisnika;
            this.porudzbineDostavljac.idPorudzbine = id;
            axios.post(`rest/porudzbine/posaljiZahtevZaDostavljaca`, this.porudzbineDostavljac);
        },
        dostavljenaPorudzbina : function(idN){
            this.odobrenaPorudzbina.idDostavljaca = this.korisnik.korisnik.idKorisnika;
            this.odobrenaPorudzbina.idPorudzbine = idN;
            axios.post(`rest/porudzbine/dostavljenaPorudzbina`, this.odobrenaPorudzbina);
        }
    }
});