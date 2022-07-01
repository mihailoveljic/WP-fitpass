Vue.component("manager-facility", {
	name:"manager-facility",
	data: function () {
		    return {
				manager: null,
				sportsFacility: null,
				editDialog: false,
				editErrorMessages : "",
				editFormHasErrors: false,
				trainingDTO: null,
				trainings: [],
			}				
	},
	template: 
` 
	<div>
		<v-card width="1200" class="mx-auto mt-8" outlined rounded="8">
			<div v-if="!!sportsFacility">
				<v-row class="d-flex flex-row justify-space-between">
					<v-col cols="5">
						<v-img class="ma-4" :src="sportsFacility.image" height="300" width="300" dark></v-img>
					</v-col>
					<v-col cols="7" align-self="center">
						<div class="text-h3 gray--text text-center mt-2">
							{{sportsFacility.name}}
						</div>
						<div class="text-h6 gray--text text-center">
							{{sportsFacility.sportsFacilityType}}
						</div>
						<v-divider class="my-2"></v-divider>
						<div class="text-body-1 gray--text text-center">
							{{sportsFacility.location.address.street}} {{sportsFacility.location.address.number}}, {{sportsFacility.location.address.city}}, {{sportsFacility.location.address.zipCode}}, {{sportsFacility.location.address.country}}
						</div>
						<div class="text-body-1 gray--text text-center" v-bind:class='{"red--text" : !sportsFacility.openStatus,  "green--text" : sportsFacility.openStatus}'>
							{{sportsFacility.workingHours.fromThe}} - {{sportsFacility.workingHours.toThe}}  |   {{checkIfIsOpen(sportsFacility)}}
						</div>
						<v-rating color="primary" class="text-center" half-increments length="5" size="26" :value="sportsFacility.averageRating" readonly></v-rating>
					</v-col>
				</v-row>
				<v-row v-for="training in trainings" :key="training.id">
					<v-card width="100%" class="mx-3" flat outlined>
						<v-row class="d-flex">
							<v-col cols="2">
								<v-img class="ma-1" :src="training.image" height="150" width="150" dark></v-img>
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
								<div class="text-body-1 gray--text text-center">
									Description: {{ training.description }}
								</div>
							</v-col>
							<v-col cols="2">
									<v-btn @click="openEditForm(training)" color="primary" class="ma-4" centered width="100" height="50">
										edit
									</v-btn>
								<v-btn class="ma-4" @click="" width="100" height="50">delete</v-btn>
							</v-col>
						</v-row>
					</v-card>
				</v-row>
				<v-dialog v-model="editDialog" persistent max-width="600px">
					<v-card>
						<v-card-title>
							<span class="text-h5">Edit training</span>
						</v-card-title>
						<v-card-text>
							<v-container>
								<v-row>
									<v-col cols="12" sm="8" md="12">
										<v-text-field label="Name*" required >
										</v-text-field>
									</v-col>
								</v-row>
							</v-container>
							<small>*indicates required field</small>
						</v-card-text>
						<v-card-actions>
							<v-spacer></v-spacer>
							<v-btn color="blue darken-1" text @click="editDialog = false">
								Cancel
							</v-btn>
							<v-btn color="blue darken-1" text @click="editTraining()">
								Edit
							</v-btn>
						</v-card-actions>
					</v-card>
				</v-dialog>
			</div>
		<div ref="map" class="map my-4"></div>
		</v-card>
	</div>
`
	, 
	props:['userToken'],
	methods : {
			checkIfIsOpen: function(sportsFacility){
				let from = sportsFacility.workingHours.fromThe.split(':');
				let to = sportsFacility.workingHours.toThe.split(':');
				let hour = new Date().getHours();
				let minute = new Date().getMinutes();
				if(from[0]<= hour && hour <=to[0]){
					if(from[0]== hour){
						if(from[1]>minute){
							sportsFacility.openStatus = false;
							return "Closed";
						} 
					}else if(to[0]==hour){
						if(to[1]<minute){
							sportsFacility.openStatus = false;
							return "Closed";
						} 
					}else{
						sportsFacility.openStatus = true;
						return "Open";
					}
				}
				sportsFacility.openStatus = false;
				return "Closed";
			},
			openEditForm: function(training){
				this.trainingDTO = training;
				this.editDialog = true;
			},
			editTraining: function(){
				
			}
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
	mounted () {
		axios.get('rest/managers/' + this.userToken.id)
              .then(response => {
					this.manager = response.data;
					axios.get('rest/SportsFacilityController/' + this.manager.sportsFacilityId)
			              .then(response => {
								this.sportsFacility = response.data;
								var markers = new ol.layer.Vector({
								  source: new ol.source.Vector(),
								  style: new ol.style.Style({
								    image: new ol.style.Icon({
								      anchor: [0.5, 0.5],
							          anchorXUnits: 'fraction',
							          anchorYUnits: 'fraction',
							          opacity: 1,
							          scale: 0.1,
								      src: 'img\\marker.png',
								    })
								  })
								});
								
								var marker = new ol.Feature(new ol.geom.Point(ol.proj.fromLonLat([this.sportsFacility.location.longitude, this.sportsFacility.location.latitude])));
								markers.getSource().addFeature(marker);
								var map = new ol.Map({
								
							        target: this.$refs.map,
							        layers: [
							          new ol.layer.Tile({
							            source: new ol.source.OSM()
							          }),
						     		],
							        view: new ol.View({
							          center: ol.proj.fromLonLat([this.sportsFacility.location.longitude, this.sportsFacility.location.latitude]),
							          zoom: 16
							        })
  								});
  								
								map.addLayer(markers);
								
								
							})
			              .catch(error => {
			                    alert(error.message + " GRESKA");
			              });
			              axios.get('rest/TrainingController/getAllTrainingsInCertainSportFacility/' + this.manager.sportsFacilityId)
				              .then(response => {
									this.trainings = response.data;
								})
				              .catch(error => {
				                    alert(error.message + " GRESKA");
				                    });
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
              });
	}
});

