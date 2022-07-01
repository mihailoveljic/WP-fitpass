Vue.component("manager-facility", {
	name:"manager-facility",
	data: function () {
		    return {
				manager: null,
				sportsFacility: null
			}				
	},
	template: 
` 
	<v-card width="1200" height="800" class="mx-auto mt-8" outlined rounded="8">
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
		</div>
		<div ref="map" class="map">
		</div>
	</v-card>
	
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
								var map = new ol.Map({
								
							        target: this.$refs.map,
							        layers: [
							          new ol.layer.Tile({
							            source: new ol.source.OSM()
							          }),
							          new ol.layer.Vector({
									     source: new ol.source.Vector({
									         features: [
									             new ol.Feature({
									                 geometry: new ol.geom.Point(ol.proj.fromLonLat([this.sportsFacility.location.longitude, this.sportsFacility.location.latitude]))
									             })
									         ]
									     })
 										})
						     		],
							        view: new ol.View({
							          center: ol.proj.fromLonLat([this.sportsFacility.location.longitude, this.sportsFacility.location.latitude]),
							          zoom: 16
							        })
  								});
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

