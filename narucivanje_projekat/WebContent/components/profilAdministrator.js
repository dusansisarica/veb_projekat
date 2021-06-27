Vue.component("profil-admin", {
    data: function () {
        return {
          korisnik : {korisnickoIme : null, ime : null, prezime : null, datumRodjenja : null}
        }
    },

    mounted : function(){
        axios.get('rest/currentUser').
        then(response => this.korisnik = response.data).
        catch(response => this.korisnik = response.data);
    },

    template: `
    <form>
        <nav class="navbar navbar-light bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a> 
            </div>
        </nav>
        <table>
            <p>Ime korisnika: {{korisnik.ime}}</p>
            <p>Prezime korisnika: {{korisnik.prezime}}</p>
            <p>Korisnicko ime korisnika: {{korisnik.korisnickoIme}}</p>
            <p>Datum rodjenja korisnika: {{korisnik.datumRodjenja}}</p>
        </table>
    </form>
    `
});