Vue.component("prikazi-restoran", {
    data : function(){
        return {
            restoran : {naziv : null, tipRestorana: null, status: null, logoRestorana: null, artikli: []},
            id : null
        }
    },

    mounted(){
        this.id = this.$route.params.id;
        console.log(this.id);
        axios.get('/narucivanje/rest/restorani/nadjiRestoran/' + this.id).
        then(response => this.restoran = response.data);
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
            {{restoran.naziv}}A
            <p>{{restoran.tipRestorana}}B</p>
            <p v-if="restoran.status == true">Otvoren</p>
            <p v-else>Zatvoren</p>
            <p>Dodati lokaciju, ocenu, komentare</p>
            <p>Artikli:</p>
        </form>

    `

});