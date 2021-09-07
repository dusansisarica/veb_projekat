Vue.component("prikazi-restoran", {
    data : function(){
        return {
            restoran : {naziv : null, tipRestorana: null, status: null, logoRestorana: null, artikli: []},
            id : null,
            korisnik : {korisnik : null, uloga : null},
            tip : null,
            uloga : null,
            narudzbina : {korisnikId : null, artikalId : null, kolicina : null}
        }
    },

    mounted(){
        this.id = this.$route.params.id;
        console.log(this.id);
        axios.get('/narucivanje/rest/restorani/nadjiRestoran/' + this.id).
        then(response => this.restoran = response.data);
        axios.get(`rest/currentUser`).
            then(response => (this.korisnik = response.data)). //router.push('/prijava')).
            catch(response => (this.korisnik = response.data));
        this.narudzbina.korisnikId = this.korisnik.korisnik.idKorisnika;
    },

    template : `
        <form>
            <nav class="navbar navbar-light bg-dark">
                <div class="container-fluid">
                    <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a>
                    <div class="ml-auto">
                    <button class="btn btn-outline-success"  type="submit">Prijavite se!</button>
                    <button class="btn btn-outline-success"  type="submit">Registruj se!</button>
                    </div>    
                </div>
            </nav>

            <img :src="'slike/' + restoran.logoRestorana"> 
            <p>{{restoran.naziv}}<p>
            <p>{{restoran.tipRestorana}}</p>
            <p v-if="restoran.status == true">Otvoren</p>
            <p v-else>Zatvoren</p>
            <p>Dodati lokaciju, ocenu, komentare</p>
            <p>U ponudi:</p>
            <div class="card-deck" style="display:inline-block;">
            <div class="card" v-for="artikal in this.restoran.artikli" style="width: 18rem; display:inline-block; margin:0.3%;">
            <img :src="'slike/' + artikal.slikaArtikla" class="card-img-top" alt="slika artikla">
                <ul class="list-group list-group-flush" >
                    <li class="list-group-item">{{artikal.naziv}}</li>
                    <li class="list-group-item">{{artikal.tipArtikla}}</li>
                    <li class="list-group-item">Količina: {{artikal.kolicina}}
                    <span v-if="artikal.tipArtikla == 'pice'">
                         ml
                    </span>
                    <span v-else>
                         g
                    </span>
                    </li>
                    <li class="list-group-item">{{artikal.opis}}</li>
                    <li class="list-group-item" >Cena: {{artikal.cena}}</li>
                    <li v-if="korisnik.uloga == 'kupac'" class="list-group-item">
                      
                  			  <td>Kolicina:</td>
                   		      <td><input type = "text" v-model="narudzbina.kolicina"  name="kolicina"></td>
               			 
                        <button class="btn btn-outline-success" v-on:click="naruciArtikal(artikal.idArtikla)"  type="submit">Naruči ovo</button>
                    </li>
                </ul>
            </div>
            </div>
        </form>

    `,
     methods : {
        naruciArtikal : function(idArtikla){
            event.preventDefault();
            this.narudzbina.artikalId = idArtikla;
            this.narudzbina.korisnikId = this.korisnik.korisnik.idKorisnika;
            axios.post('/narucivanje/rest/porudzbine/dodajArtikalUPorudzbinu', this.narudzbina);
        }
    }

});