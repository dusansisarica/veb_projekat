Vue.component("pocetna-menadzer", {
    data: function () {
        return {
          korisnik : {korisnik : null, uloga : null},
          menadzer : {restoranId : null},
          restoran : {id : null, naziv : null, tipRestorana : null, logoRestorana : null}
        }
    },

    mounted: function() {
    
		  axios
		    .get(
		      `rest/currentUser`
		    )
		    .then(response => {
		      this.korisnik = response.data;		
		      axios
		        .get(
		         `/narucivanje/rest/menadzeri/pronadjiMenadzera/${this.korisnik.korisnik.idKorisnika}`
		        )
		        .then(response => {
		          this.menadzer = response.data;
		           axios
			        .get(
			         `/narucivanje/rest/restorani/nadjiRestoran/${this.menadzer.restoranId}`			      
			        )
			        .then(response => {
			          this.restoran = response.data;
			        })
			        .catch(err => {
			          console.log(err);
			        });
		      	  })
		        .catch(err => {
		          console.log(err);
		        });
		    });
    },

    template: `
    <form>
        <nav class="navbar navbar-light bg-dark">
            <div class="container-fluid">
            <button class="btn btn-dark" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
            <i class="fa fa-bars"></i>
            </button>
          
                <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a> 
                <div class="ml-auto ">
                    <label class="offset-md-0.2" style="color:white">Dobrodosli, {{korisnik.korisnik.ime}}</label>
                    <button class="btn btn-outline-success" type="submit">Odjavite se</button>
                </div>
            </div>
        </nav>
        <div class="card" style="width: 18rem; display:inline-block; margin:0.3%;">
                <img :src="'slike/' + restoran.logoRestorana" class="card-img-top" alt="logo restorana">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">{{restoran.naziv}}</li>
                    <li class="list-group-item">{{restoran.tipRestorana}}</li>
                </ul>
                <div class="card-body">
                    <button class="btn btn-outline-success" v-on:click="dodajArtikal(restoran.id)" type="submit">Dodaj artikal</button>
                </div>
                <div class="card-body">
                    <button class="btn btn-outline-success" v-on:click="pogledajPorudzbine()" type="submit">Pogledaj porudžbine</button>
                </div>
                <div class="card-body">
                    <button class="btn btn-outline-success" v-on:click="pogledajKomentare()" type="submit">Pogledaj komentare</button>
                </div>
        </div>
    </form>
    `,

    methods: {
      dodajArtikal : function(id){
            router.push(`/pocetna/menadzer/dodaj-artikal/${id}`);
        },
        pogledajPorudzbine : function(){
          router.push(`/pocetna/menadzer/porudzbine`);
        },
        pogledajKomentare : function(){
          router.push(`/pocetna/menadzer/komentari`);
        }
    }
});