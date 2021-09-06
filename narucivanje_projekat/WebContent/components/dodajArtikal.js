Vue.component("dodaj-artikal", {
    data : function(){
        return{
            artikal: {naziv: null, cena: null, tipArtikla: null, restoran : null, kolicina : null, opis : null, slikaArtikla : null}
        }
    },

    template: `
    <form>
        <nav class="navbar navbar-light bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" style="color:white" href="http://localhost:8080/narucivanje/#/">Početna</a> 
            </div>
        </nav>
        <form>
            <table>
                <tr>
                    <td>Naziv artikla:</td>
                    <td><input type = "text" v-model="artikal.naziv" name="naziv"></td>
                </tr>
                <tr>
                    <td>Cena:</td>
                    <td><input type="text" v-model="artikal.cena" name="cena"></td>
                </tr>
                <tr>
                    <td>Kolicina:</td>
                    <td><input type="text" v-model="artikal.kolicina" name="kolicina"></td>
                </tr>
                <tr>
                    <td>Opis:</td>
                    <td><input type="text" v-model="artikal.opis" name="opis"></td>
                </tr>
                  <tr>
                    <td>Slika artikla:</td>
                    <td><input type="file" class="form-control" id="inputGroupFile01"></td>
                </tr>
                <tr>
                    <td>Tip artikla:</td>
                    <td>
                    <select @change="onChange($event)">
                    <option value=1>Hrana</option>
                    <option value=2>Pice</option>
                  </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" v-on:click="dodajArtikal" value="dodajArtikal"></td>
                </tr>
                
            </table>
        </form>
    </form>
    `,

    methods : {
        dodajArtikal : function(){
          event.preventDefault();
		  this.artikal.restoran = this.$route.params.id;
          this.artikal.slikaArtikla = document.getElementById("inputGroupFile01").files[0].name; 
          axios.post('rest/artikli', this.artikal).
             then(response => router.push('/pocetna/menadzer'));
        },
        onChange : function(event){
            console.log(event.target.value);
            if (event.target.value == 1){
                this.artikal.tipArtikla = 0;
            }
            else{
                this.artikal.tipArtikla = 1;
            }
        }
    }
});