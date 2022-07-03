Vue.component("sports-facility", {
	name: "sports-facility",
	data: function () {
		    return {
				sportsFacility: null,
				trainings: [],
				sportsFacilityId: -1,
			}				
	},
	template: 
` 
	<div>
		<v-row>
		<v-col cols="2">
		</v-col>
		<v-col cols="8">
		<v-card width="1200" class="mx-auto mt-8" outlined rounded="8">
			<div v-if="!!sportsFacility">
				<v-row class="d-flex flex-row justify-space-between">
					<v-col cols="4">
						<v-img class="ma-4" :src="sportsFacility.image" height="300" width="300" dark></v-img>
					</v-col>
					<v-col cols="3" class="mx-auto my-6">
						<v-card flat>
							<v-list dense outlined >
						        <v-subheader class="text-h6">Content of the facility</v-subheader>
						      	<v-divider></v-divider>
						        <v-list-item v-for="content in sportsFacility.facilityContents" :key="content.id">
						            <v-list-item-content>
							            <v-list-item-title v-text="content"></v-list-item-title>
						            </v-list-item-content>
					        	</v-list-item>
							</v-list>
						</v-card>
					</v-col>
					<v-col cols="5" align-self="center">
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
								<div v-if="training.additionalPrice > 0" class="text-body-1 gray--text text-center">
									Aditional price: {{ training.additionalPrice }}
								</div>
								<div class="text-body-1 gray--text text-center">
									Description: {{ training.description }}
								</div>
							</v-col>
							<v-col cols="2" class="ma-auto">
									<v-btn @click="enrollTraining(training)" v-if="!!userToken" color="primary"  centered width="100" height="50">
										Enroll
									</v-btn>
							</v-col>
						</v-row>
					</v-card>
				</v-row>
			</div>
		<v-divider></v-divider>
		<comments-carousel :userToken="userToken" :sportsFacilityId="sportsFacilityId"></comments-carousel>
		<div ref="map" class="map my-4"></div>
		</v-card>
		</v-col>
			<v-col cols="2">
				
			</v-col>
		</v-row>
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
	},
	created(){
		this.sportsFacilityId = this.$route.params.sportsFacilityId;
	},
	mounted () {              
      	axios.get('rest/SportsFacilityController/' + this.sportsFacilityId)
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
              
      axios.get('rest/TrainingController/getAllTrainingsInCertainSportFacility/' + this.sportsFacilityId)
            .then(response => {
				this.trainings = response.data;
			 })
            .catch(error => {
                alert(error.message + " GRESKA");
	  });
	}
});