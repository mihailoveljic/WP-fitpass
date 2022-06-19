const SportsFacilities = { template: '<sports-facilities></sports-facilities>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{path: '/sports-facilities', name: 'sports-facilities', component: SportsFacilities}
	  ]
});

var app = new Vue({
	router,
	el: '#app',
	vuetify: new Vuetify(),
	data: {
		mode: "GUEST",
		appBarLinks: [
			{
				name:'Home',
				link: '/'
			},
			{
				name:'Sport Facilities',
				link: '/sports-facilities'
			},
			{
				name:'Programs',
				link: '/programs'
			},
			{
				name:'Trainers',
				link: '/trainers'
			}
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
		userRegistrationDTO: {
			username: "",
			password:"",
			name:"",
			surname:"",
			gender: null,
			dateOfBirth : {
				year: null,
				month: null,
				day: null
			}
		},
		userToken: null
	},
	created(){
		this.userToken = JSON.parse(sessionStorage.getItem('userToken'));
		if(this.userToken != null) this.mode = this.userToken.role;
	},
	watch: {
		menu(val) {
			val && setTimeout(() => (this.activePicker = 'YEAR'))
		},
	},
	computed:{
		isGuest(){
			return this.mode == "GUEST";
		},
	},
	methods: {
		save(date) {
			this.$refs.menu.save(date);
		},
		login(){
			if(this.userToken != null) return;
			axios.post('rest/LoginController/login', this.userLoginDTO)
              .then(response => {
				this.userToken = response.data
              	if(this.userToken == null) return;
	            sessionStorage.setItem('userToken', JSON.stringify(this.userToken));
	            this.mode= this.userToken.role;
	            this.loginDialog = false;
              }
              )
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
		},
		logout(){
			axios.get('rest/LoginController/logout')
              .then(response => (this.checkForLogout(response.data)))
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
		},
		checkForLogout(isLoggedout){
			if(!isLoggedout) return;
             sessionStorage.removeItem('userToken');
             this.userToken = null;
             this.mode = "GUEST";
		},
		register(){
			let temp = this.date.split('-');
			this.userRegistrationDTO.dateOfBirth.year = temp[0];
			this.userRegistrationDTO.dateOfBirth.month = temp[1];
			this.userRegistrationDTO.dateOfBirth.day = temp[2];
			axios.post('rest/RegisterController/registerBuyer', this.userRegistrationDTO)
              .then(response => {
				this.userToken = response.data
              	if(this.userToken == null) return;
	            sessionStorage.setItem('userToken', JSON.stringify(this.userToken));
	            this.mode= this.userToken.role;
	            this.registerDialog = false;
              }
              )
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
		}
	}
});