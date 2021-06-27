Vue.component("pocetna-admin", {
    data: function () {
        return {
          korisnik : {ime : null},
          korisnikIme: ""
        }
    },

    mounted: function() {
        event.preventDefault();
            axios.get(`rest/currentUser`).
            then(response => (this.korisnik = response.data)). //router.push('/prijava')).
            catch(response => (this.korisnik = response.data));
    },

    template: `
    <form>
        <nav class="navbar navbar-light bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a> 
                <div class="ml-auto ">
                    <label class="offset-md-0.2" style="color:white">Dobrodosli, {{korisnik.ime}}</label>
                    <button class="btn btn-outline-success" v-on:click="profilAdministrator" type="submit">Moj profil</button>
                    <button class="btn btn-outline-success" type="submit">Odjavite se</button>
                </div>
            </div>
        </nav>
        <p>POCETNA ZA ADMINA</p>
    </form>
    `,

    methods: {
        profilAdministrator : function(){
            router.push('/pocetna/admin/profil');
        }
    }
});