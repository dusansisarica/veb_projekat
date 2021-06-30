Vue.component("pocetna-admin", {
    data: function () {
        return {
          korisnik : {ime : null},
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
                <div>
                <button class="btn btn-outline-success" v-on:click="dodajMenadzera" type="submit">Dodaj Menadzera</button>
                </div>
                </div>
            </div>
                <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a> 
                <div class="ml-auto ">
                    <label class="offset-md-0.2" style="color:white">Dobrodosli, {{korisnik.ime}}</label>
                    <button class="btn btn-outline-success" type="submit">Odjavite se</button>
                </div>
            </div>
        </nav>
        <button class="btn btn-outline-success" v-on:click="dodajRestoran" type="submit">Dodaj restoran</button>
        <p>POCETNA ZA ADMINA</p>
    </form>
    `,

    methods: {
        profilAdministrator : function(){
            router.push('/pocetna/admin/profil');
        },
        dodajRestoran : function(){
            router.push('/pocetna/admin/dodaj-restoran');
        },
        dodajMenadzera : function(){
            router.push('/pocetna/admin/dodaj-menadzera');
        }
    }
});