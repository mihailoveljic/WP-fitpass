const SportsFacilities = { template: '<sports-facilities></sports-facilities>'}
const BuyersList = { template : '<buyers-list></buyers-list>'}
const ManagersList = {template : '<managers-list></managers-list>'}
const CoachesList = {template : '<coaches-list></coaches-list>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{path: '/sports-facilities', name: 'sports-facilities', component: SportsFacilities},
		{path: '/buyers', name: 'buyers-list', component: BuyersList},
		{path: '/managers', name:'managers-list', component: ManagersList},
		{path: '/coaches', name:'coaches-list', component: CoachesList}
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
		userToken: null,
		isUsernameUnique : true,
		registrationErrorMessages : '',
		loginErrorMessages : '',
		loginFormHasErrors: false,
		registrationFormHasErrors: false
	},
	created(){
		this.userToken = JSON.parse(sessionStorage.getItem('userToken'));
		if(this.userToken != null) {
			this.mode = this.userToken.role;
		this.refreshNavBar();
		}
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
		loginForm () {
	        return {
	         	loginUsername: this.userLoginDTO.username,
				loginPassword:this.userLoginDTO.password
			}
        },
        registrationForm () {
	        return {
	         	registrationUsername: this.userRegistrationDTO.username,
				registrationPassword:this.userRegistrationDTO.password,
				registrationName:this.userRegistrationDTO.name,
				registrationSurname: this.userRegistrationDTO.surname,
				registrationGender: this.userRegistrationDTO.gender
			}
        }
	},
	methods: {
		save(date) {
			this.$refs.menu.save(date);
		},
		login(){
			this.loginFormHasErrors = false

	        Object.keys(this.loginForm).forEach(f => {
	          if (!this.loginForm[f]) this.loginFormHasErrors = true
	
	          this.$refs[f].validate(true)
	        })
			
			if(this.loginFormHasErrors) return;
			if(this.userToken != null) return;
			axios.post('rest/LoginController/login', this.userLoginDTO)
              .then(response => {
				this.userToken = response.data;
              	if(this.userToken == null || this.userToken=="") return;
	            sessionStorage.setItem('userToken', JSON.stringify(this.userToken));
	            this.mode= this.userToken.role;
	            this.refreshNavBar();
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
             this.refreshNavBar();
		},
		register(){
			this.registrationFormHasErrors = false

	        Object.keys(this.registrationForm).forEach(f => {
	          if (!this.registrationForm[f]) this.registrationFormHasErrors = true
	
	          this.$refs[f].validate(true)
	        })
			
			if(this.registrationFormHasErrors) return;
			
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
		},
		checkIfUsernameIsUnique(){
			if(this.userRegistrationDTO.username === ""){
				this.isUsernameUnique = false
				return false
			}
			let proba = this.errorMessages;
			axios.get('rest/RegisterController/checkIfUsernameIsUnique/' + this.userRegistrationDTO.username)
              .then(response => (this.isUsernameUnique = response.data ))
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
                    return this.isUsernameUnique
		},
		refreshNavBar(){
			
			if(this.mode == 'KUPAC'){
			this.appBarLinks = [
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
				},
				{
					name:'My Account',
					link:'/account'
				}
			]
			} else if(this.mode == "ADMINISTRATOR"){
				this.appBarLinks= [
			{
				name:'Home',
				link: '/'
			},
			{
				name:'Sport Facilities',
				link: '/sports-facilities'
			},
			{
				name:'Managers',
				link: '/managers'
			},
			{
				name:'Trainers',
				link: '/coaches'
			},
			{
				name: 'Buyers',
				link: '/buyers'
			},
			{
					name:'My Account',
					link:'/account'
			}
		]
			} else if(this.mode == "MENADZER"){
				this.appBarLinks= [
			{
				name:'Home',
				link: '/'
			},
			{
				name:'Sport Facility',
				link: '/sports-facility'
			},
			{
				name:'Programs',
				link:'/programs'
			},
			{
					name:'My Account',
					link:'/account'
			}
		]
			} else if(this.mode == "TRENER"){
				this.appBarLinks= [
			{
				name:'Home',
				link: '/'
			},
			{
				name:'Sport Facilities',
				link: '/sports-facilities'
			},
			{
					name:'My Account',
					link:'/account'
			}
		]
			} else if(this.mode == "GUEST")
			{
				this.appBarLinks = [
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
			]
			}
		}
	}
});