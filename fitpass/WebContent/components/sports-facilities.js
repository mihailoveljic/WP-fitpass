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
				requiredRating: 0.0,
				sportsFacilityCountrySearched: "",
				sportsFacilityCitySearched: "",
				isOpenCheckbox: false,
				sortNameAsc : false,
				sortCountryAsc : false,
				sortCityAsc : false,
				sortRatingAsc : false
		    }
	},
	props: ["mode"],
	template: 
` 
	<div>
		<v-row>
			<v-col cols="3">
			<v-btn v-if="mode=='ADMINISTRATOR'" color="primary" class="ma-0 d-none d-lg-flex""
								 centered x-large to="/newSportFacility">
									New sport facility
								</v-btn>
			</v-col>
			<v-col cols="6">
				<v-card v-for="sportsFacility in sportsFacilities" :key="sportsFacility.id" @click="openSportFacility(sportsFacility)" width="800" height="252" class="mx-auto mt-8" outlined  rounded="8" hover>
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
								{{sportsFacility.location.address.street}} {{sportsFacility.location.address.number}}, {{sportsFacility.location.address.city}}, {{sportsFacility.location.address.zipCode}}, {{sportsFacility.location.address.country}}
							</div>
							<div class="text-body-1 gray--text text-center" v-bind:class='{"red--text" : !sportsFacility.openStatus,  "green--text" : sportsFacility.openStatus}'>
								{{sportsFacility.workingHours.fromThe}} - {{sportsFacility.workingHours.toThe}}  |   {{checkIfIsOpen(sportsFacility)}}
							</div>
							<v-rating color="primary" class="text-center" half-increments length="5" size="26" :value="sportsFacility.averageRating" readonly></v-rating>
						</v-col>
					</v-row>
				</v-card>
			</v-col>
			<v-col cols="2">
				<v-card width="300" class="mx-auto pa-4 mt-8 text-center" outlined  rounded="8">
					<v-card flat transparent class="justify-center align-center text-center d-flex">
						<v-btn class="mb-4" @click="clearFilters()">Clear</v-btn>
					</v-card>
					<v-card class="justify-center align-center text-center d-flex">
						<v-text-field @keyup.enter="filterSportsFacility" v-model="sportsFacilityNameSearched" label="Sport facility name" outlined clearable></v-text-field>
						<v-btn class="mx-4"  @click="sortByName" icon><v-icon size="18px">mdi-sort</v-icon></v-btn>
					</v-card>
					<v-card class="justify-center align-center text-center d-flex">
						<v-text-field @keyup.enter="filterSportsFacility" v-model="sportsFacilityCountrySearched" label="Country" outlined clearable></v-text-field>
						<v-btn class="mx-4"  @click="sortByCountry" icon><v-icon size="18px">mdi-sort</v-icon></v-btn>
					</v-card>
					<v-card class="justify-center align-center text-center d-flex">
						<v-text-field @keyup.enter="filterSportsFacility" v-model="sportsFacilityCitySearched" label="City" outlined clearable></v-text-field>
						<v-btn class="mx-4"  @click="sortByCity" icon><v-icon size="18px">mdi-sort</v-icon></v-btn>
					</v-card>
					<v-card class="justify-center align-center text-center d-flex">
						<v-combobox @change="filterSportsFacility" v-model="sportsFacilityTypesSearched" :items="sportsFacilityTypeNames" label="Sport facility types" clearable multiple outlined small-chips></v-combobox>
					</v-card>
					<v-card class="justify-center align-center text-center d-flex">
						<v-combobox @change="filterSportsFacility" v-model="facilityContentsSearched" :items="facilityContentNames" label="Programs" clearable multiple outlined small-chips></v-combobox>
					</v-card>
					<v-card class="justify-center align-center text-center d-flex">
						<label>Required rating:</label>
					</v-card>
						<v-card class="justify-center align-center text-center d-flex">
							<v-rating @input="filterSportsFacility" outlined color="primary" half-increments hover clearable length="5" v-model="requiredRating"></v-rating>
							<v-btn class="mx-4"  @click="sortByRating" icon><v-icon size="18px">mdi-sort</v-icon></v-btn>
						</v-card>
					<v-card class="justify-center align-center text-center d-flex">
						<v-checkbox class="justify-center" @change="filterSportsFacility" v-model="isOpenCheckbox" label="Opened"></v-checkbox>
					</v-card>
				</v-card>
			</v-col>
		</v-row>
	</div>
`
	, 
	methods : {
		clearFilters(){
				this.sportsFacilityNameSearched = "";
				this.sportsFacilityTypesSearched = new Array();
				this.facilityContentsSearched = new Array();
				this.requiredRating = 0.0,
				this.sportsFacilityCountrySearched = "",
				this.sportsFacilityCitySearched = "",
				this.isOpenCheckbox = false,
		        this.filterSportsFacility();
		},
		openSportFacility(sportsFacility){
			this.$router.push('/sports-facility/' + sportsFacility.id);
		},
		sortByName(){
			if(this.sortNameAsc){
				this.sportsFacilities.sort((a, b) => a.name.localeCompare(b.name));
			}else{				
				this.sportsFacilities.sort((a, b) => a.name.localeCompare(b.name));
				this.sportsFacilities.reverse();
			}
			this.sortNameAsc = !this.sortNameAsc;
		},
		sortByCountry(){
			if(this.sortCountryAsc){
				this.sportsFacilities.sort((a, b) => a.location.address.country.localeCompare(b.location.address.country));
			}else{				
				this.sportsFacilities.sort((a, b) => a.location.address.country.localeCompare(b.location.address.country));
				this.sportsFacilities.reverse();
			}
			this.sortCountryAsc = !this.sortCountryAsc;
		},
		sortByCity(){
			if(this.sortCityAsc){
				this.sportsFacilities.sort((a, b) => a.location.address.city.localeCompare(b.location.address.city));
			}else{				
				this.sportsFacilities.sort((a, b) => a.location.address.city.localeCompare(b.location.address.city));
				this.sportsFacilities.reverse();
			}
			this.sortCityAsc = !this.sortCityAsc;
		},
		sortByRating(){
			if(this.sortRatingAsc){
				this.sportsFacilities.sort((a, b) => {
					return a.rating - b.rating;
				});
			}else{				
				this.sportsFacilities.sort((a, b) => {
					return a.rating - b.rating;
				});
				this.sportsFacilities.reverse();
			}
			this.sortRatingAsc = !this.sortRatingAsc;
		},
		filterSportsFacilityByRating(){
			if(this.requiredRating <= 0.0) return false;
			this.sportsFacilities = this.sportsFacilities.filter(sportsFacility => {
				if(sportsFacility.averageRating >= this.requiredRating) return true;
				return false;
			});
		},
		filterSportsFacilityByCountry(){
			if(this.sportsFacilityCountrySearched == "") return false;
			this.sportsFacilities = this.sportsFacilities.filter(sportsFacility => {
				if(sportsFacility.location.address.country.toLowerCase().includes(this.sportsFacilityCountrySearched.toLowerCase())) return true;
				return false;
			});
		},
		filterSportsFacilityByCity(){
			if(this.sportsFacilityCitySearched == "") return false;
			this.sportsFacilities = this.sportsFacilities.filter(sportsFacility => {
				if(sportsFacility.location.address.city.toLowerCase().includes(this.sportsFacilityCitySearched.toLowerCase())) return true;
				return false;
			});
		},
		filterSportsFacilityByName(){
			if(this.sportsFacilityNameSearched == "") return false;
			this.sportsFacilities = this.sportsFacilities.filter(sportsFacility => {
				if(sportsFacility.name.toLowerCase().includes(this.sportsFacilityNameSearched.toLowerCase())) return true;
				return false;
			});
		},
		filterSportsFacility(){
			this.sportsFacilities = JSON.parse(JSON.stringify(this.sportsFacilitiesBackup));
			this.filterSportsFacilityTypes();
			this.filterFacilityContents();
			this.filterSportsFacilityByName();
			this.filterSportsFacilityByCountry();
			this.filterSportsFacilityByCity();
			this.filterSportsFacilityByRating();
			this.filterSportsFacilityByOpenStatus();
		},
		filterSportsFacilityByOpenStatus(){
			if(this.isOpenCheckbox){
				this.sportsFacilities = this.sportsFacilities.filter(sportsFacility => (sportsFacility.openStatus));
			}
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
		},
		checkIfIsOpen: function(sportsFacility){
				let from = sportsFacility.workingHours.fromThe.split(':');
				let to = sportsFacility.workingHours.toThe.split(':');
				
				let now = new Date();
			
				let openTime = new Date(now.getYear() + 1900, now.getMonth(), now.getDate(), from[0], from[1]);
				let closeTime = new Date(now.getYear() + 1900, now.getMonth(), now.getDate(), to[0], to[1]);
				
				if(openTime <= now && now <= closeTime){
					sportsFacility.openStatus = true;
					return "Open";
				}else{
					sportsFacility.openStatus = false;
					return "Closed";
				}
				
			}
	},
	computed: {
		
	},
	mounted () {
		axios.get('rest/SportsFacilityController')
              .then(response => {
					this.sportsFacilities = response.data;
					for(let sportsFacility of this.sportsFacilities){
						this.checkIfIsOpen(sportsFacility);
					}
					this.sportsFacilities = this.sportsFacilities.sort((a, b) => Number(b.openStatus) - Number(a.openStatus));
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