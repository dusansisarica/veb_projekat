Vue.component("pocetna-admin", {
    data: function () {
        return {
          korisnik : {korisnik : null, uloga : null},
          korisnikIme: ""
        }
    },

    mounted: function() {
            axios.get(`rest/currentUser`).
            then(response => (this.korisnik = response.data)). //router.push('/prijava')).
            catch(response => (this.korisnik = response.data));
    },

    template: `
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
                <button class="btn btn-outline-success" v-on:click="izmeniProfil" type="submit">Izmeni profil</button>
                <div>
                <button class="btn btn-outline-success" v-on:click="dodajMenadzera" type="submit">Dodaj Menadzera</button>
                </div>
                <div>
                <button class="btn btn-outline-success" v-on:click="dodajDostavljaca" type="submit">Dodaj Dostavljaca</button>
                </div>
                <div>
                <button class="btn btn-outline-success" v-on:click="prikaziKorisnike" type="submit">Prikazi Korisnike</button>
                </div>
                </div>
            </div>
                <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a> 
                <div class="ml-auto ">
                    <label class="offset-md-0.2" style="color:white">Dobrodosli, {{korisnik.korisnik.ime}}</label>
                    <button class="btn btn-outline-success" type="submit" v-on:click="odjava" >Odjavite se</button>
                </div>
            </div>
        </nav>
        <button class="btn btn-outline-success" v-on:click="dodajRestoran" type="submit">Dodaj restoran</button>
        <p>POCETNA ZA ADMINA</p>
    </form>
    `,

    methods: {
    	izmeniProfil : function(){
            router.push('/pocetna/admin/izmeni/profil');
        },
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
        prikaziKorisnike : function(){
            router.push('/pocetna/admin/prikazi-korisnike');
        },
        odjava : function(){
            axios.post(`rest/logout`).
            then(router.push('/'));
        }
    }
});