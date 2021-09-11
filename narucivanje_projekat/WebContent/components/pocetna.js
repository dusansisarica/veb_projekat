Vue.component("pocetna-strana", {
    data: function(){
        return {
            restoran: {naziv : null, tipRestorana : null, statusRestorana : null, logoRestorana : null, prosecnaOcena : null},
            parametarPretrageNaziv : "",
            pretrazeniRestorani : null,
            parametarPretrageGrad : "",
            opcijaSortiranja : "",
            filterTipRestorana : "",
            filtriraniRestorani : null,
            korisnik : {korisnik : null, uloga : null}
        }
    },

    mounted : function(){
        axios.get('/narucivanje/rest/restorani').
        then(response => this.restoran = response.data);
        axios.get('/narucivanje/rest/currentUser').
        then(response => this.korisnik = response.data);
    },

    computed : {
        filteredResource(){
            this.restoran.sort((x,y) => y.statusRestorana - x.statusRestorana);
            if (this.parametarPretrageNaziv){
                this.pretrazeniRestorani = this.restoran.filter(restoran =>
                    restoran.naziv.toLowerCase().includes(this.parametarPretrageNaziv));
            }
            if (this.parametarPretrageGrad){
                if (this.parametarPretrageNaziv){
                    this.pretrazeniRestorani = this.pretrazeniRestorani.filter(restoran =>
                        restoran.lokacijaRestorana.adresa.split(",")[1].toLowerCase().includes(this.parametarPretrageGrad.toLowerCase()));
                }
                else
                {
                    this.pretrazeniRestorani = this.restoran.filter(restoran =>
                        restoran.lokacijaRestorana.adresa.split(",")[1].toLowerCase().includes(this.parametarPretrageGrad.toLowerCase()));

                }
            }
            if (this.parametarPretrageNaziv || this.parametarPretrageGrad){
                switch(this.opcijaSortiranja){
                    case "imeRastuce":
                        this.pretrazeniRestorani.sort((a, b) => a.naziv.toLowerCase() > b.naziv.toLowerCase() ? 1 : -1);
                        break;
                    case "imeOpadajuce":
                        this.pretrazeniRestorani.sort((a, b) => a.naziv.toLowerCase() < b.naziv.toLowerCase() ? 1 : -1);
                        break;
                    case "przRastuce":
                        this.pretrazeniRestorani.sort((a, b) => a.lokacijaRestorana.adresa.split(",")[1].toLowerCase() > b.lokacijaRestorana.adresa.split(",")[1].toLowerCase() ? 1 : -1);
                        break;
                    case "przOpadajuce":
                        this.pretrazeniRestorani.sort((a, b) => a.lokacijaRestorana.adresa.split(",")[1].toLowerCase() < b.lokacijaRestorana.adresa.split(",")[1].toLowerCase() ? 1 : -1);
                        break;
                    case "ocenaRastuce":
                        this.pretrazeniRestorani.sort((a, b) => a.prosecnaOcena > b.prosecnaOcena ? 1 : -1);
                        break;
                    case "ocenaOpadajuce":
                        this.pretrazeniRestorani.sort((a, b) => a.prosecnaOcena < b.prosecnaOcena ? 1 : -1);
                        break;
                }
                if (this.filterTipRestorana){
                    if (this.filterTipRestorana == "otvoren"){
                        return this.filtriraniRestorani = this.pretrazeniRestorani.filter(restoran =>
                            restoran.statusRestorana == true);
                    }
                    if (this.filterTipRestorana == "zatvoren"){
                        return this.filtriraniRestorani = this.pretrazeniRestorani.filter(restoran =>
                            restoran.statusRestorana == false);
                    }
                    return this.filtriraniRestorani = this.pretrazeniRestorani.filter(restoran =>
                        restoran.tipRestorana.toLowerCase().includes(this.filterTipRestorana));
                }
            }

            if (!this.parametarPretrageNaziv && !this.parametarPretrageGrad) {
                switch(this.opcijaSortiranja){
                    case "imeRastuce":
                        this.restoran.sort((a, b) => a.naziv.toLowerCase() > b.naziv.toLowerCase() ? 1 : -1);
                        break;
                    case "imeOpadajuce":
                        this.restoran.sort((a, b) => a.naziv.toLowerCase() < b.naziv.toLowerCase() ? 1 : -1);
                        break;
                    case "przRastuce":
                        this.restoran.sort((a, b) => a.lokacijaRestorana.adresa.split(",")[1].toLowerCase() > b.lokacijaRestorana.adresa.split(",")[1].toLowerCase() ? 1 : -1);
                        break;
                    case "przOpadajuce":
                        this.restoran.sort((a, b) => a.lokacijaRestorana.adresa.split(",")[1].toLowerCase() < b.lokacijaRestorana.adresa.split(",")[1].toLowerCase() ? 1 : -1);
                        break;
                    case "ocenaRastuce":
                        this.restoran.sort((a, b) => a.prosecnaOcena > b.prosecnaOcena ? 1 : -1);
                        break;
                    case "ocenaOpadajuce":
                        this.restoran.sort((a, b) => a.prosecnaOcena < b.prosecnaOcena ? 1 : -1);
                        break;
                }
                if (this.filterTipRestorana){
                    if (this.filterTipRestorana == "otvoren"){
                        return this.filtriraniRestorani = this.restoran.filter(restoran =>
                            restoran.statusRestorana);
                    }
                    if (this.filterTipRestorana == "zatvoren"){
                        return this.filtriraniRestorani = this.restoran.filter(restoran =>
                            !restoran.statusRestorana);
                    }
                    return this.filtriraniRestorani = this.restoran.filter(restoran =>
                        restoran.tipRestorana.toLowerCase().includes(this.filterTipRestorana));
                }
                return this.restoran;
            }
            return this.pretrazeniRestorani;
        }
    },

    template: `
    <form>
        <nav class="navbar navbar-light bg-dark">
            <div v-if="!(korisnik.uloga == 'kupac')" class="container-fluid">
                <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a>
                <div class="ml-auto">
                <button class="btn btn-outline-success" v-on:click="prijaviteSe" type="submit">Prijavite se!</button>
                <button class="btn btn-outline-success" v-on:click="registrujteSe" type="submit">Registruj se!</button>
                </div>    
            </div>
            <div v-else class="ml-auto ">
            <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a>
                <button class="btn btn-outline-success" v-on:click="korpa" type="submit">Korpa</button> 
                <button class="btn btn-outline-success" v-on:click="profilKupac" type="submit">Moj profil</button>
                <button class="btn btn-outline-success" v-on:click="izmeniProfil" type="submit">Izmeni profil</button>
                <button class="btn btn-outline-success" v-on:click="porudzbine" type="submit">Pogledaj porudžbine</button> 
                <label class="offset-md-0.2" style="color:white">Dobrodosli, {{korisnik.korisnik.ime}}</label>
                <button class="btn btn-outline-success" type="submit" v-on:click="odjava">Odjavite se</button>
            </div>
        </nav>
        <div style="margin:0.3%;">
        <input type="text"  id="naziv" name="naziv" placeholder="naziv restorana">
        <input type="text"  id="mesto" name="mesto" placeholder="grad">
        <select name="sort" id="sort">
            <option value="" disabled selected>Sortiraj po:</option>
            <option value="imeRastuce">Naziv restorana-rastuće</option>
            <option value="imeOpadajuce">Naziv restorana-opadajuće</option>
            <option value="przRastuce">Lokacija-rastuće</option>
            <option value="przOpadajuce">Lokacija-opadajuće</option>
            <option value="ocenaRastuce">Ocena-rastuće</option>
            <option value="ocenaOpadajuce">Ocena-opadajuće</option>
        </select>
        <select name="filter1" id="filter1">
            <option value="" disabled selected>Prikazi samo:</option>
            <option value="italijanski">Italijanske restorane</option>
            <option value="kineski">Kineske restorane</option>
            <option value="rostilj">Roštilj restorane</option>
            <option value="otvoren">Otvorene restorane</option>
            <option value="zatvoren">Zatvorene restorane</option>


        </select>

        <button class="btn btn-outline-success" v-on:click="pretrazi" type="button" @submit.prevent="add()">Pretrazi</button>
        </div>
        <div class="card-deck" style="display:inline-block;">
            <div class="card" style="width: 18rem; display:inline-block; margin:0.3%;" v-for="r in filteredResource">
                <img :src="'slike/' + r.logoRestorana" class="card-img-top" alt="logo restorana">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">{{r.naziv}}</li>
                    <li class="list-group-item">{{r.tipRestorana}}</li>
                    <li class="list-group-item">{{r.statusRestorana}}</li>
                    <li class="list-group-item">{{r.lokacijaRestorana.adresa}}</li>
                    <li class="list-group-item">{{r.prosecnaOcena}}</li>
                </ul>
                <div class="card-body">
                    <button class="btn btn-outline-success" v-on:click="pogledajRestoran(r.id)" type="submit">Pogledaj ponudu</button>
                </div>
            </div>
        </div>
    </form>
    `,

    methods:{
    	izmeniProfil : function(){
            router.push('/pocetna/kupac/izmeni/profil');
        },
    	profilKupac : function(){
            router.push('/pocetna/kupac/profil');
        },
        prijaviteSe : function(){
            router.push(`/prijava`);
        },
        registrujteSe : function(){
            router.push('/registracija');
        },
        pogledajRestoran : function(id){
            router.push(`/restorani/${id}`);
        },
        pretrazi : function(){
            this.parametarPretrageNaziv = document.getElementById("naziv").value;
            this.parametarPretrageGrad = document.getElementById("mesto").value;
            this.opcijaSortiranja = document.getElementById("sort").value;
            this.filterTipRestorana = document.getElementById("filter1").value;
        },
        odjava : function(){
            axios.post(`rest/logout`).
            then(this.$forceUpdate());
        },
        korpa : function(){
        	 router.push(`pocetna/korpa`);
        },
        porudzbine : function(){
            router.push(`pocetna/porudzbine`);
        }
    }

});