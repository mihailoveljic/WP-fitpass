Vue.component("sports-facility-training", {
	name: "sports-facility-training",
	data: function () {
		    return {
				trainingId: -1,
				sportFacilitysId: -1,
				training: null,
				menu: null,
				date: null,
				activePicker: null,
				trainingErrorMessages: [],
				trainingFormHasErrors: false,
				time: null,
				timePicker: null,
				timePickerOpen: false,
				buyer: null,
				membership: null
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
			<v-card v-if="!!training" width="100%" class="mx-3" flat outlined>
				<v-row class="d-flex" justify="center">
				<v-col cols="2">
				</v-col>
				<v-col cols="6" >
				<v-img class="mt-8" :src="training.image" dark></v-img>
				</v-col>
				<v-col cols="2">
				</v-col>
				</v-row>
				<v-row class="d-flex">
					<v-col cols="2">
						
					</v-col>
					<v-col cols="8" align-self="center">
						<div class="text-h5 gray--text text-center mt-2">
							{{ training.name }}
						</div>
						<div class="text-subtitle-1 gray--text text-center">
							{{ training.trainingType.name }}
						</div>
						<div class="text-body-1 gray--text text-center">
							{{ training.coach.name }} {{ training.coach.surname }}
						</div>
						<div class="text-body-1 gray--text text-center">
							Duration: {{ training.duration }}
						</div>
						<div v-if="training.additionalPrice > 0" class="text-body-1 gray--text text-center">
							Aditional price: {{ training.additionalPrice }}
						</div>
						<div class="text-body-1 gray--text text-center">
							Description: {{ training.description }}
						</div>
					</v-col>
				</v-row>
				<v-row class="d-flex mx-12" justify="center">
					<v-col cols="3">
					</v-col>
					<v-col cols="6">
						<v-menu ref="menu" v-model="menu"
								:close-on-content-click="false"
								transition="scale-transition" offset-y min-width="auto">
								<template v-slot:activator="{ on, attrs }">
									<v-text-field ref="trainingDate" v-model="date" label="Choose date:"
										prepend-icon="mdi-calendar" readonly v-bind="attrs"
										v-on="on">
									</v-text-field>
								</template>
								<v-date-picker v-model="date" required 
									:active-picker.sync="activePicker"
									:min="(new Date()).toISOString().substr(0, 10)" @change="save"
									:rules="[() => !!date || 'This field is required!']" :error-messages="trainingErrorMessages"></v-date-picker>
							</v-menu>
					</v-col>
					<v-col cols="3">
					</v-col>
				</v-row>
				<v-row class="d-flex mx-12" justify="center">
					<v-col cols="3">
					</v-col>
					<v-col cols="6">
						<v-menu ref="timePicker" v-model="timePickerOpen" :close-on-content-click="false" :nudge-right="40" :return-value.sync="time"
							transition="scale-transition" offset-y max-width="290px" min-width="290px">
							
							<template v-slot:activator="{ on, attrs }">
								<v-text-field ref="trainingTime" v-model="time" label="Choose time:" prepend-icon="mdi-clock-time-four-outline" readonly v-bind="attrs"
									v-on="on" :rules="[() => !!time || 'This field is required!']" :error-messages="trainingErrorMessages">
									
								</v-text-field>
							</template>
							<v-time-picker v-if="timePickerOpen" v-model="time" full-width
								@click:minute="$refs.timePicker.save(time)">
							</v-time-picker>
						</v-menu>
					</v-col>
					<v-col cols="3">
					</v-col>
				</v-row>
				<v-row class="d-flex my-8" justify="center">
					<v-btn @click="enroll()" color="primary" x-large>
						Enroll
					</v-btn>
				</v-row>
			</v-card>
		</v-col>
		
		<v-col cols="3">
		
		</v-col>
	</v-row>
`
	,
	methods : {
		save(date) {
				this.$refs.menu.save(date);
		},
		enroll(){
			let date = this.date.split('-');
			let time = this.time.split(':');
			let formattedDate = new Date(date[0], date[1]-1, date[2], time[0], time[1]);
			let enrollRequestDTO ={
				buyerId: this.buyer.id,
				trainingId: this.training.id,
				coachId: this.training.coach.id,
				date: formattedDate.getTime()
			}
			axios.post('rest/TrainingHistoryController/enroll', enrollRequestDTO)
              .then(response => {
					this.managers = response.data;
				})
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
	         	trainingDate: this.date,
	         	trainingTime: this.time
			}
        }
	},
	mounted () {
	 	   axios.get('rest/TrainingController/'+ this.trainingId)
	          .then(response => {
					this.training = response.data;
			  });
           axios.get('rest/buyers/'+ this.userToken.id)
              .then(response => {
					this.buyer = response.data;
					axios.get('rest/MembershipController/'+ this.buyer.membershipId)
		              .then(response => {
							this.membership = response.data;
					  }); 
			  });   
    },
	created(){
		if(!this.userToken){
			this.$router.push('/');
			}
		this.sportsFacilityId = this.$route.params.sportsFacilityId;
		this.trainingId = this.$route.params.trainingId;

	},
	beforeUpdate(){
		if(!this.userToken){
			this.$router.push('/');
			}
	}
});