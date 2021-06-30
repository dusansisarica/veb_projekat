Vue.component("dodaj-restoran", {
    data: function(){
        return{
            tipovi : null,
            restoran : {naziv : null, tipRestorana : null, statusRestorana : null, logoRestorana : null},
            lokacija : {geografskaDuzina : null, geografskaSirina : null, adresa : null},
            restoranLokacija : {restoran : null, lokacija : null, menadzer : null},
            slobodniMenadzeri : null,
            menadzer : null,
            isHidden: true,
            korisnik: {ime: null, prezime: null, pol: false, datumRodjenja : null},
            korisnikRegistracija: {korisnickoIme: null, lozinka: null, uloga: "menadzer"},
            korisnikPomocna: {korisnik: null, korisnikRegistracija: null},
        }
    },

    mounted: function(){
        axios.get('/narucivanje/rest/restorani/dobavi-tipove').
        then(response => this.tipovi = response.data);
        axios.get('/narucivanje/rest/menadzeri/slobodni-menadzeri').
        then(response => this.slobodniMenadzeri = response.data);
    },

    template: `
    <form>
        <nav class="navbar navbar-light bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a> 
            </div>
        </nav>
        <p>Unesite sledece informacije o novom restoranu:</p>
        <form>
            <table style="display: inline-block;">
                <tr>
                    <td>Naziv restorana:</td>
                    <td><input type="text" v-model="restoran.naziv"></td>
                </tr>
                <tr>
                    <td>Tip restorana:</td>
                    <td><select v-model="restoran.tipRestorana">
                    <option v-for="value in tipovi" :key="value">
                    {{value}}
                    </option>
                    </select></td>
                </tr>
                <tr>
                    <td>Geografska duzina:</td>
                    <td><input type="text" v-model="lokacija.geografskaDuzina"></td>
                </tr>
                <tr>
                    <td>Geografska sirina:</td>
                    <td><input type="text" v-model="lokacija.geografskaSirina"></td>
                </tr>
                <tr>
                    <td>Adresa restorana:</td>
                    <td><input type="text" v-model="lokacija.adresa"></td>
                </tr>
                <tr>
                    <td>Logo restorana:</td>
                    <td><input type="file" class="form-control" id="inputGroupFile01"></td>
                </tr>
                <tr>
                    <td>Menadzer restorana:</td>
                    <td><select @change="OnChangeMenadzer" >
                    <option></option>
                    <option v-for="value in slobodniMenadzeri" :value="value.idKorisnika">
                    {{value.ime}} {{value.prezime}}
                    </option>
                    </select></td>
                </tr>
            </table>
            <button v-on:click="sakrijPrikazi">Napravi menadzera</button>
                <table style="display: inline-block" v-if="!isHidden">
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
                        <td><input type="submit" v-on:click="dodajMenadzera" value="Dodaj menadzera"></td>
                    </tr>
                
                </table>
        
            </form>
            <button v-on:click="dodajRestoran">Potvrdi dodavanje</button>
    </form>
    `,

    methods : {
        dodajRestoran : function(){
            event.preventDefault();
            this.restoran.logoRestorana = document.getElementById("inputGroupFile01").files[0].name; 
            this.lokacija.geografskaDuzina = parseFloat(this.lokacija.geografskaDuzina);
            this.lokacija.geografskaSirina = parseFloat(this.lokacija.geografskaSirina);
            this.restoranLokacija.restoran = this.restoran;
            this.restoranLokacija.lokacija = this.lokacija;
            this.restoranLokacija.menadzer = this.menadzer;
             axios.post('/narucivanje/rest/restorani', this.restoranLokacija).
             then(response => router.push('/pocetna/admin'));
        },
        OnChangeMenadzer : function(event){
            this.menadzer = event.target.value;
        },
        sakrijPrikazi : function(){
            if (this.isHidden == true){
                this.isHidden = false;
            }
            else{
                this.isHidden = true;
            }
        },
        dodajMenadzera : function(){
            event.preventDefault();
            this.korisnikPomocna.korisnik = this.korisnik;
            this.korisnikPomocna.korisnikRegistracija = this.korisnikRegistracija;
            axios.post('/narucivanje/rest/menadzeri', this.korisnikPomocna);
            this.azurirajMenadzere();
            this.sakrijPrikazi();
        },
        azurirajMenadzere : function(){
            axios.get('/narucivanje/rest/menadzeri/slobodni-menadzeri').
            then(response => this.slobodniMenadzeri = response.data);
        }
    }
});