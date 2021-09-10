Vue.component("porudzbineZaOdobravanje", {
    data: function () {
        return {
          korisnik : {korisnik : null, uloga : null},
          menadzer : {restoranId : null},
          porudzbine : {dostavljac : [], porudzbina : null},
          porudzbinaDostavljac : {idDostavljaca : null, idPorudzbine : null}
        }
    },

    mounted: function() {
        axios.get(`rest/currentUser`).
        then(response => { this.korisnik = response.data;
            axios.get(`/narucivanje/rest/menadzeri/pronadjiMenadzera/${this.korisnik.korisnik.idKorisnika}`).
            then(response => {this.menadzer = response.data;
            axios.get(`/narucivanje/rest/porudzbine/dobaviSvePorudzbineZaOdobrenjeDostavljaca/${this.menadzer.restoranId}`).
            then(response1 => this.porudzbine = response1.data)})
        });
    },

    computed : {
        dostavljaci(){
            axios.get(`rest/currentUser`).
            then(response => { this.korisnik = response.data;
                axios.get(`/narucivanje/rest/menadzeri/pronadjiMenadzera/${this.korisnik.korisnik.idKorisnika}`).
                then(response => {this.menadzer = response.data;
                axios.get(`/narucivanje/rest/porudzbine/dobaviSvePorudzbineZaOdobrenjeDostavljaca/${this.menadzer.restoranId}`).
                then(response1 => this.porudzbine = response1.data)})
            }); 
            return this.porudzbine;
        }
    },


    template : `
    <form>
        <nav class="navbar navbar-light bg-dark">
            <div class="container-fluid">
            <button class="btn btn-dark" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
            <i class="fa fa-bars"></i>
            </button>
          
                <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a> 
                <div class="ml-auto ">
                    <button class="btn btn-outline-success" type="submit">Odjavite se</button>
                </div>
            </div>
        </nav>

        <div v-for="narudzbina in dostavljaci">
        <div v-for="dostavljac1 in narudzbina.dostavljac">
            <ol class="list-group list-group-numbered" style="width: 18rem; display:inline-block; margin:0.3%;">
                <li class="list-group-item">id porudžbine: {{narudzbina.porudzbina.idPorudzbine}}</li>
                <li class="list-group-item">Dostavljač: {{dostavljac1.ime}} {{dostavljac1.prezime}}</li>
                <button class="btn btn-outline-success" v-on:click="odobriPorudzbinuDostavljacu(dostavljac1.idKorisnika, narudzbina.porudzbina.idPorudzbine)" type="submit">Prihvati dostavljača</button>
            </ol>
        </div>
        </div>
    </form>
    `,

    methods: {
      odobriPorudzbinu : function(id){
          axios.post(`/narucivanje/rest/porudzbine/odobriPorudzbinu/${id}`);
      },
      cekaDostavljaca : function(id){
        axios.post(`/narucivanje/rest/porudzbine/cekaDostavljaca/${id}`);
      },
      odobriPorudzbinuDostavljacu : function(idKorisnik, idPorudzbina){
          this.porudzbinaDostavljac.idDostavljaca = idKorisnik;
          this.porudzbinaDostavljac.idPorudzbine = idPorudzbina;
          axios.post(`/narucivanje/rest/porudzbine/odobriPorudzbinuDostavljacu`, this.porudzbinaDostavljac);
      }
    }
});