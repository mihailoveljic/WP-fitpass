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
			saving : false,
			imageAdded : false,
			inputStarted : false,
			selectedCoach: null,
			imagePreview: ""
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
	        <v-text-field class=""
		          v-model="trainingDTO.name"
		          label="Name"
	          		required>
	        </v-text-field>
	        <v-text-field class=""
		          v-model="trainingDTO.duration"
		          label="Duration (in mins)"
	          		required>
	        </v-text-field>
	        <v-text-field class=""
		          v-model="trainingDTO.description"
		          label="Description"
	          		required>
	        </v-text-field>
	        <v-text-field class=""
		          v-model="trainingDTO.additionalPrice"
		          label="Additional price"
	          		required>
	        </v-text-field>
	 		<v-combobox v-model="trainingDTO.trainingType" :items="trainingTypes" item-text="name" label="Training Type" clearable outlined small-chips class="my-4"></v-combobox>
	        <v-combobox v-model="selectedCoach" label="Coach" item-text="fullName" :items="coaches" clearable outlined small-chips></v-combobox>
	        <v-row>
				<v-img class="ma-4" v-if="imageAdded" :src="imagePreview" height="150" width="150" dark></v-img>
				<v-progress-circular v-if="!imageAdded && inputStarted" :size="50" color="primary" indeterminate></v-progress-circular>
			</v-row>
	        <v-file-input @click:clear="hideImageLoading()" @change="uploadImage()" counter v-model="trainingDTO.image" prepend-icon="mdi-camera" required show-size truncate-length="25"></v-file-input>
	      <v-btn
	        class="mr-4"
	        @click="createNewTraining" :loading="saving">
	        submit
	      </v-btn>
	      <v-btn to="/manager-facility">
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
			this.trainingDTO.coach = {
				id: this.selectedCoach.id,
			}
			
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
			if(!this.trainingDTO.image) return;
			this.imageAdded = false;
			this.inputStarted = true;
			let promiseImageUploaded = axios.post('rest/TrainingController/uploadImage', this.trainingDTO.image , 
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
	}
});