Vue.component("my-trainings", {
	name: "my-trainings",
	data: function () {
		    return {
				myTrainingsHistory : null,
				trainingHistoryBackup: null,
				min: 0,
		        max: 0,
		        range: [0, 0],
		        sportsFacilityNameSearched: "",
		        activePickerToDate: null,
		        activePickerFromDate: null,
		        menuToDate: null,
		        menuFromDate: null,
		        toDate: null,
		        fromDate: null,
		        sportsFacilityTypesSearched: "",
		        trainingTypesSearched: "",
		        sportsFacilityTypes: [],
		        trainingTypes: [],
		        sortSportsFacilityNameAsc: false,
		        sortAdditionalPriceAsc: false,
		        sortDateAsc: false,	
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
				<v-row v-for="th in myTrainingsHistory" :key="th.id">
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
							</v-col>
						</v-row>
					</v-card>
				</v-row>
		</v-card>
		</v-col>
		
		<v-col cols="3">
			<v-card width="300" class="mx-auto pa-4 mt-4 text-center" outlined  rounded="8">
				<v-card flat transparent class="justify-center align-center text-center d-flex">
					<v-btn class="mb-4" @click="clearFilters()">Clear</v-btn>
				</v-card>
				<v-card flat transparent class="justify-center align-center text-center d-flex">
					<v-text-field @keyup.enter="filterTrainingHistory" v-model="sportsFacilityNameSearched" label="Sport facility" outlined clearable></v-text-field>
					<v-btn class="mx-4" @click="sortTrainingHistoryBySportsFacilityName" icon><v-icon size="18px">mdi-sort</v-icon></v-btn>
				</v-card>
				<v-card flat transparent class="my-4">
					<v-row>
					    <v-subheader class="center-text">Additional price</v-subheader>
					    <v-btn class="mx-4" @click="sortTrainingHistoryByAdditionalPrice" icon><v-icon size="18px">mdi-sort</v-icon></v-btn>
				   </v-row>
				    <v-card-text>
				      <v-row>
				        <v-col class="px-4">
				          <v-range-slider v-model="range" :max="max" :min="min" hide-details class="align-center" @end="filterTrainingHistory">
				            <template v-slot:prepend>
				              <v-text-field :value="range[0]" class="mt-0 pt-0" hide-details single-line type="number" style="width: 60px"
				              @change="$set(range, 0, $event); filterTrainingHistoryByAdditionalPrice();">
				              </v-text-field>
				            </template>
				            <template v-slot:append>
				              <v-text-field :value="range[1]" class="mt-0 pt-0" hide-details single-line type="number" style="width: 60px" 
				              @change="$set(range, 1, $event); filterTrainingHistory()">
				              </v-text-field>
				            </template>
				          </v-range-slider>
				        </v-col>				         
				      </v-row>
				    </v-card-text>
				  </v-card>
				  <v-card flat transparent>
				  	<v-row>
					  <v-subheader class="center-text">Date of training</v-subheader>
					  <v-btn class="mx-4" @click="sortTrainingHistoryByDate" icon><v-icon size="18px">mdi-sort</v-icon></v-btn>
					</v-row>
					  <template>
						<div>
							<v-menu ref="menuFromDate" v-model="menuFromDate"
								:close-on-content-click="false"
								transition="scale-transition" offset-y min-width="auto">
								<template v-slot:activator="{ on, attrs }">
									<v-text-field v-model="fromDate" label="From" outlined class="mt-4"
										prepend-icon="mdi-calendar" readonly v-bind="attrs"
										v-on="on">
									</v-text-field>
								</template>
								<v-date-picker ref="fromDate" v-model="fromDate" required
									:active-picker.sync="activePickerFromDate"
									@change="saveFromDate(); filterTrainingHistory();"
									></v-date-picker>
							</v-menu>
						</div>
					</template>
					<template>
						<div>
							<v-menu ref="menuToDate" v-model="menuToDate"
								:close-on-content-click="false"
								transition="scale-transition" offset-y min-width="auto">
								<template v-slot:activator="{ on, attrs }">
									<v-text-field v-model="toDate" label="To" outlined
										prepend-icon="mdi-calendar" readonly v-bind="attrs"
										v-on="on">
									</v-text-field>
								</template>
								<v-date-picker ref="toDate" v-model="toDate" required
									:active-picker.sync="activePickerToDate"
									@change="saveToDate(); filterTrainingHistory();"
									></v-date-picker>
							</v-menu>
						</div>
					</template>
				</v-card>
				<v-card flat transparent class="justify-center align-center text-center d-flex">	
					<v-combobox @change="filterTrainingHistory" v-model="sportsFacilityTypesSearched" item-text="name" :items="sportsFacilityTypes" label="Sport facility types" clearable multiple outlined small-chips></v-combobox>
				</v-card>
				<v-card flat transparent class="justify-center align-center text-center d-flex">
					<v-combobox @change="filterTrainingHistory" v-model="trainingTypesSearched" item-text="name" :items="trainingTypes" label="Training type" clearable multiple outlined small-chips></v-combobox>
				</v-card>
			</v-card>
		</v-col>
	</v-row>
`
	,
	watch: {
		menuToDate(val) {
			val && setTimeout(() => (this.activePicker = 'YEAR'))
		},
		menuFromDate(val) {
			val && setTimeout(() => (this.activePicker = 'YEAR'))
		}
	},
	methods : {
		clearFilters(){
		        this.sportsFacilityNameSearched = "";
		        this.toDate = null;
		        this.fromDate = null;
		        this.sportsFacilityTypesSearched = "";
		        this.trainingTypesSearched = "";
		        this.range[0] = this.min;
		        this.range[1] = this.max;
		        this.filterTrainingHistory();
		},
		saveToDate(date) {
			this.$refs.menuToDate.save(date);
		},
		saveFromDate(date) {
			this.$refs.menuFromDate.save(date);
		},
		filterTrainingHistory(){
			this.myTrainingsHistory = JSON.parse(JSON.stringify(this.trainingHistoryBackup));
			this.filterTrainingHistoryBySportFacility();
			this.filterTrainingHistoryByAdditionalPrice();
			this.filterTrainingHistoryByDate();
			this.filterTrainingHistoryBySportFacilityTypes();
			this.filterTrainingHistoryByTrainingTypes();
		},
		filterTrainingHistoryBySportFacility(){
			if(this.sportsFacilityNameSearched == "") return false;
			this.myTrainingsHistory = this.myTrainingsHistory.filter(th => {
				if(th.training.sportsFacility.name.toLowerCase().includes(this.sportsFacilityNameSearched.toLowerCase())) return true;
				return false;
			});
		},
		filterTrainingHistoryByAdditionalPrice(){
			this.myTrainingsHistory = this.myTrainingsHistory.filter(th => {
				if(this.range[0] <= th.training.additionalPrice && th.training.additionalPrice <= this.range[1]) return true;
				return false;
			});
		},
		filterTrainingHistoryByDate(){
			this.myTrainingsHistory = this.myTrainingsHistory.filter(th => {					
				let date = new Date(th.year, th.month - 1, th.day, th.hour, th.minute);
				date = Date.parse(date);
				
				if(!!this.fromDate){
					let from = Date.parse(this.fromDate);
					
					if(date < from) return false;
				}
				if(!!this.toDate){
					let to = Date.parse(this.toDate);
					
					if(date > to) return false;
				}
				return true;
			});
		},
		filterTrainingHistoryBySportFacilityTypes(){
			if(this.sportsFacilityTypesSearched.length == 0) return;
			
			this.myTrainingsHistory = this.myTrainingsHistory.filter(th => {
				if(this.sportsFacilityTypesSearched.includes(th.training.sportsFacility.sportsFacilityType)) return true;
				return false;
			});
		},
		filterTrainingHistoryByTrainingTypes() {
			if(this.trainingTypesSearched.length == 0) return;
			
			this.myTrainingsHistory = this.myTrainingsHistory.filter(th => {
				if(this.trainingTypesSearched.includes(th.training.trainingType)) return true;
				return false;
			});
		},
		sortTrainingHistoryBySportsFacilityName(){
			this.myTrainingsHistory.sort((a, b) => a.training.sportsFacility.name.localeCompare(b.training.sportsFacility.name));
			if(!this.sortSportsFacilityNameAsc)this.myTrainingsHistory.reverse();
			
			this.sortSportsFacilityNameAsc = !this.sortSportsFacilityNameAsc;
		},
		sortTrainingHistoryByAdditionalPrice(){
			this.myTrainingsHistory.sort((a, b) => {return a.training.additionalPrice - b.training.additionalPrice});
			if(!this.sortAdditionalPriceAsc)this.myTrainingsHistory.reverse();
			
			this.sortAdditionalPriceAsc = !this.sortAdditionalPriceAsc;
		},
		sortTrainingHistoryByDate() {
			this.myTrainingsHistory.sort((a, b) => {return a.date - b.date});
			if(!this.sortAdditionalPriceAsc)this.myTrainingsHistory.reverse();
			
			this.sortAdditionalPriceAsc = !this.sortAdditionalPriceAsc;
		},
	},
	mounted () {
       axios.get('rest/TrainingHistoryController/getAllTrainingsHistoryByCertainCoach/' + this.userToken.id + '/-1')
		      .then(response => {
					this.myTrainingsHistory = response.data;
					this.myTrainingsHistory.forEach(th =>{
					let date = new Date(th.date);
					th.date = date;
					th.day = date.getDate();
					th.month = date.getMonth() + 1;
					th.year = date.getYear() + 1900;
					th.hour = date.getHours();
					th.minute = date.getMinutes();
					if(th.training.additionalPrice > this.max) {							
						this.max = th.training.additionalPrice;
						}
				});
				this.trainingHistoryBackup = this.myTrainingsHistory;
				this.range[1] = this.max;
			})
		      .catch(error => {
		            alert(error.message + " GRESKA");
		            }); 
	  axios.get('rest/SportsFacilityTypeController')
		  .then(response => {
		    	let sfts = response.data;
		    	sfts.forEach(sft => {
					this.sportsFacilityTypes.push(sft.name);
				});
			})
		  .catch(error => {
		        alert(error.message + " GRESKA");
		  }); 
	   axios.get('rest/TrainingTypeController')
		  .then(response => {
		    	let tts = response.data;
		    	tts.forEach(tt=>{
					this.trainingTypes.push(tt.name);
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