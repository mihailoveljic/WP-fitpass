Vue.component("new-training", {
	name:"new-training",
	data:function(){
		return {
			trainingTypes : [],
			coaches : [],
			trainingDTO : {
				id : -1,
				name : null,
				trainingType : null,
				sportsFacilityId : null,
				duration : null,
				coach : null,
				description : null,
				additionalPrice : 0,
				image : []
			},
			enteredTrainingType : null,
			saving : false,
			imageAdded : false,
			inputStarted : false,
			selectedCoach: null,
			imagePreview: "",
			image: null,
			createErrorMessages : "",
			registrationFormHasErrors : false
		}
	},
	template:
`
<div>
	<v-row>
	<v-col cols="4">
	</v-col>
	<v-col cols="4">
	<template>
	    <form @submit.prevent="submit">
	        <v-text-field class="" ref='registrationName'
		          v-model="trainingDTO.name"
		          label="Name"
		          :rules="[() => !!trainingDTO.name || 'This field is required!']" :error-messages="createErrorMessages"
	          		required>
	        </v-text-field>
	        <v-text-field class="" ref='registrationDuration'
		          v-model="trainingDTO.duration"
		          type = "number"
		          label="Duration (in mins)"
		          :rules="[() => !!trainingDTO.duration || 'This field is required!']" :error-messages="createErrorMessages"
	          		required>
	        </v-text-field>
	        <v-text-field class="" ref='registrationDescription'
		          v-model="trainingDTO.description"
		          :rules="[() => !!trainingDTO.description || 'This field is required!']" :error-messages="createErrorMessages"
		          label="Description"
	          		required>
	        </v-text-field>
	        <v-text-field class=""
		          v-model="trainingDTO.additionalPrice"
		          label="Additional price"
	          		required>
	        </v-text-field>
	 		<v-combobox v-model="enteredTrainingType" :items="trainingTypes" item-text="name" label="Training Type" clearable outlined small-chips class="my-4"
	 		ref='registrationTrainingType'
	 		:rules="[() => !!enteredTrainingType || 'This field is required!']" :error-messages="createErrorMessages"
	 		></v-combobox>
	        <v-combobox v-model="selectedCoach" label="Coach" item-text="fullName" :items="coaches" clearable outlined small-chips
	        ref='registrationCoach'
	        :rules="[() => !!selectedCoach || 'This field is required!']" :error-messages="createErrorMessages"
	        ></v-combobox>
	        <v-row>
				<v-img class="ma-4" v-if="imageAdded" :src="imagePreview" height="150" width="150" dark></v-img>
				<v-progress-circular v-if="!imageAdded && inputStarted" :size="50" color="primary" indeterminate></v-progress-circular>
			</v-row>
	        <v-file-input @click:clear="hideImageLoading()" @change="uploadImage()" counter v-model="image" prepend-icon="mdi-camera" required show-size truncate-length="25"></v-file-input>
	      <v-btn
	        class="mr-4"
	        @click="createNewTraining" :loading="saving">
	        submit
	      </v-btn>
	      <v-btn @click="backToManagerFacilityRoute">
	        cancel
	      </v-btn>
	    </form>
</template>
</v-col>
	<v-col cols="4">
	</v-col>
</v-row>
</div>
`,

	props:['userToken'],
	methods: {
		createNewTraining() {
			this.registrationFormHasErrors = false
			
	        Object.keys(this.registrationForm).forEach(f => {
	          if (!this.registrationForm[f]) this.registrationFormHasErrors = true
	
	          this.$refs[f].validate(true)
	        })
			
			if(this.registrationFormHasErrors) return false;
			if(!this.image)
			{
				alert("Niste dodali sliku!");
				return;
			}
			this.trainingDTO.coach = {
				id: this.selectedCoach.id,
			}
			if(!this.enteredTrainingType.hasOwnProperty('id'))
			{
				this.trainingDTO.trainingType = {
					id : -1,
					name : this.enteredTrainingType,
					isDeleted: false
				}
			} else this.trainingDTO.trainingType = this.enteredTrainingType;
			axios.get('rest/managers/' + this.userToken.id)
			.then(response => {
				this.trainingDTO.sportsFacilityId = response.data.sportsFacilityId;
				axios.post('rest/TrainingController/', this.trainingDTO)
	              .then(response => {
						alert(response.data.name + " uspesno dodat!");
						this.$router.push('/manager-facility');	              
					}
	              )
	              .catch(error => {
	                    alert(error.message + " GRESKA");
	              });
			})
			.catch(error => {
				alert(error.message + "GRESKA");
			})
			
		},
		hideImageLoading(){
			this.imageAdded = false;
			this.inputStarted = false;
		},
		uploadImage(){
			if(!this.image) return;
			this.imageAdded = false;
			this.inputStarted = true;
			let promiseImageUploaded = axios.post('rest/TrainingController/uploadImage', this.image , 
			{
				headers:{
					'Content-Type': 'image/jpeg'
				}
			})
              .then(response => {
				let parts = response.headers.location.split('/');
				this.imagePreview = "\\fitpass\\data\\img\\trainings\\" + parts[3];
				this.trainingDTO.image = parts[3];
              })
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
             let s = new Promise(r => setTimeout(r, 7000));
             Promise.all([promiseImageUploaded, s]).then(() =>{
				this.imageAdded = true
				this.inputStarted = false;
			 });
		},
		backToManagerFacilityRoute() {
			this.$router.push('/manager-facility');
		}
	},
	mounted() {
		
		axios.get('rest/coaches')
              .then(response => {
					this.coaches = response.data;
					this.coaches.forEach((coach) =>{
						coach.fullName = coach.name + " " + coach.surname;
					});
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
        axios.get('rest/TrainingTypeController')
              .then(response => {
					this.trainingTypes = response.data;
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
	},
	computed: {
		registrationForm () {
	        return {
	         	registrationName: this.trainingDTO.name,
				registrationTrainingType:this.enteredTrainingType,
				registrationDuration:this.trainingDTO.duration,
				registrationDescription: this.trainingDTO.description,
				registrationCoach: this.selectedCoach
			}
        }
	},
});