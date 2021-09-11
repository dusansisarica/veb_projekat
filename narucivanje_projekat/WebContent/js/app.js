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
const ProfilKupac = {template : '<profil-kupac></profil-kupac>'}
const ProfilMenadzer = {template : '<profil-menadzer></profil-menadzer>'}
const IzmeniProfilKupac = {template : '<izmeni-profil-kupac></izmeni-profil-kupac>'}
const IzmeniProfilMenadzer = {template : '<izmeni-profil-menadzer></izmeni-profil-menadzer>'}
const IzmeniProfilAdmin = {template : '<izmeni-profil-admin></izmeni-profil-admin>'}
const PocetnaDostavljac = {template : '<pocetna-dostavljac></pocetna-dostavljac>'}
const PorudzbineOdobravanje = {template : '<porudzbineZaOdobravanje></porudzbineZaOdobravanje>'}
const KupacPorudzbine = {template : '<kupac-porudzbine></kupac-porudzbine>'}
const KomentariMenadzer = {template : '<komentarMenadzer></komentarMenadzer>'}
const ProfilDostavljac = {template : '<profil-dostavljac></profil-dostavljac>'}
const IzmeniProfilDostavljac = {template : '<izmeni-profil-dostavljac></izmeni-profil-dostavljac>'}
const IzmeniArtikal = {template : '<izmeni-artikal></izmeni-artikal>'}






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
		{ path: '/pocetna/kupac/profil', component: ProfilKupac},
		{ path: '/pocetna/menadzer/profil', component: ProfilMenadzer},
		{ path: '/pocetna/kupac/izmeni/profil', component: IzmeniProfilKupac},
		{ path: '/pocetna/menadzer/izmeni/profil', component: IzmeniProfilMenadzer},
		{ path: '/pocetna/admin/izmeni/profil', component: IzmeniProfilAdmin},
		{ path: '/pocetna/dostavljac', component: PocetnaDostavljac},
		{ path: '/pocetna/menadzer/odobravanje', component: PorudzbineOdobravanje},
		{ path: '/pocetna/porudzbine', component: KupacPorudzbine},
		{ path: '/pocetna/menadzer/komentari', component: KomentariMenadzer},
		{ path: '/pocetna/dostavljac/profil', component: ProfilDostavljac},
		{ path: '/pocetna/dostavljac/izmeni/profil', component: IzmeniProfilDostavljac},
		{ path: '/izmeni/artikal/:id', component: IzmeniArtikal},
		


	  ]
});

var app = new Vue({
	router,
	el: '#index'
});