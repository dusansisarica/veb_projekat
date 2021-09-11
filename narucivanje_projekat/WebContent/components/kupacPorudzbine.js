Vue.component("kupac-porudzbine", {
    data: function(){
        return {
            korisnik : {korisnik : null, uloga : null},
            korisnikovePorudzbine : {artikalKolicina : [], idNarudzbine : null},
            komentarIndikator : "",
            komentar : {idKorisnika : null, idRestorana : null, tekst : null, ocena : null},
            komentarZaSlanje : {idNarudzbine : null, komentar : null},
            filterTipPorudzbine : ""
        }
    },

    mounted : function(){
        axios.get(`rest/currentUser`).
        then(response => {this.korisnik = response.data;
            axios.get(`rest/porudzbine/dobaviSvePorudzbineKupca/${this.korisnik.korisnik.idKorisnika}`).
            then(response => this.korisnikovePorudzbine = response.data)
        })
    },

    computed : {
        prikaziFormu(){
            if (this.komentarIndikator){
                return true;
            }
            return false;
        },
        filteredResource(){
            if (this.filterTipPorudzbine){
                return this.filtriraniRestorani = this.korisnikovePorudzbine.filter(porudzbina =>
                    porudzbina.idNarudzbine.tipPorudzbine.includes(this.filterTipPorudzbine));
            }
            else{
                return this.korisnikovePorudzbine;
            }
        }
        
    },

    template: `
    <form>
        <nav class="navbar navbar-light bg-dark">
            <div class="ml-auto ">
            <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a>
                <label class="offset-md-0.2" style="color:white">Dobrodosli, {{korisnik.korisnik.ime}}</label>
            </div>
        </nav>

        <div style="margin:0.3%;">
        <select name="filter1" id="filter1">
            <option value="" disabled selected>Prikazi samo:</option>
            <option value="obrada">U obradi</option>
            <option value="uPripremi">U pripremi</option>
            <option value="cekaDostavljaca">Ceka dostavljaca</option>
            <option value="uTransportu">U transportu</option>
            <option value="dostavljena">Dostavljena</option>
            <option value="otkazana">Otkazana</option>
        </select>

        <button class="btn btn-outline-success" v-on:click="pretrazi" type="button" @submit.prevent="add()">Pretrazi</button>
        </div>

        <div style="margin:0.3%;">
            <div v-for="p in filteredResource">
                <ol class="list-group list-group-numbered" style="width: 18rem; display:inline-block; margin:0.3%;" v-for="a in p.artikalKolicina" syle="width : 100px;">
                    <li class="list-group-item">Naziv artikla: {{a.artikal.naziv}}</li>
                    <li class="list-group-item">Količina artikla: {{a.cena}}</li>
                </ol>
                <span>
                <div>
                <ul class="list-group list-group-horizontal"  syle="width : 100px;">
                    <li class="list-group-item">Status : {{p.idNarudzbine.tipPorudzbine}}</li>
                    <li class="list-group-item">Cena : {{p.idNarudzbine.cena}}</li>
                    <button v-if="p.idNarudzbine.tipPorudzbine == 'dostavljena'" class="btn btn-outline-success" v-on:click="otvoriFormuZaKomentar(p.idNarudzbine.idPorudzbine)">Dodaj komentar i oceni restoran</button> 
                    <button v-if="p.idNarudzbine.tipPorudzbine == 'obrada'" class="btn btn-outline-success" v-on:click="otkaziPorudzbinu(p.idNarudzbine.idPorudzbine)"  type="submit">Otkaži porudzbinu</button>

                    </ul>
                <div>
                    <table v-if="p.idNarudzbine.idPorudzbine == komentarIndikator">
                        <tr><td>Unesite komentar: </td><td><textarea v-model="komentar.tekst" ></textarea></td></tr>
                        <tr><td>Ocenite restoran: </td><td>
                        <select id="ocena" v-model="komentar.ocena">
                            <option></option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select></tr>
                        <button class="btn btn-outline-success" v-on:click="posaljiKomentar(p.idNarudzbine.idPorudzbine)">Pošalji komentar</button> 
                    </table>
                </div>
                </div>
                </span>
                <hr>
            </div>
        </div>
    </form>
    `,

    methods:{
        otvoriFormuZaKomentar : function(id){
            this.komentarIndikator = id;
        },
        posaljiKomentar : function(id){
            this.komentarZaSlanje.idNarudzbine = id;
            this.komentar.idKorisnika = this.korisnik.korisnik.idKorisnika;
            this.komentarZaSlanje.komentar = this.komentar;

            axios.post(`rest/komentari/`, this.komentarZaSlanje);
            this.komentarIndikator = "nista";
        },
        otkaziPorudzbinu : function(id){
            axios.post(`rest/porudzbine/otkaziPorudzbinu/${id}`);
        },
        pretrazi : function(){
            this.filterTipPorudzbine = document.getElementById("filter1").value;
        }
    }

});