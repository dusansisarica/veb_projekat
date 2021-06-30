const Pocetna = {template : '<pocetna-strana></pocetna-strana>'}
const Prijava = {template : '<prijava></prijava>'}
const Registracija = {template : '<registracija></registracija>'}
const PocetnaKupac = {template : '<pocetna-kupac></pocetna-kupac>'}
const PocetnaAdmin = {template : '<pocetna-admin></pocetna-admin>'}
const ProfilAdmin = {template : '<profil-admin></profil-admin>'}
const DodajRestoranAdmin = {template : '<dodaj-restoran></dodaj-restoran>'}
const DodajMenadzeraAdmin = {template : '<dodaj-menadzera></dodaj-menadzera>'}
const DodajDostavljacaAdmin = {template : '<dodaj-dostavljaca></dodaj-dostavljaca>'}
const PrikaziKorisnikeAdmin = {template : '<prikazi-korisnike></prikazi-korisnike>'}


const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: Pocetna},
		{ path: '/prijava', component: Prijava},
		{ path: '/registracija', component: Registracija},	
		{ path: '/pocetna', component: PocetnaKupac},
		{ path: '/pocetna/admin', component: PocetnaAdmin},
		{ path: '/pocetna/admin/profil', component: ProfilAdmin},
		{ path: '/pocetna/admin/dodaj-restoran', component: DodajRestoranAdmin},
		{ path: '/pocetna/admin/dodaj-menadzera', component: DodajMenadzeraAdmin},
		{ path: '/pocetna/admin/dodaj-dostavljaca', component: DodajDostavljacaAdmin},
		{ path: '/pocetna/admin/prikazi-korisnike', component: PrikaziKorisnikeAdmin}
	  ]
});

var app = new Vue({
	router,
	el: '#index'
});