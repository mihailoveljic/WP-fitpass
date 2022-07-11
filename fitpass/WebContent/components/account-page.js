Vue.component("account-page", {
	name:"account-page",
	data: function () {
		    return {
				user: null,
				userUpdateDTO: {
					id: -1,
					username: "",
					oldPassword:"",
					newPassword:"",
					changePassword: false,
					name:"",
					surname:"",
					gender: null,
					dateOfBirth : {
						year: null,
						month: null,
						day: null
					}
				},
				activePicker: null,
				date: null,
				menu: false,
		        updateErrorMessages : "",
		        updateFormHasErrors : false,
		        saving: false,
		    }
	},
	template: 
` 
	<v-card>
		<v-row>	
			<v-col cols="4">
			</v-col>
			<v-col cols="4">
				<v-text-field label="Username" required v-model="userUpdateDTO.username" disabled>
				</v-text-field>
			</v-col>
			<v-col cols="4">
			</v-col>
		</v-row>
		<v-row>	
			<v-col cols="4">
			</v-col>
			<v-col cols="4">
				<v-text-field ref="oldPassword" label="Password" type="password" v-model="userUpdateDTO.oldPassword"
					:rules="[() => !!userUpdateDTO.oldPassword || 'This field is required!']" :error-messages="updateErrorMessages">
				</v-text-field>
			</v-col>
			<v-col cols="4">
			</v-col>
		</v-row>
		<v-row>	
			<v-col cols="4">
			</v-col>
			<v-col cols="4">
				<v-text-field :disabled="!userUpdateDTO.changePassword" ref="newPassword" label="New Password" type="password" v-model="userUpdateDTO.newPassword"
					:rules="[() => !!userUpdateDTO.newPassword || 'This field is required!']" :error-messages="updateErrorMessages">
				</v-text-field>
				<v-checkbox 
			      v-model="userUpdateDTO.changePassword"
			      label="New password?">
			    </v-checkbox>
			</v-col>
			<v-col cols="4">
			</v-col>
		</v-row>
		<v-row>
			<v-col cols="4">
			</v-col>
			<v-col cols="2">
				<v-text-field ref="name" label="Name" required v-model="userUpdateDTO.name"
					:rules="[() => !!userUpdateDTO.name || 'This field is required!']" :error-messages="updateErrorMessages">
				</v-text-field>
			</v-col>
			<v-col cols="2">
				<v-text-field ref="surname" label="Surname" required v-model="userUpdateDTO.surname"
					:rules="[() => !!userUpdateDTO.surname || 'This field is required!']" :error-messages="updateErrorMessages">
				</v-text-field>
			</v-col>
			<v-col cols="4">
			</v-col>
		</v-row>
		
		<v-row>	
			<v-col cols="4">
			</v-col>
			<v-col cols="2">
				<v-container fluid>
					<p>Gender:</p>
					<v-radio-group ref="gender" v-model="userUpdateDTO.gender" mandatory
					 :rules="[() => !!userUpdateDTO.gender || 'This field is required!']" :error-messages="updateErrorMessages">
						<v-radio label="Male" value="MALE"></v-radio>
						<v-radio label="Female" value="FEMALE"></v-radio>
					</v-radio-group>
				</v-container>
			</v-col>
			<v-col cols="2">
				<div>
					<v-menu ref="menu" v-model="menu"
						:close-on-content-click="false"
						transition="scale-transition" offset-y min-width="auto">
						<template v-slot:activator="{ on, attrs }">
							<v-text-field v-model="date" label="Birthday date"
								prepend-icon="mdi-calendar" readonly v-bind="attrs"
								v-on="on">
							</v-text-field>
						</template>
						<v-date-picker ref="date" v-model="date" required
							:active-picker.sync="activePicker"
							:max="(new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10)"
							min="1950-01-01" @change="save"
							:rules="[() => !!date || 'This field is required!']" :error-messages="updateErrorMessages"></v-date-picker>
					</v-menu>
				</div>
			</v-col>
			<v-col cols="4">
			</v-col>
		</v-row>
		<v-row class="center-text">	
			<v-col cols="4">
			</v-col>
			<v-col cols="4">
				<v-card-actions class="text-center">
					<v-spacer></v-spacer>
					<v-btn @click="saveChanges" :loading="saving" color="primary" class="ma-0 d-none d-lg-flex">
						Save Changes
					</v-btn>
				</v-card-actions>
			</v-col>
			<v-col cols="4">
			</v-col>
		</v-row>

		
		
		
	</v-card>
`
	, 
	props:['userToken'],
	methods : {
		save(date) {
				this.$refs.menu.save(date);
		},
		saveChanges(){
			this.saving = true;
			if(this.userToken.role =='KUPAC'){
				axios.put('rest/buyers', this.userUpdateDTO)
	              .then(response => {
						this.saving = false;
						if(!response.data) alert('Wrong password!');
						else alert('Changes saved!');
					})
	              .catch(error => {
	                    alert(error.message + " GRESKA");
                });
			}else if(this.userToken.role =='ADMINISTRATOR'){
				axios.put('rest/administrators', this.userUpdateDTO)
	              .then(response => {
						this.saving = false;
						if(!response.data) alert('Wrong password!');
						else alert('Changes saved!');
					})
	              .catch(error => {
	                    alert(error.message + " GRESKA");
                });
			}else if(this.userToken.role =='MENADZER'){
				axios.put('rest/managers', this.userUpdateDTO)
	              .then(response => {
						this.saving = false;
						if(!response.data) alert('Wrong password!');
						else alert('Changes saved!');
					})
	              .catch(error => {
	                    alert(error.message + " GRESKA");
                });
			}else if(this.userToken.role =='TRENER'){
				axios.put('rest/coaches', this.userUpdateDTO)
	              .then(response => {
						this.saving = false;
						if(!response.data) alert('Wrong password!');
						else alert('Changes saved!');
					})
	              .catch(error => {
	                    alert(error.message + " GRESKA");
                });
			}else if(this.userToken.role =='GUEST'){
				this.$router.push('/');
			}
		}		
	},
	watch: {
		menu(val) {
			val && setTimeout(() => (this.activePicker = 'YEAR'))
		},
	},
	computed: {
		registrationForm () {
	        return {
				oldPassword:this.userUpdateDTO.oldPassword,
				newPassword:this.userUpdateDTO.newPassword,
				name:this.userUpdateDTO.name,
				surname: this.userUpdateDTO.surname,
				gender: this.userUpdateDTO.gender
			}
        }
	},
	created(){
		if(!this.userToken){
			this.$router.push('/');
			}
	},
	beforeUpdate(){
		if(!this.userToken){
			this.$router.push('/');
			}
	},
	mounted () {
		if(this.userToken.role == "KUPAC"){
			axios.get('rest/buyers/'+this.userToken.id)
              .then(response => {
					this.user = response.data;
					this.userUpdateDTO.id = this.user.id;
					this.userUpdateDTO.username = this.user.username;
					this.userUpdateDTO.name = this.user.name;
					this.userUpdateDTO.surname = this.user.surname;
					this.userUpdateDTO.gender = this.user.gender;
					date = new Date(this.user.dateOfBirth);
					this.userUpdateDTO.dateOfBirth.year = date.getFullYear()-1900;
					this.userUpdateDTO.dateOfBirth.month = date.getMonth();
					this.userUpdateDTO.dateOfBirth.day = date.getDate();
					this.date = this.userUpdateDTO.dateOfBirth.year + '-' +this.userUpdateDTO.dateOfBirth.month + '-' + this.userUpdateDTO.dateOfBirth.day
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
		}else if(this.userToken.role == "ADMINISTRATOR"){
			axios.get('rest/administrators/'+this.userToken.id)
              .then(response => {
					this.user = response.data;
					this.userUpdateDTO.id = this.user.id;
					this.userUpdateDTO.username = this.user.username;
					this.userUpdateDTO.name = this.user.name;
					this.userUpdateDTO.surname = this.user.surname;
					this.userUpdateDTO.gender = this.user.gender;
					date = new Date(this.user.dateOfBirth);
					this.userUpdateDTO.dateOfBirth.year = date.getFullYear()-1900;
					this.userUpdateDTO.dateOfBirth.month = date.getMonth();
					this.userUpdateDTO.dateOfBirth.day = date.getDate();
					this.date = this.userUpdateDTO.dateOfBirth.year + '-' +this.userUpdateDTO.dateOfBirth.month + '-' + this.userUpdateDTO.dateOfBirth.day
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
		}else if(this.userToken.role == "MENADZER"){
			axios.get('rest/managers/'+this.userToken.id)
              .then(response => {
					this.user = response.data;
					this.userUpdateDTO.id = this.user.id;
					this.userUpdateDTO.username = this.user.username;
					this.userUpdateDTO.name = this.user.name;
					this.userUpdateDTO.surname = this.user.surname;
					this.userUpdateDTO.gender = this.user.gender;
					date = new Date(this.user.dateOfBirth);
					this.userUpdateDTO.dateOfBirth.year = date.getFullYear()-1900;
					this.userUpdateDTO.dateOfBirth.month = date.getMonth();
					this.userUpdateDTO.dateOfBirth.day = date.getDate();
					this.date = this.userUpdateDTO.dateOfBirth.year + '-' +this.userUpdateDTO.dateOfBirth.month + '-' + this.userUpdateDTO.dateOfBirth.day
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
		}else if(this.userToken.role == "TRENER"){
			axios.get('rest/coaches/'+this.userToken.id)
              .then(response => {
					this.user = response.data;
					this.userUpdateDTO.id = this.user.id;
					this.userUpdateDTO.username = this.user.username;
					this.userUpdateDTO.name = this.user.name;
					this.userUpdateDTO.surname = this.user.surname;
					this.userUpdateDTO.gender = this.user.gender;
					date = new Date(this.user.dateOfBirth);
					this.userUpdateDTO.dateOfBirth.year = date.getFullYear()-1900;
					this.userUpdateDTO.dateOfBirth.month = date.getMonth();
					this.userUpdateDTO.dateOfBirth.day = date.getDate();
					this.date = this.userUpdateDTO.dateOfBirth.year + '-' +this.userUpdateDTO.dateOfBirth.month + '-' + this.userUpdateDTO.dateOfBirth.day
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
		}else if(this.userToken.role == "GUEST"){
			this.$router.push('/');
		}
		
    }
});

