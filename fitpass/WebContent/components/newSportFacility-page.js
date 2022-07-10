Vue.component("newSportFacility-page", {
	name:"newSportFacility-page",
	data: function () {
		    return {
				user: null,
				sportFacilityDTO: {
					id: -1,
					name: "",
					sportsFacilityType: null,
					facilityContent : [],
					openStatus : false,
					country : "",
					street : "",
					number : "",
					city : "",
					zipCode : "",
					image : null,
					fromThe : null,
					toThe : null
				},
				sportsFacilityTypes : [],
				facilityContents : [],
				date: null,
				menu: false,
		        updateErrorMessages : "",
		        updateFormHasErrors : false,
		        saving: false,
		        managers : [],
		        manager: null,
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
		        registrationErrorMessages : "",
				registrationFormHasErrors: false,
				fromThePickerOpen: false,
				toThePickerOpen: false,
				imageAdded: false,
				image: "",
				imagePreview: "",
				inputStarted: false,
				mapInitCenter: {
					longitude: 20.2821717,
					latitude: 44.8154029
				},
				markers: null,
				marker: null,
				coords: null
		    }
	},
	template: 
` 
	<v-card>
		<v-row>	
			<v-col cols="4">
			</v-col>
			<v-col cols="2">
				<v-text-field ref="name" label="Name" required v-model="sportFacilityDTO.name"
					:rules="[() => !!sportFacilityDTO.name || 'This field is required!']" :error-messages="updateErrorMessages">
				</v-text-field>
			</v-col>
			<v-col cols="2">
				<v-combobox v-model="sportFacilityDTO.sportsFacilityType" item-text="name" :items="sportsFacilityTypes" label="Sport facility types" clearable outlined small-chips></v-combobox>
			</v-col>
			<v-col cols="4">
			</v-col>
		</v-row>
		<v-row>	
			<v-col cols="4">
			</v-col>			
			<v-col cols="4">
				<v-combobox v-model="sportFacilityDTO.facilityContent" item-text="name" :items="facilityContents" label="Programs" clearable multiple outlined small-chips></v-combobox>
			</v-col>
			<v-col cols="4">
			</v-col>
		</v-row>
		<v-row >	
			<v-col cols="4">
			</v-col>			
			<v-col cols="2">
				<v-combobox v-model="manager" item-text="fullName" :items="managers" label="Available managers" clearable outlined></v-combobox>
			</v-col>
			<v-col cols="2">
				<v-dialog v-model="createDialog" persistent max-width="600px">
							<template v-slot:activator="{ on, attrs }">
								<v-btn v-bind="attrs" v-on="on" color="primary" class="ma-0 d-none d-lg-flex"
								 centered x-large>
									New manager?
								</v-btn>
							</template>
							<v-card>
								<v-card-title>
									<span class="text-h5">Manager registration form</span>
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
			</v-col>
			<v-col cols="4">
			</v-col>
		</v-row>
		<v-row>
			<v-col cols="4">
			</v-col>
			<v-col cols="4">
				<v-text-field ref="country" label="Country" required v-model="sportFacilityDTO.country"
					:rules="[() => !!sportFacilityDTO.country || 'This field is required!']" :error-messages="updateErrorMessages">
				</v-text-field>
			</v-col>
			<v-col cols="4">
			</v-col>	
		</v-row>
		<v-row>
			<v-col cols="4">
			</v-col>
			<v-col cols="2">
				<v-text-field ref="city" label="City" required v-model="sportFacilityDTO.city"
					:rules="[() => !!sportFacilityDTO.city || 'This field is required!']" :error-messages="updateErrorMessages">
				</v-text-field>
			</v-col>	
			<v-col cols="2">
				<v-text-field ref="zipCode" label="Zip Code" required v-model="sportFacilityDTO.zipCode"
					:rules="[() => !!sportFacilityDTO.zipCode || 'This field is required!']" :error-messages="updateErrorMessages">
				</v-text-field>
			</v-col>
			<v-col cols="4">
			</v-col>	
		</v-row>
		<v-row>
			<v-col cols="4">
			</v-col>
			<v-col cols="2">
				<v-text-field ref="street" label="Street" required v-model="sportFacilityDTO.street"
					:rules="[() => !!sportFacilityDTO.street || 'This field is required!']" :error-messages="updateErrorMessages">
				</v-text-field>
			</v-col>
			<v-col cols="2">
				<v-text-field ref="number" label="Number" required v-model="sportFacilityDTO.number"
					:rules="[() => !!sportFacilityDTO.number || 'This field is required!']" :error-messages="updateErrorMessages">
				</v-text-field>
			</v-col>
			<v-col cols="4">
			</v-col>		
		</v-row>
		
		<v-row>
			<v-col cols="4">
			</v-col>
			<v-col cols="4">
				<div ref="map" class="map""></div>
			</v-col>
			<v-col cols="4">
			</v-col>
		</v-row>
		<v-row>
			<v-col cols="4">
			</v-col>
			<v-col cols="2">
				
				<v-img class="ma-4" v-if="imageAdded" :src="imagePreview" height="150" width="150" dark></v-img>
				<v-progress-circular v-if="!imageAdded && inputStarted" :size="50" color="primary" indeterminate></v-progress-circular>
			</v-col>
			<v-col cols="2">
				<v-file-input @click:clear="hideImageLoading()" @change="uploadImage()" counter v-model="sportFacilityDTO.image" prepend-icon="mdi-camera" required show-size truncate-length="25"></v-file-input>
			</v-col>
			<v-col cols="4">
			</v-col>
		</v-row>
		<v-row>
			<v-col cols="4">
			</v-col>
			<v-col cols="2">
					<v-menu
						ref="fromThePicker"
						v-model="fromThePickerOpen"
						:close-on-content-click="false"
						:nudge-right="40"
						:return-value.sync="sportFacilityDTO.fromThe"
						transition="scale-transition"
						offset-y
						max-width="290px"
						min-width="290px"
					>
					<template v-slot:activator="{ on, attrs }">
					<v-text-field
						v-model="sportFacilityDTO.fromThe"
						label="Opens at:"
						prepend-icon="mdi-clock-time-four-outline"
						readonly
						v-bind="attrs"
						v-on="on"
						:rules="[() => !!sportFacilityDTO.fromThe || 'This field is required!']" :error-messages="updateErrorMessages"
					></v-text-field>
					</template>
					<v-time-picker
					v-if="fromThePickerOpen"
					v-model="sportFacilityDTO.fromThe"
					full-width
					@click:minute="$refs.fromThePicker.save(sportFacilityDTO.fromThe)"
					></v-time-picker>
				</v-menu>
			</v-col>
			<v-col cols="2">
				<v-menu
						ref="toThePicker"
						v-model="toThePickerOpen"
						:close-on-content-click="false"
						:nudge-right="40"
						:return-value.sync="sportFacilityDTO.toThe"
						transition="scale-transition"
						offset-y
						max-width="290px"
						min-width="290px"
					>
					<template v-slot:activator="{ on, attrs }">
					<v-text-field
						v-model="sportFacilityDTO.toThe"
						label="Closes at:"
						prepend-icon="mdi-clock-time-four-outline"
						readonly
						v-bind="attrs"
						v-on="on"
					></v-text-field>
					</template>
					<v-time-picker
					v-if="toThePickerOpen"
					v-model="sportFacilityDTO.toThe"
					full-width
					@click:minute="$refs.toThePicker.save(sportFacilityDTO.toThe)"
					></v-time-picker>
				</v-menu>
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
					<v-btn @click="createNewSportFacility" :loading="saving" class="ma-0 d-none d-lg-flex" color="primary">
						Create
					</v-btn>
				</v-card-actions>
			</v-col>
			<v-col cols="4">
			</v-col>
		</v-row>
	</v-card>
`
	, 
	props:
		['mode', 'userToken'],
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
		hideImageLoading(){
			this.imageAdded = false;
			this.inputStarted = false;
		},
		uploadImage(){
			if(!this.sportFacilityDTO.image) return;
			this.imageAdded = false;
			this.inputStarted = true;
			let promiseImageUploaded = axios.post('rest/SportsFacilityController/uploadImage', this.sportFacilityDTO.image , 
			{
				headers:{
					'Content-Type': 'image/jpeg'
				}
			})
              .then(response => {
				let parts = response.headers.location.split('/');
				this.imagePreview = "\\fitpass\\data\\img\\sports-facilities\\" + parts[3];
				this.image = parts[3];
              })
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
             let s = new Promise(r => setTimeout(r, 8000));
             Promise.all([promiseImageUploaded, s]).then(() =>{
				this.imageAdded = true
				this.inputStarted = false;
			 });
		},
		createNewSportFacility(){
			//validacija
		if(this.coords==null){
			alert('Unesi lokaciju objekta na mapi!');
			return;
		}
		if(this.sportFacilityDTO.fromThe > this.sportFacilityDTO.toThe){
			alert("Morate uneti ispravno radno vreme");
			return;
		}
	    let sportFacility = {
					id: -1,
					name:  this.sportFacilityDTO.name,
					sportsFacilityTypeId:  this.sportFacilityDTO.sportsFacilityType.id,
					facilityContentIds:  this.sportFacilityDTO.facilityContent.map(fc => fc.id),
					openStatus:  this.sportFacilityDTO.openStatus,
					location: {
						longitude: parseFloat(this.coords[0]),
						latitude: parseFloat(this.coords[1]),
						address: {
							country: this.sportFacilityDTO.country,
							street: this.sportFacilityDTO.street,
							number: this.sportFacilityDTO.number,
							city: this.sportFacilityDTO.city,
							zipCode: parseInt(this.sportFacilityDTO.zipCode)
						}
					},
					image: this.image,
					averageRating: 0,
					workingHours:{
						fromThe:  this.sportFacilityDTO.fromThe,
						toThe: this.sportFacilityDTO.toThe
					}
				}
	            
	            axios.post('rest/SportsFacilityController', sportFacility)
	              .then(response => {
					this.sportFacilityDTO.id = response.data.id;
					this.manager.sportsFacilityId = this.sportFacilityDTO.id;
					let managerFacilityDTO = {
						managerId: this.manager.id,
						facilityId: this.sportFacilityDTO.id
					}
					axios.put('rest/managers/updateFacility', managerFacilityDTO)
		              .then(response => {
						        this.$router.push('/sports-facilities');
		              }
	              )
	              .catch(error => {
	                    alert(error.message + " GRESKA");
	              });
					
	              }
	              )
	              .catch(error => {
	                    alert(error.message + " GRESKA");
	              });
		},
		register(){
			this.registrationFormHasErrors = false

	        Object.keys(this.registrationForm).forEach(f => {
	          if (!this.registrationForm[f]) this.registrationFormHasErrors = true
	
	          this.$refs[f].validate(true)
	        })
			
			if(this.registrationFormHasErrors) return false;
			
			let temp = this.date.split('-');
			this.userRegistrationDTO.dateOfBirth.year = temp[0];
			this.userRegistrationDTO.dateOfBirth.month = temp[1];
			this.userRegistrationDTO.dateOfBirth.day = temp[2];
			axios.post('rest/RegisterController/registerManager', this.userRegistrationDTO)
              .then(response => {
				this.userToken = response.data
              	if(this.userToken == null){
					alert("Something went wrong!");
					return false;
				}
	            this.createDialog = false;
	            alert("New manager successfuly added!");
	            this.reloadPage();
              }
              )
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
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
	         	registrationUsername: this.userRegistrationDTO.username,
				registrationPassword:this.userRegistrationDTO.password,
				registrationName:this.userRegistrationDTO.name,
				registrationSurname: this.userRegistrationDTO.surname,
				registrationGender: this.userRegistrationDTO.gender
			}
        }
	},
	mounted () {
		this.markers = new ol.layer.Vector({
					  	source: new ol.source.Vector(),
					  	style: new ol.style.Style({
				    	image: new ol.style.Icon({
					      anchor: [0.5, 1],
				          anchorXUnits: 'fraction',
				          anchorYUnits: 'fraction',
				          opacity: 1,
				          scale: 0.075,
					      src: 'img\\marker.png',
					    })
					  })
					});
		this.map = new ol.Map({
	        target: this.$refs.map,
	        layers: [
	          new ol.layer.Tile({
	            source: new ol.source.OSM()
	          }),
	 		],
	        view: new ol.View({
	          center: ol.proj.fromLonLat([this.mapInitCenter.longitude, this.mapInitCenter.latitude]),
	          zoom: 8
	        })
		});
		
		this.map.addLayer(this.markers);
		
		this.map.on('click', (evt) =>{
	   		// Get the pointer coordinate
   			this.coords = ol.proj.toLonLat(evt.coordinate);
   			
   			
			this.marker = new ol.Feature(new ol.geom.Point(ol.proj.fromLonLat([this.coords[0], this.coords[1]])));
			this.markers.getSource().clear();
			this.markers.getSource().addFeature(this.marker);
		});
		axios.get('rest/managers/free')
              .then(response => {
					this.managers = response.data;
					for(var i = 0; i<this.managers.length; i++){
						if(this.managers[i].sportsFacilityId !== -1){
							this.managers.splice(i, i+1);
						}
					}
					this.managers.forEach((manager) =>{
						manager.fullName = manager.name + " " + manager.surname;
					});
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
        axios.get('rest/FacilityContentController')
              .then(response => {
					this.facilityContents = response.data;
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
         axios.get('rest/SportsFacilityTypeController')
              .then(response => {
					this.sportsFacilityTypes = response.data;
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });           
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
	}
});

