const SportsFacilities = { template: '<sports-facilities :mode="$attrs.mode"></sports-facilities>'}
const BuyersList = { template : '<buyers-list :mode="$attrs.mode"></buyers-list>'}
const ManagersList = {template : '<managers-list :mode="$attrs.mode"></managers-list>'}
const CoachesList = {template : '<coaches-list :mode="$attrs.mode"></coaches-list>'}
const AccountPage = {template : '<account-page :userToken="$attrs.usertoken"></account-page>'}
const ManagerFacility = {template : '<manager-facility :userToken="$attrs.usertoken"></manager-facility>'}
const SportsFacility = {template : '<sports-facility :userToken="$attrs.usertoken"></sports-facility>'}
const UsersList = {template : '<users-list :mode="$attrs.mode"></users-list>'}
const NewSportFacilityPage = {template : '<newSportFacility-page :mode="$attrs.mode" :userToken="$attrs.usertoken"></newSportFacility-page>'}
const ManagerFacilityCoaches = {template : '<manager-facility-coaches :userToken="$attrs.usertoken"></manager-facility-coaches>'}
const ManagerFacilityBuyers = {template : '<manager-facility-buyers :userToken="$attrs.usertoken"></manager-facility-buyers>'}
const NewTraining = {template : '<new-training :userToken="$attrs.usertoken"></new-training>'}
const MyTrainingsBuyers = {template : '<my-trainings-buyers :userToken="$attrs.usertoken"></my-trainings-buyers>'}
const NewFacilityContent = {template : '<new-facilityContent :userToken="$attrs.usertoken"></new-facilityContent>'}
const MyTrainings = {template : '<my-trainings :userToken="$attrs.usertoken"></my-trainings>'}
const PersonalTrainings = {template : '<personal-trainings :userToken="$attrs.usertoken"></personal-trainings>'}
const GroupTrainings = {template : '<group-trainings :userToken="$attrs.usertoken"></group-trainings>'}







const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{path: '/sports-facilities', name: 'sports-facilities', component: SportsFacilities},
		{path: '/buyers', name: 'buyers-list', component: BuyersList},
		{path: '/managers', name:'managers-list', component: ManagersList},
		{path: '/coaches', name:'coaches-list', component: CoachesList},
		{path: '/account', name:'account-page', component: AccountPage},
		{path: '/users', name:'users-list', component: UsersList},
		{path: '/newSportFacility', name:'newSportFacility-page', component: NewSportFacilityPage},
		{path: '/manager-facility', name:'manager-facility', component: ManagerFacility},
		{path: '/sports-facility/:sportsFacilityId', name:'sports-facility', component: SportsFacility},
		{path: '/manager-facility-coaches', name:'manager-facility-coaches', component: ManagerFacilityCoaches},
		{path: '/manager-facility-buyers', name:'manager-facility-buyers', component: ManagerFacilityBuyers},
		{path: '/manager-facility/new-training', name:'new-training', component: NewTraining},
		{path: '/my-trainings-buyers', name:'my-trainings-buyers', component: MyTrainingsBuyers},
		{path: '/manager-facility/new-facilityContent', name:'new-facilityContent', component: NewFacilityContent},
		{path: '/my-trainings', name:'my-trainings', component: MyTrainings},
		{path: '/personal-trainings', name:'personal-trainings', component: PersonalTrainings},
		{path: '/group-trainings', name:'group-trainings', component: GroupTrainings},

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
				link: '/coaches'
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
		registrationFormHasErrors: false,
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
			if(this.userToken != null || this.userToken == "") return;
			axios.post('rest/LoginController/login', this.userLoginDTO)
              .then(response => {
				this.userToken = response.data;
              	if(this.userToken == null || this.userToken=="") return;
	            sessionStorage.setItem('userToken', JSON.stringify(this.userToken));
	            this.mode= this.userToken.role;
	            this.refreshNavBar();
	            this.loginDialog = false;
	            this.$router.push('/');
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
			this.$router.push('/');
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
						name:'My Trainings',
						link:'/my-trainings-buyers'
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
				name:'All users',
				link:'/users'
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
						link: '/manager-facility'
					},
					{
						name:'Coaches',
						link:'/manager-facility-coaches'
					},
					{
						name:'Clients',
						link:'/manager-facility-buyers'
					},
					{
						name:'Programs',
						link:'/programs'
					},
					{
							name:'My Account',
							link:'/account'
					},
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
						name:'My trainings',
						link: '/my-trainings'
					},
					{
						name:'Personal trainings',
						link: '/personal-trainings'
					},
					{
						name:'Group trainings',
						link: '/group-trainings'
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