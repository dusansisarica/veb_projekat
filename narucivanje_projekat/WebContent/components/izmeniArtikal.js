Vue.component("izmeni-artikal", {
    data: function () {
        return {
          id: null,
          artikalDTO : {id : null, naziv : null, cena : null, tipArtikla : null, kolicina : null, opis : null, slikaArtikla : null},
          artikal : {naziv : null, cena : null, tipArtikla : null, kolicina : null, opis : null, slika : null},
        }
    },

    mounted : function(){
    	this.id = this.$route.params.id;
        axios.get('rest/artikli/' + this.id).
        then(response => this.artikal = response.data);
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
                    <td>Naziv:</td>
                    <td><input type = "text"   v-model="this.artikal.naziv" id="naziv"  name="naziv"></td>
                </tr>
                <tr>
                    <td>Cena:</td>
                    <td><input type="text" v-model="this.artikal.cena" id="cena" name="cena"></td>
                </tr>
                <tr>
                    <td>Kolicina:</td>
                    <td><input type="text"  v-model="this.artikal.kolicina" id="kolicina" name="kolicina"></td>
                </tr>
                 <tr>
                    <td>Opis:</td>
                    <td><input type="text"  v-model="this.artikal.opis" id="opis" name="opis"></td>
                </tr>
                <tr>
                    <td>Slika artikla:</td>
                    <td><input type="file" class="form-control" id="inputGroupFile01"></td>
                </tr>
                <tr>
                    <td>Tip artikla:</td>
                    <td>
                    <select v-if="artikal.tipArtikla == 'jelo'" @change="onChange($event)">
                    <option  value=1 selected>Hrana</option> 
                    <option value=2>Pice</option>
                 	</select>
                 	<select v-if="artikal.tipArtikla == 'pice'" @change="onChange($event)">
                    <option  value=1>Hrana</option> 
                    <option value=2 selected>Pice</option>
                 	</select>
                    </td>
                </tr>
                
                <tr>
                    <td><input type="submit" v-on:click="izmeniArtikal" value="Izmeni artikal"></td>
                </tr>
                
            </table>
        </form>
    </form>
    `,
     methods : {
        onChange : function(event){
            console.log(event.target.value);
            if (event.target.value == 1){
                this.artikalDTO.tipArtikla = 0;
            }
            else{
                this.artikalDTO.tipArtikla = 1;
            }
        },izmeniArtikal : function(){
           this.artikalDTO.id = this.id;
           this.artikalDTO.naziv = document.getElementById("naziv").value;
           this.artikalDTO.cena = document.getElementById("cena").value;
           this.artikalDTO.kolicina = document.getElementById("kolicina").value;
           this.artikalDTO.slikaArtikla = document.getElementById("inputGroupFile01").files[0].name;
           this.artikalDTO.opis = document.getElementById("opis").value;
           
           event.preventDefault();
           axios.post('/narucivanje/rest/artikli/izmeniArtikal', this.artikalDTO);
        }
     }
});