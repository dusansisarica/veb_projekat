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
        <table>
            <td v-for="r in restoran">
                <tr>{{r.naziv}}</tr>
                <tr>{{r.tipRestorana}}</tr>
                <tr>{{r.statusRestorana}}</tr>
                <img width="42" height="42" :src="'slike/' + r.logoRestorana"/>
            </td>
        </table>
    </form>
    `,

    methods:{
        prijaviteSe : function(){
            router.push(`/prijava`);
        },
        registrujteSe : function(){
            router.push('/registracija');
        }
    }

});