Vue.component("prikazi-korisnike", {
    data : function(){
        return {
            sviKorisnici : null,
            korisnik : null
        }
    },

    mounted : function(){
        axios.get('/narucivanje/rest').
        then(response => this.sviKorisnici = response.data);
        for(value in this.sviKorisnici){
            this.korisnik = value.korisnik;
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
        <div class="card-deck" style="display:inline-block;">
        <div class="card" v-for="value in this.sviKorisnici" style="width: 18rem; display:inline-block; margin:0.3%;">
            <ul class="list-group list-group-flush" >
                <li class="list-group-item">Ime: {{value.korisnik.ime}}</li>
                <li class="list-group-item">Prezime: {{value.korisnik.prezime}}</li>
                <li class="list-group-item">Datum rodjenja: {{value.korisnik.datumRodjenja}}</li>
                <li class="list-group-item">Pol: {{value.korisnik.pol}}</li>
                <li class="list-group-item">Uloga: {{value.uloga}}</li>
            </ul>
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
        }
    }
});