Vue.component("pocetna-strana", {
    template: `
    <form>
        <input type="submit" v-on:click="prijaviteSe" value="Prijavite se!">
        <input type="submit" v-on:click="registrujteSe" value="Registruj se!">
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