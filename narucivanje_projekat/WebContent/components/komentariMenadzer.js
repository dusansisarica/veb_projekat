Vue.component("komentarMenadzer", {
    data: function(){
        return {
           korisnik : {korisnik : null, uloga : null},
           komentari : {tekst : null, ocena : null, idKorisnika : null, idKomentara : null},
           menadzer : {restoranId : null},
           kupac : {ime : null, prezime : null}
        }
    },

    mounted: function(){
        axios.get(`rest/currentUser`).
        then(response =>{ this.korisnik = response.data;
            axios.get(`/narucivanje/rest/menadzeri/pronadjiMenadzera/${this.korisnik.korisnik.idKorisnika}`).
            then(response => {this.menadzer = response.data;
            axios.get(`rest/komentari/dobaviKomentareZaRestoran/${this.menadzer.restoranId}`).
        then(response => this.komentari = response.data)})});
        
    },

    computed : {
        sviKomentari(){
            axios.get(`rest/komentari/dobaviKomentareZaRestoran/${this.menadzer.restoranId}`).
            then(response => this.komentari = response.data);
            return this.komentari
        }
    }, 

    template: `
    <form>
        <div class="card-deck" style="display:inline-block;">
        <div class="card" v-for="value in sviKomentari"  style="width: 18rem; display:inline-block; margin:0.3%;">
            <ul class="list-group list-group-flush" >
                <li class="list-group-item">Komentar: {{value.tekst}}</li>
                <li class="list-group-item">Ocena: {{value.ocena}}</li>
                <li class="list-group-item">Status: {{value.odobreno}}</li>
            </ul>
            <button class="btn btn-outline-success" v-on:click="odobriKomentar(value.idKomentara)"  type="submit">Odobri komentar</button>
        </div>
        </div>
    </form>
    `,
     methods : {
        odobriKomentar : function(id){
            axios.post(`rest/komentari/odobriKomentar/${id}`);
        }
    }
});