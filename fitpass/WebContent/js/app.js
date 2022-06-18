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
        userLoginDTO: {
	        username: "",
	        password: "",
		},
		user: null,
	},
	created(){
		this.user = JSON.parse(sessionStorage.getItem('user'));
		if(this.user != null) this.mode = this.user.role;
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
		async login(){
			if(this.user != null) return;
			await axios.post('rest/LoginController/login', this.userLoginDTO)
              .then(response => (this.user = response.data))
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
             if(this.user == null) return;
             sessionStorage.setItem('user', JSON.stringify(this.user));
             this.mode= this.user.role;
             this.loginDialog = false;
		}
	},
});