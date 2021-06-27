const Pocetna = {template : '<pocetna-strana></pocetna-strana>'}
const Prijava = {template : '<prijava></prijava>'}
const Registracija = {template : '<registracija></registracija>'}
const PocetnaKupac = {template : '<pocetna-kupac></pocetna-kupac>'}
const PocetnaAdmin = {template : '<pocetna-admin></pocetna-admin>'}
const ProfilAdmin = {template : '<profil-admin></profil-admin>'}


const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: Pocetna},
		{ path: '/prijava', component: Prijava},
		{ path: '/registracija', component: Registracija},	
		{ path: '/pocetna', component: PocetnaKupac},
		{ path: '/pocetna/admin', component: PocetnaAdmin},
		{ path: '/pocetna/admin/profil', component: ProfilAdmin}
	  ]
});

var app = new Vue({
	router,
	el: '#index'
});