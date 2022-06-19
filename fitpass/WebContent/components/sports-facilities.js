Vue.component("sports-facilities", {
	data: function () {
		    return {
				sportsFacilities : null
		    }
	},
	template: 
` 
	<div>
		<v-card v-for="sportsFacility in sportsFacilities" :key="sportsFacility.id" width="800" height="252" class="mx-auto mt-8" outlined  rounded="8" hover>
			<v-row class="d-flex flex-row justify-space-between">
				<v-col cols="5">
					<v-img class="ma-4" :src="sportsFacility.image" height="220" width="220" dark></v-img>
				</v-col>
				<v-col cols="7" align-self="center">
					<div class="text-h4 gray--text text-center mt-2">
						{{sportsFacility.name}}
					</div>
					<div class="text-body-1 gray--text text-center">
						{{sportsFacility.sportsFacilityType}}
					</div>
					<v-divider class="my-2"></v-divider>
					<div class="text-body-1 gray--text text-center">
						{{sportsFacility.location.address.street}} {{sportsFacility.location.address.number}}, {{sportsFacility.location.address.city}}, {{sportsFacility.location.address.zipCode}}
					</div>
					<div class="text-body-1 gray--text text-center green--text">
						{{sportsFacility.workingHours.fromThe}} - {{sportsFacility.workingHours.toThe}}  <!--|   {{checkIfIsOpen(sportsFacility)}}-->
					</div>
					<v-rating color="primary" class="text-center" half-increments length="5" size="26" :value="sportsFacility.averageRating" readonly></v-rating>
				</v-col>
			</v-row>
		</v-card>
	</div>
`
	, 
	methods : {
		
	},
	computed: {
		/*checkIfIsOpen: function(sportsFacility){
			if(sportsFacility.openStatus){
				if(sportsFacility.workingHours.fromThe > new Date().getHours < sportsFacility)
			}
		}*/
	},
	mounted () {
		axios.get('rest/SportsFacilityController')
              .then(response => (this.sportsFacilities = response.data))
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
    }
});