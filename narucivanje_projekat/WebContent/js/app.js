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
const PrikazJednogRestorana = {template : '<prikazi-restoran></prikazi-restoran>'}
const PocetnaMenadzer = {template : '<pocetna-menadzer></pocetna-menadzer>'}
const DodajArtikal = {template : '<dodaj-artikal></dodaj-artikal>'}
const Korpa = {template : '<korpa></korpa>'}
const PorudzbineMenadzer = {template : '<porudzbine-menadzer></porudzbine-menadzer>'}
const PocetnaDostavljac = {template : '<pocetna-dostavljac></pocetna-dostavljac>'}
const PorudzbineOdobravanje = {template : '<porudzbineZaOdobravanje></porudzbineZaOdobravanje>'}
const KupacPorudzbine = {template : '<kupac-porudzbine></kupac-porudzbine>'}
const KomentariMenadzer = {template : '<komentarMenadzer></komentarMenadzer>'}





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
		{ path: '/pocetna/admin/prikazi-korisnike', component: PrikaziKorisnikeAdmin},
		{ path: '/restorani/:id', component: PrikazJednogRestorana},
		{ path: '/pocetna/menadzer', component: PocetnaMenadzer},
	    { path: '/pocetna/menadzer/dodaj-artikal/:id', component: DodajArtikal},
		{ path: '/pocetna/korpa', component: Korpa},
		{ path: '/pocetna/menadzer/porudzbine', component: PorudzbineMenadzer},
		{ path: '/pocetna/dostavljac', component: PocetnaDostavljac},
		{ path: '/pocetna/menadzer/odobravanje', component: PorudzbineOdobravanje},
		{ path: '/pocetna/porudzbine', component: KupacPorudzbine},
		{ path: '/pocetna/menadzer/komentari', component: KomentariMenadzer}


	  ]
});

var app = new Vue({
	router,
	el: '#index'
});