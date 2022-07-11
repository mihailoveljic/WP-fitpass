Vue.component("membership-buyers", {
	name: "membership-buyers",
	data: function () {
		    return {
				membershipTypes : [],
				membership : null,
				membershipType : null,
				buyer : null
			}
	},
	props:
		['userToken']
	,
	template: 
` 
<div>
	<v-row>
		<v-col cols="1">
		</v-col>
		<v-col cols="5">
			<div class=" d-flex justify-center mx-auto text-h2 my-8">Active membership</div>
		</v-col>
		<v-col cols="5">
			<div class=" d-flex justify-center mx-auto text-h2 my-8">Buyer status</div>
		</v-col>
		<v-col cols="1">
		</v-col>
	</v-row>
	<v-row class = "my-8">
		<v-col cols="1">
		
		</v-col>
		
		<v-col cols="5" v-if="membership!=null && !!membershipType && membership.isActive">
			
			<v-card class="pa-4">
	
					<v-row class = "mx-auto text-center text-h4 blue--text mt-4" justify='center'>
						
						Membership Type: {{ membershipType.name }}
						
					</v-row>
					<v-row class = " mb-5 mx-auto text-center text-caption" justify='center'>
						Order id: {{ membership.membershipNumber }}
					</v-row>
					<v-row class = " my-1 mx-auto text-center text-h6" justify='center'>
						
						Remaining daily trainings: {{ membership.numberOfRemainingTrainings }}
						
					</v-row>
					<v-row class = "my-1 mx-auto text-center text-subtitle-1" justify='center'>
						
						Payment date: {{ membership.paymentDate.day }}-{{ membership.paymentDate.month }}-{{ membership.paymentDate.year }} 
						
					</v-row>
					<v-row class = "my-1 mx- text-center text-subtitle-1" justify='center'>
						
						Expiration date: {{ membership.expirationDate.day }}-{{ membership.expirationDate.month }}-{{ membership.expirationDate.year }}
						
					</v-row>
				</v-card>
		</v-col>
		
		<v-col v-else cols="5">
			<div class=" d-flex justify-center mx-auto text-h2 my-8">Active membership</div>
			<v-card class = "pa-4">
	
					<v-row class = "mx-auto text-center text-h4 blue--text my-4" justify='center'>
						
						No active membership!
						
					</v-row>
				</v-card>
		</v-col>
		
		<v-col cols="5">
			<buyer-info :userToken="userToken"></buyer-info>
		</v-col>
		
		<v-col cols="1">
		
		</v-col>
	</v-row>
	

	<v-row>
	<v-col cols="1">
	</v-col>
	<v-col cols="10">
		<v-row>
			<v-col cols="4" v-for="membershipType in membershipTypes" :key="membershipType.id" class = "mx-auto">
				<v-card class = "mx-auto">
					<v-row class = "mx-auto">
						<v-img class="ma-4" :src="'data/img/membershipTypes/' + membershipType.image" height="220" width="220" dark></v-img>
					</v-row>
					<v-row class = "mx-auto mb-4 text-center text-h3" justify='center'>
						{{ membershipType.name }}
					</v-row>
					<v-divider></v-divider>
					<v-row class = "mx-auto mt-4 text-center text-h4 blue--text" justify='center'>
						
						Price: {{ membershipType.price }} RSD
						
					</v-row>
					<v-row class = "mx-auto text-center text-subtitle-1" justify='center'>
						
						Daily trainings: {{ membershipType.numberOfDailyTrainings }}
						
					</v-row>
					<v-row class = "mx-auto text-center text-subtitle-1" justify='center'>
						
						Duration: {{ membershipType.durationInDays }} days
						
					</v-row>
					<v-row class = "mx-auto text-center" justify='center'>
						<v-btn class="ma-8 text-h6" color="primary" x-large :to="'/membership-buyers/buy-membership/' + membershipType.id">
							Buy!
						</v-btn>
					</v-row>
				</v-card>
			</v-col>
		</v-row>
	</v-col>
	<v-col cols="1">
	</v-col>
	</v-row>
</div>
`
	,
	methods : {
		
	},
	mounted () {
              axios.get('rest/MembershipTypeController')
              .then(response => {
					this.membershipTypes = response.data;
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
              axios.get('rest/MembershipController/byBuyer/' + this.userToken.id)
              .then(response => {
					this.membership = response.data;
					let date1 = new Date(this.membership.paymentDate);
					let date2 = new Date(this.membership.expirationDate);
					this.membership.paymentDate = {
						day: date1.getDate(),
						month : date1.getMonth() + 1,
						year : date1.getYear() + 1900
					}
					this.membership.expirationDate = {
						day: date2.getDate(),
						month : date2.getMonth() + 1, 
						year : date2.getYear() + 1900
					}
					if(this.membership !== ""){
						axios.get('rest/MembershipTypeController/' + this.membership.membershipTypeId)
		              .then(response => {
							this.membershipType = response.data;
						})
		              .catch(error => {
		                    alert(error.message + " GRESKA");
		                    });
		                }
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