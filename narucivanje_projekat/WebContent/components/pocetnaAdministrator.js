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
            <i class="fa fa-bars">
            </button>
          
            <div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
                <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasExampleLabel">Offcanvas</h5>
                <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                </div>
                <div class="offcanvas-body">
                <div>
                    Some text as placeholder. In real life you can have the elements you have chosen. Like, text, images, lists, etc.
                </div>
                <div class="dropdown mt-3">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown">
                    Dropdown button
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <li><a class="dropdown-item" href="#">Action</a></li>
                    <li><a class="dropdown-item" href="#">Another action</a></li>
                    <li><a class="dropdown-item" href="#">Something else here</a></li>
                    </ul>
                </div>
                </div>
            </div>
                <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a> 
                <div class="ml-auto ">
                    <label class="offset-md-0.2" style="color:white">Dobrodosli, {{korisnik.ime}}</label>
                    <button class="btn btn-outline-success" v-on:click="profilAdministrator" type="submit">Moj profil</button>
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
        }
    }
});