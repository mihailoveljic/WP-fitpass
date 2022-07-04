Vue.component("edit-training", {
	name: "edit-training",
	data: function () {
		    return {
				trainingId : -1,
				trainingTypes : [],
				coaches : [],
				trainingDTO : null,
				enteredTrainingType : null,
				saving : false,
				imageAdded : false,
				inputStarted : false,
				selectedCoach: null,
				imagePreview: "",
				image: null
			}
	},
	props:
		['userToken']
	,
	template: 
` 
	<div>
	<v-row>
	<v-col cols="4">
	</v-col>
	<v-col cols="4">
	<template>
	    <form @submit.prevent="submit" v-if="!!trainingDTO">
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
	 		<v-combobox v-model="enteredTrainingType" :items="trainingTypes" item-text="name" label="Training Type" clearable outlined small-chips class="my-4"></v-combobox>
	        <v-combobox v-model="selectedCoach" label="Coach" item-text="fullName" :items="coaches" clearable outlined small-chips></v-combobox>
	        <v-row>
				<v-img class="ma-4" v-if="imageAdded" :src="imagePreview" height="150" width="150" dark></v-img>
				<v-progress-circular v-if="!imageAdded && inputStarted" :size="50" color="primary" indeterminate></v-progress-circular>
			</v-row>
	        <v-file-input @click:clear="hideImageLoading()" @change="uploadImage()" counter v-model="image" prepend-icon="mdi-camera" required show-size truncate-length="25"></v-file-input>
	      <v-btn
	        class="mr-4"
	        @click="editTraining" :loading="saving">
	        submit
	      </v-btn>
	      <v-btn @click="redirectBack">
	        cancel
	      </v-btn>
	    </form>
</template>
</v-col>
	<v-col cols="4">
	</v-col>
</v-row>
</div>
`
	,
	methods : {
		redirectBack(){
			this.$router.push('/manager-facility');
		},
		editTraining() {
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
				axios.put('rest/TrainingController/', this.trainingDTO)
	              .then(response => {
						alert(this.trainingDTO.name + " uspesno izmenjen!");
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
		}
	},
	mounted () {
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
        axios.get('rest/TrainingController/' + this.trainingId)
              .then(response => {
					this.trainingDTO = response.data;
					this.enteredTrainingType = this.trainingDTO.trainingType;
					this.selectedCoach = this.trainingDTO.coach;
					this.selectedCoach.fullName = this.selectedCoach.name + " " + this.selectedCoach.surname;
					this.imagePreview = this.trainingDTO.image;
					let imageInFormatForBakcEnd = this.imagePreview.split('\\');
					this.trainingDTO.image = imageInFormatForBakcEnd[4];
					this.imageAdded = true;
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
    },
	created(){
		if(!this.userToken){
			this.$router.push('/');
			}
		this.trainingId = this.$route.params.trainingId;
	},
	beforeUpdate(){
		if(!this.userToken){
			this.$router.push('/');
			}
	}
});