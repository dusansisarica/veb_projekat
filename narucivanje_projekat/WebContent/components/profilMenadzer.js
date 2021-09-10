Vue.component("profil-menadzer", {
    data: function () {
        return {
          korisnik : {korisnik : null, uloga : null},
          korisnikRegistracija : {korisnickoIme : null}
        }
    },

    mounted : function(){
        axios
		    .get(
		      `rest/currentUser`
		    )
		    .then(response => {
		      this.korisnik = response.data;		
		      axios
		        .get(
		         `rest/korisnikRegistracija/${this.korisnik.korisnik.idKorisnika}`
		        )
		        .then(response => {
		          this.korisnikRegistracija = response.data;
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
                <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a> 
            </div>
        </nav>
        <table>
            <p>Ime korisnika: {{korisnik.korisnik.ime}}</p>
            <p>Prezime korisnika: {{korisnik.korisnik.prezime}}</p>
            <p>Korisnicko ime korisnika: {{korisnikRegistracija.korisnickoIme}}</p>
            <p>Datum rodjenja korisnika: {{korisnik.korisnik.datumRodjenja}}</p>
        </table>
    </form>
    `
});