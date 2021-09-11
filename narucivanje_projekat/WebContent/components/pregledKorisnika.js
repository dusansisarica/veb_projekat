Vue.component("prikazi-korisnike", {
    data : function(){
        return {
            sviKorisnici : null,
            korisnik : null,
            parametarPretrage : "",
            parametarPretragePrezime : "",
            parametarPretrageKorIme : "",
            pretrazeniKorisnici : null,
            opcijaSortiranja : null,
            filterUloga : null,
            filtriraniKorisnici : null,
            korisnikZaProveru : {korisnik : null, uloga : null}
        }
    },

    created(){
        axios.get(`rest/`).
        then(response => this.sviKorisnici = response.data);
        for(value in this.sviKorisnici){
            this.korisnik = value.korisnik;
        }
        
    },

    computed: {
        filteredResources(){
            if (this.parametarPretrage){
                this.pretrazeniKorisnici = this.sviKorisnici.filter(korisnik =>
                    korisnik.korisnik.ime.toLowerCase().includes(this.parametarPretrage));
                // axios.get(`rest/pretraga?ime=${this.parametarPretrage}`).
                // then(response => this.pretrazeniKorisnici = response.data);
                // for(value in this.sviKorisnici){
                // this.korisnik = value.korisnik;
                // }
                // return this.pretrazeniKorisnici;
            }
            if (this.parametarPretragePrezime){
                this.pretrazeniKorisnici = this.pretrazeniKorisnici.filter(korisnik =>
                    korisnik.korisnik.prezime.toLowerCase().includes(this.parametarPretragePrezime));
            }
            if (this.parametarPretrageKorIme){
                this.pretrazeniKorisnici = this.pretrazeniKorisnici.filter(korisnik =>
                    korisnik.korisnik.kor.toLowerCase().includes(this.parametarPretragePrezime));
            }
                // axios.get(`rest/`).
                // then(response => this.sviKorisnici = response.data);
                // for(value in this.sviKorisnici){
                //     this.korisnik = value.korisnik;
                // }
            if (this.parametarPretrage || this.parametarPretragePrezime){
                switch(this.opcijaSortiranja){
                    case "imeRastuce":
                        this.pretrazeniKorisnici.sort((a, b) => a.korisnik.ime.toLowerCase() > b.korisnik.ime.toLowerCase() ? 1 : -1);
                        break;
                    case "imeOpadajuce":
                        this.pretrazeniKorisnici.sort((a, b) => a.korisnik.ime.toLowerCase() < b.korisnik.ime.toLowerCase() ? 1 : -1);
                        break;
                    case "przRastuce":
                        this.pretrazeniKorisnici.sort((a, b) => a.korisnik.prezime.toLowerCase() > b.korisnik.prezime.toLowerCase() ? 1 : -1);
                        break;
                    case "przOpadajuce":
                        this.pretrazeniKorisnici.sort((a, b) => a.korisnik.prezime.toLowerCase() < b.korisnik.prezime.toLowerCase() ? 1 : -1);
                        break;
                }
                if(this.filterUloga){
                        return this.filtriraniKorisnici = this.pretrazeniKorisnici.filter(korisnik =>
                            korisnik.uloga.toLowerCase().includes(this.filterUloga));
                }
            }
            if(!this.parametarPretrage && !this.parametarPretragePrezime){
                switch(this.opcijaSortiranja){
                    case "imeRastuce":
                        return this.sviKorisnici.sort((a, b) => a.korisnik.ime.toLowerCase() > b.korisnik.ime.toLowerCase() ? 1 : -1);
                        break;
                    case "imeOpadajuce":
                        return this.sviKorisnici.sort((a, b) => a.korisnik.ime.toLowerCase() < b.korisnik.ime.toLowerCase() ? 1 : -1);
                        break;
                    case "przRastuce":
                        return this.sviKorisnici.sort((a, b) => a.korisnik.prezime.toLowerCase() > b.korisnik.prezime.toLowerCase() ? 1 : -1);
                        break;
                    case "przOpadajuce":
                        return this.sviKorisnici.sort((a, b) => a.korisnik.prezime.toLowerCase() < b.korisnik.prezime.toLowerCase() ? 1 : -1);
                        break;
                }
                if(this.filterUloga){
                    return this.filtriraniKorisnici = this.sviKorisnici.filter(korisnik =>
                        korisnik.uloga.toLowerCase().includes(this.filterUloga));
            }
                return this.sviKorisnici;
            }
            return this.pretrazeniKorisnici;
        }
    },

    template : `
    <form>
        <nav class="navbar navbar-light bg-dark">
            <div class="container-fluid">
                <button class="btn btn-dark" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample" aria-controls="offcanvasExample">
                <i class="fa fa-bars"></i>
                </button>
            
                <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
                    <div class="offcanvas-header">
                    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                    </div>
                    <div class="offcanvas-body">
                    <div>
                    <button class="btn btn-outline-success" v-on:click="profilAdministrator" type="submit">Moj profil</button>
                    </div>
                    <div>
                    <button class="btn btn-outline-success" v-on:click="dodajMenadzera" type="submit">Dodaj Menadzera</button>
                    </div>
                    <div>
                    <button class="btn btn-outline-success" v-on:click="dodajDostavljaca" type="submit">Dodaj Dostavljaca</button>
                    </div>
                    </div>
                </div>
                    <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a> 
            </div>
        </nav>
        <div>
        <input type="text"  id="ime" name="ime" placeholder="ime korisnika">
        <input type="text"  id="prezime" name="prezime" placeholder="prezime korisnika">
        <input type="text"  id="username" name="username" placeholder="korisnicko ime korisnika">
        <select name="sort" id="sort">
            <option value="" disabled selected>Sortiraj po:</option>
            <option value="imeRastuce">Ime-rastuće</option>
            <option value="imeOpadajuce">Ime-opadajuće</option>
            <option value="przRastuce">Prezime-rastuće</option>
            <option value="przOpadajuce">Prezime-opadajuće</option>
        </select>
        <select name="filter1" id="filter1">
            <option value="" disabled selected>Prikazi samo:</option>
            <option value="kupac">Kupce</option>
            <option value="menadzer">Menadžere</option>
            <option value="dostavljac">Dostavljače</option>
            <option value="admin">Admine</option>
        </select>
        <button class="btn btn-outline-success" v-on:click="pretrazi" type="button" @submit.prevent="add()">Pretrazi</button>
        </div>
        <div class="card-deck" style="display:inline-block;">
        <div class="card" v-for="value in filteredResources" style="width: 18rem; display:inline-block; margin:0.3%;">
            <ul class="list-group list-group-flush" >
                <li v-if=" value.korisnik.sumnjiviKupac > 3" style="color:red" class="list-group-item">Ime: {{value.korisnik.ime}}</li>
                <li v-else class="list-group-item" >Ime: {{value.korisnik.ime}}</li>

                <li class="list-group-item">Prezime: {{value.korisnik.prezime}}</li>
                <li class="list-group-item">Datum rodjenja: {{value.korisnik.datumRodjenja}}</li>
                <li class="list-group-item">Pol: {{value.korisnik.pol}}</li>
                <li class="list-group-item">Uloga: {{value.uloga}}</li>
                <li class="list-group-item">Obrisan: {{value.korisnik.obrisan}}</li>
            </ul>
            <button class="btn btn-outline-success" v-on:click="obrisi(value.korisnik.idKorisnika)" type="button" @submit.prevent="add()">Izbrisi korisnika</button>
            <button class="btn btn-outline-success" v-on:click="banuj(value.korisnik.idKorisnika)" type="button" @submit.prevent="add()">Banuj korisnika</button>
            </div>
        </div>
    </form>
    `,

    methods : {
        profilAdministrator : function(){
            router.push('/pocetna/admin/profil');
        },
        dodajRestoran : function(){
            router.push('/pocetna/admin/dodaj-restoran');
        },
        dodajMenadzera : function(){
            router.push('/pocetna/admin/dodaj-menadzera');
        },
        dodajDostavljaca : function(){
            router.push('/pocetna/admin/dodaj-dostavljaca');
        },
        pretrazi : function(){
            this.parametarPretrage = document.getElementById("ime").value;
            this.parametarPretrageKorIme = document.getElementById("username").value;
            this.parametarPretragePrezime = document.getElementById("prezime").value;
            this.opcijaSortiranja = document.getElementById("sort").value;
            this.filterUloga = document.getElementById("filter1").value;
            // axios.get(`rest/pretraga?ime=${this.parametarPretrage}`).
            // then(response => (this.sviKorisnici = response.data));//, this.$router.go);
        },
        obrisi : function(id){
            axios.post(`rest/obrisiKorisnika/${id}`);
        }
        
    }
});