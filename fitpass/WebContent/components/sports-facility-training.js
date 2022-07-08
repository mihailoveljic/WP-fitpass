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
				membership: null,
				trainingHistory : null,
				dialogShow : false,
				guestbook : {
					comment: "",
					rating : 0
				}
			}
	},
	props:
		['userToken']
	,
	template: 
` <div>
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
	
	
	
	<v-dialog v-model="dialogShow" persistent max-width="600px">
							<v-card>
								<v-card-title>
									<span class="text-h5">Leave your comment</span>
								</v-card-title>
								<v-card-text>
									<v-container>
										<v-row>
											<v-col cols="12" sm="8" md="12">
												<v-text-field label="Comment*" v-model="guestbook.comment">
												</v-text-field>
											</v-col>
											<v-col cols="12" sm="8" md="12">
												<v-rating color="primary" class="text-center" half-increments length="5" size="26" v-model="guestbook.rating"></v-rating>
											</v-col>
										</v-row>
									</v-container>
								</v-card-text>
								<v-card-actions>
									<v-spacer></v-spacer>
									<v-btn color="blue darken-1" text @click="cancelComment()">
										Cancel
									</v-btn>
									<v-btn color="blue darken-1" text @click="submitComment()">
										Submit
									</v-btn>
								</v-card-actions>
							</v-card>
						</v-dialog>
</div>
`
	,
	methods : {
		submitComment(){
			this.guestbook.buyerId = this.userToken.id;
			this.guestbook.sportsFacilityId = this.sportsFacilityId;
			axios.post('rest/GuestbookController/' , this.guestbook)
      			.then(() => {
					this.$router.push("/my-trainings-buyers");
				})
	            .catch(error => {
	                alert(error.message + " GRESKA");
			    });
		},
		cancelCommect(){
			this.$router.push("/my-trainings-buyers");
		},
		save(date) {
				this.$refs.menu.save(date);
		},
		enroll(){
			if(this.membership == ""){
				alert("Nemate aktivnu clanarinu!");
				this.$router.push('/membership-buyers');
				return;
			}
			let date = this.date.split('-');
			let time = this.time.split(':');
			let enrollRequestDTO ={
				buyerId: this.userToken.id,
				trainingId: this.training.id,
				coachId: this.training.coach.id,
				year : date[0],
				month : date[1],
				day : date[2],
				hour : time[0],
				minutes : time[1]
			}
			axios.post('rest/TrainingHistoryController/enroll', enrollRequestDTO)
              .then(response => {
					this.trainingHisotry = response.data;
					if(this.trainingHisotry == ""){
						alert("Nemate aktivnu clanarinu!");
						this.$router.push('/membership-buyers');
						return;
					}
					if(response.data == "COMMENT_NEEDED"){
						this.dialogShow = true;
						return;
					}
					this.$router.push("/my-trainings-buyers");
					return;
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
			  
			axios.get('rest/MembershipController/byBuyer/'+ this.userToken.id)
              .then(response => {
					this.membership = response.data;
					if(this.membership == "") {
						alert("Nemate aktivnu clanarinu!");
						this.$router.push('/membership-buyers');
					}
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