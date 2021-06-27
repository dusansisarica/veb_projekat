Vue.component("prijava", {
    data: function () {
        return {
          korisnik: {korisnickoIme: null, lozinka: null},
          poruka : ""
        }
    },

    template: `
    <form>
        <nav class="navbar navbar-light bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a> 
            </div>
        </nav>

        <table>
            <tr><td>Username</td><td><input type="text" v-model="korisnik.korisnickoIme" name="username"></td></tr>
            <tr><td>Password</td><td><input type="password" v-model="korisnik.lozinka" name="password"></td></tr>
            <tr><td><input type="submit" v-on:click="validacijaPrijave" value="Prijavite se"></td></tr>
        </table>
        <p>{{poruka}}</p>    
    </form>
    `,
    
    methods: {
        validacijaPrijave : function(){
            event.preventDefault();
            axios.post(`rest/login`, this.korisnik).
            then(response => router.push(response.data)).
            catch(response => (this.poruka = "Pogrešno korisničko ime i/ili lozinka"));
        }
    }

});