Vue.component("pocetna-strana", {
    data: function(){
        return {
            restoran: {naziv : null, tipRestorana : null, statusRestorana : null, logoRestorana : null}
        }
    },

    mounted : function(){
        axios.get('/narucivanje/rest/restorani').
        then(response => this.restoran = response.data);
    },

    template: `
    <form>
        <nav class="navbar navbar-light bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a>
                <div class="ml-auto">
                <button class="btn btn-outline-success" v-on:click="prijaviteSe" type="submit">Prijavite se!</button>
                <button class="btn btn-outline-success" v-on:click="registrujteSe" type="submit">Registruj se!</button>
                </div>    
            </div>
        </nav>
        <div class="card-deck" style="display:inline-block;">
            <div class="card" style="width: 18rem; display:inline-block; margin:0.3%;" v-for="r in restoran">
                <img :src="'slike/' + r.logoRestorana" class="card-img-top" alt="logo restorana">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">{{r.naziv}}</li>
                    <li class="list-group-item">{{r.tipRestorana}}</li>
                    <li class="list-group-item">{{r.statusRestorana}}</li>
                </ul>
                <div class="card-body">
                    <button class="btn btn-outline-success" v-on:click="pogledajRestoran(r.id)" type="submit">Pogledaj ponudu</button>
                </div>
            </div>
        </div>
    </form>
    `,

    methods:{
        prijaviteSe : function(){
            router.push(`/prijava`);
        },
        registrujteSe : function(){
            router.push('/registracija');
        },
        pogledajRestoran : function(id){
            router.push(`/restorani/${id}`);
        }
    }

});