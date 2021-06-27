Vue.component("pocetna-strana", {
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