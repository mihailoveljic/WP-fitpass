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
	<v-row v-if="membership!=null && !!membershipType">
		<v-col cols="3">
		
		</v-col>
		
		<v-col cols="6">
			<v-card class = "mx-auto">
					<v-row class = "mx-auto mb-4 text-center text-h3" justify='center'>
						{{ membership.membershipNumber }}
					</v-row>
					<v-divider></v-divider>
					<v-row class = "mx-auto mt-4 text-center text-h4 blue--text" justify='center'>
						
						{{ membershipType.name }}
						
					</v-row>
					<v-row class = "mx-auto text-center text-subtitle-1" justify='center'>
						
						Remaining daily trainings: {{ membership.numberOfRemainingTrainings }}
						
					</v-row>
					<v-row class = "mx-auto text-center text-subtitle-1" justify='center'>
						
						Payment date: {{ membership.paymentDate }}
						
					</v-row>
					<v-row class = "mx-auto text-center text-subtitle-1" justify='center'>
						
						Expiration date: {{ membership.expirationDate }}
						
					</v-row>
				</v-card>
		</v-col>
		
		<v-col cols="3">
		
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
						<v-btn class="ma-8 text-h6" color="primary" x-large>
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
					axios.get('rest/MembershipTypeController/' + this.membership.membershipTypeId)
		              .then(response => {
							this.membershipType = response.data;
						})
		              .catch(error => {
		                    alert(error.message + " GRESKA");
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