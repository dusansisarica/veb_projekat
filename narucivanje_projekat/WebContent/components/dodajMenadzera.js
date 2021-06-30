Vue.component("dodaj-menadzera", {
    data : function(){
        return{
            korisnik: {ime: null, prezime: null, pol: false, datumRodjenja : null},
            korisnikRegistracija: {korisnickoIme: null, lozinka: null, uloga: "menadzer"},
            korisnikPomocna: {korisnik: null, korisnikRegistracija: null},
        }
    },

    template: `
    <form>
        <nav class="navbar navbar-light bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a> 
            </div>
        </nav>

        <p>Registruj se:</p>
        <form>
            <table>
                <tr>
                    <td>Korisničko ime:</td>
                    <td><input type = "text" v-model="korisnikRegistracija.korisnickoIme" name="korisnickoIme"></td>
                </tr>
                <tr>
                    <td>Lozinka:</td>
                    <td><input type="password" v-model="korisnikRegistracija.lozinka" name="lozinka"></td>
                </tr>
                <tr>
                    <td>Ime:</td>
                    <td><input type="text" v-model="korisnik.ime" name="ime"></td>
                </tr>
                <tr>
                    <td>Prezime:</td>
                    <td><input type="text" v-model="korisnik.prezime" name="prezime"></td>
                </tr>
                <tr>
                    <td>Pol:</td>
                    <td>
                    <select @change="onChange($event)">
                    <option value=1>Muški</option>
                    <option value=2>Ženski</option>
                  </select>
                    </td>
                </tr>
                <tr>
                    <td>Datum rođenja:</td>
                    <td>
                        <input type="date" v-model="korisnik.datumRodjenja" 
                        min="1900-01-01" max="2021-06-26">
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" v-on:click="dodajMenadzera" value="Prijavite se"></td>
                </tr>
                
            </table>
        </form>
    </form>
    `,

    methods : {
        dodajMenadzera : function(){
            event.preventDefault();
            this.korisnikPomocna.korisnik = this.korisnik;
            this.korisnikPomocna.korisnikRegistracija = this.korisnikRegistracija;
            axios.post('/narucivanje/rest/menadzeri', this.korisnikPomocna).
            then(response => router.push(`/pocetna/admin`));
        }
    }
});