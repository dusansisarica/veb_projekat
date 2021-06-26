Vue.component("edit-product", {
	data: function () {
		    return {
		      product: {id: '', name:null, price:null}
		    }
	},
	template: ` 
<div>
	<h3>Dodaj proizvod</h3>
	<form>
		<label>Ime</label>
		<input type = "text" v-model = "product.name" name = "name">
		<label>Cena</label>
		<input type = "number" v-model = "product.price" name = "price">
		<input type = "submit" v-on:click = "addProduct" value = "Dodaj">
	</form>
</div>		  
`
	, 
	methods : {
		addProduct : function () {
			event.preventDefault();
				axios.post('rest/products', this.product).
				then(response => (router.push(`/`)));
		}
	}
});