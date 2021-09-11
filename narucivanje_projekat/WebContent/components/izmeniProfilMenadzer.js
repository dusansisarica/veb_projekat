Vue.component("izmeni-profil-menadzer", {
    data: function () {
        return {
          korisnik : {korisnik : null, uloga : null},
          korisnikRegistracija : {korisnickoIme : null},
          menadzerDTO : {idKorisnika : null, ime : null, prezime : null, datumRodjenja : null, korisnickoIme : null, lozinka : null},
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
        <form>
            <table>
                <tr>
                    <td>Korisničko ime:</td>
                    <td><input type = "text"   v-model="korisnikRegistracija.korisnickoIme"  name="korisnickoIme"></td>
                </tr>
                <tr>
                    <td>Ime:</td>
                    <td><input type="text" v-model="korisnik.korisnik.ime" name="ime"></td>
                </tr>
                <tr>
                    <td>Prezime:</td>
                    <td><input type="text"  v-model="korisnik.korisnik.prezime" name="prezime"></td>
                </tr>
                 <tr>
                    <td>Lozinka:</td>
                    <td><input type = "password"   v-model="korisnikRegistracija.lozinka"  name="lozinka"></td>
                </tr>
                <tr>
                    <td>Datum rođenja:</td>
                    <td>
                        <input type="date" v-model="korisnik.korisnik.datumRodjenja"  
                        min="1900-01-01" max="2021-06-26">
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" v-on:click="izmeniProfil" value="Izmeni profil"></td>
                </tr>
                
            </table>
        </form>
    </form>
    `,
     methods : {
        izmeniProfil : function(){
           this.menadzerDTO.idKorisnika = this.korisnik.korisnik.idKorisnika;
           this.menadzerDTO.ime = this.korisnik.korisnik.ime;
           this.menadzerDTO.prezime = this.korisnik.korisnik.prezime;
           this.menadzerDTO.korisnickoIme = this.korisnikRegistracija.korisnickoIme;
           this.menadzerDTO.datumRodjenja = this.korisnik.korisnik.datumRodjenja;
           this.menadzerDTO.lozinka = this.korisnikRegistracija.lozinka;
           
           event.preventDefault();
           axios.post('/narucivanje/rest/izmeniMenadzera', this.menadzerDTO);
        }
     }
});