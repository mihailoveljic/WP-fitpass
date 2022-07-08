Vue.component("coaches-list", {
	name: "coaches-list",
	data: function () {
		    return {
				coaches : [],
				createDialog:false,
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
				activePicker: null,
				date: null,
				menu: false,
		        radios: null,
		        registrationErrorMessages : "",
		        registrationFormHasErrors : false
			}
	},
	props:
		['mode']
	,
	template: 
` 
<div v-if="mode=='ADMINISTRATOR'">
	 <v-row>
	 <v-col cols="3" class="text-center">
	 
	 
	<template>
					<v-row justify="center">
						<v-dialog v-model="createDialog" persistent max-width="600px">
							<template v-slot:activator="{ on, attrs }">
								<v-btn v-bind="attrs" v-on="on" color="primary" class="ma-0 d-none d-lg-flex""
								 centered x-large>
									Add Coach
								</v-btn>
							</template>
							<v-card>
								<v-card-title>
									<span class="text-h5">Coach registration form</span>
								</v-card-title>
								<v-card-text>
									<v-container>
										<v-row>
											<v-col cols="12" sm="8" md="12">
												<v-text-field ref="registrationName" label="Name*" required v-model="userRegistrationDTO.name"
												:rules="[() => !!userRegistrationDTO.name || 'This field is required!']" :error-messages="registrationErrorMessages">
												</v-text-field>
											</v-col>
											<v-col cols="12" sm="8" md="12">
												<v-text-field ref="registrationSurname" label="Surname*" required v-model="userRegistrationDTO.surname"
												:rules="[() => !!userRegistrationDTO.surname || 'This field is required!']" :error-messages="registrationErrorMessages">
												</v-text-field>
											</v-col>
											<v-col cols="12" sm="8" md="12">
												<template>
													<v-container fluid>
														<p>Gender:</p>
														<v-radio-group ref="registrationGender" v-model="userRegistrationDTO.gender" mandatory
														 :rules="[() => !!userRegistrationDTO.gender || 'This field is required!']" :error-messages="registrationErrorMessages">
															<v-radio label="Male" value="MALE"></v-radio>
															<v-radio label="Female" value="FEMALE"></v-radio>
														</v-radio-group>
													</v-container>
												</template>
											</v-col>
											<v-col cols="12" sm="8" md="12">
												<v-text-field ref="registrationUsername" label="Username*" required v-model="userRegistrationDTO.username"
												  :rules="[  () => !!userRegistrationDTO.username || 'This field is required!',
												 			 checkIfUsernameIsUnique()  || 'Must be unique']" :error-messages="registrationErrorMessages"> <!--:class="{error : '!isUsernameUnique'}" v-on:blur="checkIfUsernameIsUnique"-->
												</v-text-field>
											</v-col>
											<v-col cols="12" sm="8" md="12">
												<v-text-field ref="registrationPassword" label="Password" type="password" v-model="userRegistrationDTO.password"
													:rules="[() => !!userRegistrationDTO.password || 'This field is required!']" :error-messages="registrationErrorMessages"></v-text-field>
											</v-col>
											<v-col cols="12" sm="8" md="12">
												<template>
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
															<v-date-picker ref="registrationDate" v-model="date" required
																:active-picker.sync="activePicker"
																:max="(new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10)"
																min="1950-01-01" @change="save"
																:rules="[() => !!date || 'This field is required!']" :error-messages="registrationErrorMessages"></v-date-picker>
														</v-menu>
													</div>

												</template>
											</v-col>
										</v-row>
									</v-container>
									<small>*indicates required field</small>
								</v-card-text>
								<v-card-actions>
									<v-spacer></v-spacer>
									<v-btn color="blue darken-1" text @click="createDialog = false">
										Cancel
									</v-btn>
									<v-btn color="blue darken-1" text @click="register()">
										Register
									</v-btn>
								</v-card-actions>
							</v-card>
						</v-dialog>
					</v-row>
				</template>
	 
	 
	 
	 </v-col>
	 <v-col cols="6">
	  <v-simple-table >
	    <template v-slot:default>
	      <thead>
	        <tr>
	          <th class="text-left">
	            Name
	          </th>
	          <th class="text-left">
	            Surname
	          </th>
	        </tr>
	      </thead>
	      <tbody>
	        <tr
	          v-for="coach in coaches"
	          :key="coach.id"
	        >
	          <td>{{ coach.name }}</td>
	          <td>{{ coach.surname }}</td>
	          <td><v-btn @click="deleteCoach(coach)">delete</v-btn></td>
	        </tr>
	      </tbody>
	    </template>
	  </v-simple-table>
	  </v-col>
	  <v-col cols="3">
	  </v-col>
	 </v-row>
</div>
`
, 
	methods : {
		save(date) {
				this.$refs.menu.save(date);
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
			axios.post('rest/RegisterController/registerCoach', this.userRegistrationDTO)
              .then(response => {
				this.userToken = response.data
              	if(this.userToken == null) return;
	            this.createDialog = false;
	            alert("New coach successfuly added!");
	            this.reloadPage();
              }
              )
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
		},
		deleteCoach(coach){
			let isExecuted = confirm("Are you sure to delete coach?");

			if(isExecuted){
				axios.delete('rest/coaches/' + coach.id)
              .then(response => {
					if(response.data == false){
						alert("Failed to delete coach!");
						return false;
					} 
              })
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
                    this.reloadPage();
                    return true;
			}
		},
		reloadPage(){
    		axios.get('rest/coaches')
              .then(response => {
					this.coaches = response.data;
					this.userRegistrationDTO = {
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
					this.date = null
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
  		},
	},
	watch: {
		menu(val) {
			val && setTimeout(() => (this.activePicker = 'YEAR'))
		},
	},
	computed: {
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
	mounted () {
		axios.get('rest/coaches')
              .then(response => {
					this.coaches = response.data;
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
    }
});