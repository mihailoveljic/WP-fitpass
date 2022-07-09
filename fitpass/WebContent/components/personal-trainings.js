Vue.component("personal-trainings", {
	name: "personal-trainings",
	data: function () {
		    return {
				myPersonalTrainingsHistory : []
			}
	},
	props:
		['userToken']
	,
	template: 
` 
	<v-row>
		<v-col cols="3">
		
		</v-col>
		
		<v-col cols="6">
		<v-card class="mx-auto mt-8" outlined rounded="8">
				<v-row v-for="th in myPersonalTrainingsHistory" :key="th.id">
					<v-card width="100%" class="mx-3" flat outlined>
						<v-row class="d-flex">
							<v-col cols="3">
								<v-img class="ma-1" :src="th.training.image" height="150" width="150" dark></v-img>
							</v-col>
							<v-col cols="6" align-self="center">
								<div class="text-h5 gray--text text-center mt-2">
									{{ th.training.name }}
								</div>
								<div class="text-subtitle-1 gray--text text-center">
									{{ th.trainingType }}
								</div>
								<div class="text-body-1 gray--text text-center">
									{{ th.coach.name }} {{ th.coach.surname }}
								</div>
								<div class="text-body-1 gray--text text-center">
									Duration: {{ th.training.duration }}
								</div>
								<div v-if="th.training.additionalPrice > 0" class="text-body-1 gray--text text-center">
									Aditional price: {{ th.training.additionalPrice }}
								</div>
								<div class="text-body-1 gray--text text-center">
									Description: {{ th.training.description }}
								</div>
							</v-col>
							<v-col cols="3" class="ma-auto" align-self="center">
									<div class="text-h6 gray--text text-center">
									{{ th.training.sportsFacility.name }}
								</div>
								<v-divider></v-divider>
								<div class="text-body-1 gray--text text-center">
									Date: {{ th.day }}.{{ th.month }}.{{ th.year }}.
								</div>
								<div class="text-body-1 gray--text text-center">
									Time: {{ th.hour }}:{{ th.minute }}
								</div>
								<v-btn class="mt-4" @click="cancelPersonalTraining(th)">Cancel training</v-btn>
							</v-col>
						</v-row>
					</v-card>
				</v-row>
		</v-card>
		</v-col>
		
		<v-col cols="3">
		
		</v-col>
	</v-row>
`
	,
	methods : {
		cancelPersonalTraining(th) {
			let currentDate = new Date();
			let dateOfTraining = new Date(th.date);
			let currentDateInMilSec = currentDate.getTime();
			if((dateOfTraining.getTime()) < (currentDateInMilSec + 172800000))
			{
				alert("Treninge mozete otkazati minimalno 2 dana ranije!");
				return;
			}
			let isExecuted = confirm("Are you sure to cancel this training?");
			if(!isExecuted){
				return;
			}
			axios.delete('rest/TrainingHistoryController/cancelPersonalTraining/' + th.id)
				              .then(response => {
								if(response.data == true){
									axios.get('rest/TrainingHistoryController/getAllTrainingsHistoryByCertainCoach/' + this.userToken.id + '/1')
				              			.then(response => {
											this.myPersonalTrainingsHistory = response.data;
											this.myPersonalTrainingsHistory.forEach(th =>{
											let date = new Date(th.date);
											th.day = date.getDate();
											th.month = date.getMonth() + 1;
											th.year = date.getYear() + 1900;
											let time = date.toTimeString().split(' ')[0];
											time = time.split(':');
											th.hour = time[0];
											th.minute = time[1];
											});
									})
				              		.catch(error => {
				                    	alert(error.message + " GRESKA");
				                    	});       
						}	
				})
				              .catch(error => {
				                    alert(error.message + " GRESKA");
				                    });       
			
		}
	},
	mounted () {
       axios.get('rest/TrainingHistoryController/getAllTrainingsHistoryByCertainCoach/' + this.userToken.id + '/1')
				              .then(response => {
									this.myPersonalTrainingsHistory = response.data;
									this.myPersonalTrainingsHistory.forEach(th =>{
									let date = new Date(th.date);
									th.day = date.getDate();
									th.month = date.getMonth() + 1;
									th.year = date.getYear() + 1900;
									let time = date.toTimeString().split(' ')[0];
									time = time.split(':');
									th.hour = time[0];
									th.minute = time[1];
								});
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