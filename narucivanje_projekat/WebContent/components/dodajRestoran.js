Vue.component("dodaj-restoran", {
    data: function(){
        return{
            tipovi : null,
            restoran : {naziv : null, tipRestorana : null, statusRestorana : null, logoRestorana : null},
            lokacija : {geografskaDuzina : null, geografskaSirina : null, adresa : null},
            restoranLokacija : {restoran : null, lokacija : null}
        }
    },

    mounted: function(){
        axios.get('/narucivanje/rest/restorani/dobavi-tipove').
        then(response => this.tipovi = response.data).
        catch(response => this.tipovi = response.data);
    },

    template: `
    <form>
        <nav class="navbar navbar-light bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a> 
            </div>
        </nav>
        <p>Unesite sledece informacije o novom restoranu:</p>
        <form>
            <table>
                <tr>
                    <td>Naziv restorana:</td>
                    <td><input type="text" v-model="restoran.naziv"></td>
                </tr>
                <tr>
                    <td>Tip restorana:</td>
                    <td><select v-model="restoran.tipRestorana">
                    <option v-for="value in tipovi" :key="value">
                    {{value}}
                    </option>
                    </select></td>
                </tr>
                <tr>
                    <td>Geografska duzina:</td>
                    <td><input type="text" v-model="lokacija.geografskaDuzina"></td>
                </tr>
                <tr>
                    <td>Geografska sirina:</td>
                    <td><input type="text" v-model="lokacija.geografskaSirina"></td>
                </tr>
                <tr>
                    <td>Adresa restorana:</td>
                    <td><input type="text" v-model="lokacija.adresa"></td>
                </tr>
                <tr>
                    <td>Logo restorana:</td>
                    <td><input type="file" class="form-control" id="inputGroupFile01"></td>
                </tr>
            </table>
        </form>
        <input type="submit" v-on:click="dodajRestoran" value="Potvrdi dodavanje">

    </form>
    `,

    methods : {
        dodajRestoran : function(){
            event.preventDefault();
            this.restoran.logoRestorana = document.getElementById("inputGroupFile01").files[0].name; 
            this.lokacija.geografskaDuzina = parseFloat(this.lokacija.geografskaDuzina);
            this.lokacija.geografskaSirina = parseFloat(this.lokacija.geografskaSirina);
            this.restoranLokacija.restoran = this.restoran;
            this.restoranLokacija.lokacija = this.lokacija;
             axios.post('/narucivanje/rest/restorani', this.restoranLokacija).
             then(response => router.push('/pocetna/admin'));
        }
    }
});