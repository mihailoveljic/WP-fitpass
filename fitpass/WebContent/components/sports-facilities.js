Vue.component("sports-facilities", {
	data: function () {
		    return {
				sportsFacilities : null,
				sportsFacilitiesBackup : null,
				sportsFacilityNames : new Array(),
				sportsFacilityNameSearched: "",
				sportsFacilityTypes: null,
				sportsFacilityTypeNames: new Array(),
				sportsFacilityTypesSearched: new Array(),
				facilityContents: null,
				facilityContentNames: new Array(),
				facilityContentsSearched: new Array(),
				requiredRating: 0.0
		    }
	},
	template: 
` 
	<div>
		<v-row>
			<v-col cols="3">
			</v-col>
			<v-col cols="6">
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
			</v-col>
			<v-col cols="2">
				<v-card width="300" class="mx-auto pa-4 mt-8 text-center" outlined  rounded="8">
					<v-autocomplete @keyup.enter="filterSportsFacility" v-model="sportsFacilityNameSearched" :items="sportsFacilityNames" label="Sport facility name" clearable solo></v-autocomplete>
					<v-combobox @change="filterSportsFacility" v-model="sportsFacilityTypesSearched" :items="sportsFacilityTypeNames" label="Sport facility types" clearable multiple outlined small-chips></v-combobox>
					<v-combobox @change="filterSportsFacility" v-model="facilityContentsSearched" :items="facilityContentNames" label="Programs" clearable multiple outlined small-chips></v-combobox>
					<label>Required rating:</label>
					<v-rating @change="filterSportsFacility" outlined color="primary" half-increments hover clearable length="5" :value="requiredRating"></v-rating>
				</v-card>
			</v-col>
		</v-row>
	</div>
`
	, 
	methods : {
		filterSportsFacility(){
			this.sportsFacilities = JSON.parse(JSON.stringify(this.sportsFacilitiesBackup));
			this.filterSportsFacilityTypes();
			this.filterFacilityContents();
		},
		filterSportsFacilityTypes(){
			if(this.sportsFacilityTypesSearched.length == 0) return;
			
			this.sportsFacilities = this.sportsFacilities.filter(sportsFacility => {
				if(this.sportsFacilityTypesSearched.includes(sportsFacility.sportsFacilityType)) return true;
				return false;
			});
		},
		filterFacilityContents(){
			if(this.facilityContentsSearched.length == 0) return;
		
			this.sportsFacilities = this.sportsFacilities.filter(sportsFacility => {
				for(let facilityContent of sportsFacility.facilityContents){
					if(this.facilityContentsSearched.includes(facilityContent)){
						for(let searchedFacility of this.facilityContentsSearched){
							if(!sportsFacility.facilityContents.includes(searchedFacility)) return false;
						}
						return true;
					}
				}
				return false;
				});
		}
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
              .then(response => {
					this.sportsFacilities = response.data;
					this.sportsFacilitiesBackup = JSON.parse(JSON.stringify(this.sportsFacilities));
					for(let sportsFacility of this.sportsFacilities){
						this.sportsFacilityNames.push(sportsFacility.name);
					}
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
        axios.get('rest/SportsFacilityTypeController')
              .then(response => {
					this.sportsFacilityTypes = response.data;
					for(let sportsFacilityType of this.sportsFacilityTypes){
						this.sportsFacilityTypeNames.push(sportsFacilityType.name);
					}
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
        axios.get('rest/FacilityContentController')
              .then(response => {
					this.facilityContents = response.data;
					for(let facilityContent of this.facilityContents){
						this.facilityContentNames.push(facilityContent.name);
					}
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
    }
});