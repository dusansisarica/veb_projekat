const Product = { template: '<edit-product></edit-product>' }
const Products = { template: '<products></products>' }
const Pocetna = {template : '<pocetna-strana></pocetna-strana>'}
const Prijava = {template : '<prijava></prijava>'}
const Registracija = {template : '<registracija></registracija>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: Pocetna},
		{ path: '/prijava', component: Prijava},
		{ path: '/registracija', component: Registracija},
	    { path: '/products/:id', component: Product}	
	  ]
});

var app = new Vue({
	router,
	el: '#index'
});