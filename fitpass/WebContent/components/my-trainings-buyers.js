Vue.component("my-trainings-buyers", {
	name: "my-trainings-buyers",
	data: function () {
		    return {
				trainingHistory: null				
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
				<v-row v-for="training in trainingHistory" :key="training.id">
					<v-card width="100%" class="mx-3" flat outlined>
						<v-row class="d-flex">
							<v-col cols="2">

							</v-col>
							<v-col cols="8" align-self="center">
								<div class="text-h5 gray--text text-center mt-2">
									{{ training }}
								</div>
								<div class="text-subtitle-1 gray--text text-center">
									
								</div>
								<div class="text-body-1 gray--text text-center">
									
								</div>
								<div class="text-body-1 gray--text text-center">
									
								</div>
								<div v-if="training.additionalPrice > 0" class="text-body-1 gray--text text-center">
									
								</div>
								<div class="text-body-1 gray--text text-center">
									
								</div>
							</v-col>
							<v-col cols="2" class="ma-auto">
									
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
		
	},
	mounted () {
             axios.get('rest/TrainingHistoryController')
              .then(response => {
					this.trainingHistory = response.data;
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