Vue.component("porudzbine-menadzer", {
    data: function () {
        return {
          korisnik : {korisnik : null, uloga : null},
          menadzer : {restoranId : null},
          porudzbine : {artikalKolicina : null, idNarudzbine : null},
          status : null
        }
    },

    mounted: function() {
        axios.get(`rest/currentUser`).
        then(response => { this.korisnik = response.data;
            axios.get(`/narucivanje/rest/menadzeri/pronadjiMenadzera/${this.korisnik.korisnik.idKorisnika}`).
            then(response1 => this.menadzer = response1.data);
            axios.get(`/narucivanje/rest/porudzbine/dobaviSvePorudzbineRestorana/${this.korisnik.korisnik.idKorisnika}`).
            then(response => this.porudzbine = response.data)
        })
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
        <div v-for="narudzbina in this.porudzbine">
        <div>
            <ol class="list-group list-group-numbered" style="width: 18rem; display:inline-block; margin:0.3%;" v-for="porudzbina in narudzbina.artikalKolicina" syle="width : 100px;">
                <li class="list-group-item">{{porudzbina.artikal.naziv}}</li>
                <li class="list-group-item">{{porudzbina.artikal.cena}}</li>
                <li class="list-group-item">{{porudzbina.kolicina}}</li>
            </ol>
        </div>
            <button class="btn btn-outline-success" type="submit" v-on:click="odobriPorudzbinu(narudzbina.idNarudzbine)">Odobri porudžbinu</button>
            <button class="btn btn-outline-success" type="submit" v-on:click="cekaDostavljaca(narudzbina.idNarudzbine)">Traži dostavljače</button>
        </div>
    </form>
    `,

    methods: {
      odobriPorudzbinu : function(id){
          axios.post(`/narucivanje/rest/porudzbine/odobriPorudzbinu/${id}`);
      },
      cekaDostavljaca : function(id){
        axios.post(`/narucivanje/rest/porudzbine/cekaDostavljaca/${id}`);
    }
    }
});