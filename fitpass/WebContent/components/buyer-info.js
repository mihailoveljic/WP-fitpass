Vue.component("buyer-info", {
	name: "buyer-info",
	data: function () {
		    return {
			
				buyer : null,
				buyerType : null
			}
	},
	props:
		['userToken']
	,
	template: 
` 
	<v-row>
		<v-col cols="1">
		
		</v-col>
		
		<v-col cols="10">
			<v-card v-if="!!buyer && !!buyerType" class = "mx-auto">
					
					<v-row class = "text-center text-h5" justify='center'>
						{{ buyer.name }} - {{ buyerType.typeName }} kupac
					</v-row>
					<v-row class = "text-center text-h6" justify='center'>
						Discount: {{ buyerType.discount }}%
					</v-row>
					<v-row class = "text-center text-subtitle-1" justify='center'>
						Collected points: {{ buyer.numberOfCollectedPoints }}
					</v-row>
					<v-row class = "text-center text-subtitle-1" justify='center'>
						Points for upgrade: {{ buyerType.requiredPointsForUpgrade }}
					</v-row>
				</v-card>
		</v-col>
		
		<v-col cols="1">
		
		</v-col>
	</v-row>
`
	,
	methods : {
		
	},
	mounted () {
              axios.get('rest/buyers/' + this.userToken.id)
              .then(response => {
					this.buyer = response.data;
					axios.get('rest/BuyerTypeController/' + this.buyer.buyerTypeId)
				              .then(response => {
									this.buyerType = response.data;
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