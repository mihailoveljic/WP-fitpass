const MyHeader = { template: '<my-header></my-header>' }
const MyFooter = { template: '<my-footer></my-footer>' }
const SportsFacilities = { template: '<sports-facilities></sports-facilities>' }

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{path: '/', component: SportsFacilities}
	  ]
});

var app = new Vue({
	router,
	el: '#app',
	vuetify: new Vuetify(),
	data: {
		mode: "GUEST",
		appBarLinks: [
			'Home',
			'Sport Facilities',
			'Programs',
			'Trainers',
		],
		drawer: false,
		group: null,
		registerDialog: false,
		loginDialog: false,
		activePicker: null,
		date: null,
		menu: false,
        radios: null,
	},
	watch: {
		menu(val) {
			val && setTimeout(() => (this.activePicker = 'YEAR'))
		},
	},
	methods: {
		save(date) {
			this.$refs.menu.save(date)
		},
	},
});