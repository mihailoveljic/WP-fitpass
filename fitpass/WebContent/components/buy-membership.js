Vue.component("buy-membership", {
	name: "buy-membership",
	data: function () {
		    return {
			
				membershipType : null,
				membershipTypeId : -1,
				buyer : null,
				buyerType : null,
				promoCodeMark : "",
				promoCode : null,
				promoCodeDenied : false,
				membership : null,
				newMembership: null

			}
	},
	props:
		['userToken']
	,
	template: 
` 
	<v-row class="ma-4">
		<v-col cols="1">
		
		</v-col>
		
		<v-col cols="6">
			<v-card v-if="!!membershipType && !!buyerType" class = "mx-auto">
					<v-row class = "mx-auto">
						<v-img class="ma-4" :src="'data/img/membershipTypes/' + membershipType.image" height="220" width="220" dark></v-img>
					</v-row>
					<v-row class = "mx-auto mb-4 text-center text-h3" justify='center'>
						{{ membershipType.name }}
					</v-row>
					<v-divider></v-divider>
					<v-row class = "mx-auto mt-4 text-center text-h4 blue--text text-decoration-line-through" justify='center'>
						
						Price: {{ membershipType.price }} RSD
						
					</v-row>
					<v-row class = "mx-auto mt-4 text-center text-h4 blue--text" justify='center'>
						
						Your price: {{ discountPrice }} RSD
						
					</v-row>
					<v-row class = "mx-auto text-center text-subtitle-1" justify='center'>
						
						Daily trainings: {{ membershipType.numberOfDailyTrainings }}
						
					</v-row>
					<v-row class = "mx-auto text-center text-subtitle-1" justify='center'>
						
						Duration: {{ membershipType.durationInDays }} days
						
					</v-row>
					<v-row class = "mx-auto text-center" justify='center'>
					<v-col cols="3">
					</v-col>
					<v-col cols="6">
						<v-text-field v-on:blur="checkIfPromoCodeExists" class="mx-auto" v-model="promoCodeMark" label="Promo Code"></v-text-field>
					</v-col>
					<v-col cols="3">
					</v-col>
					</v-row>
					<v-row class = "mx-auto text-center" justify='center'>
					<v-col cols="2">
					</v-col>
					<v-col cols="8">
						<div v-if="!!promoCode" class="mx-auto green--text">Promo code successfully added!<br></br>effect discont: {{ promoCode.discount }}%</div>
						<div v-if="promoCodeDenied" class="red--text">Neispravan promo code!</div>
					</v-col>
					<v-col cols="2">
					</v-col>
					</v-row>
					<v-row class = "mx-auto text-center" justify='center'>
						<v-btn @click="buyMembership" class="ma-8 text-h6" color="primary" x-large>
							Buy!
						</v-btn>
					</v-row>
				</v-card>
		</v-col>
		
		<v-col cols="4">
			<v-row>
				<buyer-info :userToken="userToken"></buyer-info>
			</v-row>
		</v-col>
		<v-col cols="1">
		
		</v-col>
	</v-row>
`
	,
	methods : {
		buyMembership(){
				let pd = new Date().getTime();
				let ed = pd + this.membershipType.durationInDays*(86400000);
			
				let membership = {
					id : -1,
					membershipNumber : -1,
					membershipTypeId : parseFloat(this.membershipTypeId),
					paymentDate : pd,
					expirationDate : ed,
					price : this.discountPrice,
					buyerId : this.userToken.id,
					isActive : true,
					numberOfRemainingTrainings : this.membershipType.numberOfDailyTrainings,
					isUnlimited : false,
					isDeleted : false
				}
				if(this.promoCodeMark == ""){
					axios.post('rest/MembershipController/-1' , membership)
		              .then(response => {
						})
		              .catch(error => {
		                    alert(error.message + " GRESKA u pretrazi promokoda");
		                    });
				} else {
					axios.post('rest/MembershipController/' + this.promoCodeMark , membership)
		              .then(response => {
						})
		              .catch(error => {
		                    alert(error.message + " GRESKA u pretrazi promokoda");
		                    });
				}
				
		       this.$router.push("/membership-buyers");
	
		},
		checkIfPromoCodeExists(){
			if(this.promoCodeMark == ""){
						return;
					}
			axios.get('rest/PromoCodeController/checkIfPromoCodeExists/' + this.promoCodeMark)
              .then(response => {
					this.promoCode = response.data;
					if(this.promoCode == ""){
						this.promoCodeDenied = true;
						return;
					}
					this.promoCodeDenied = false;
				})
              .catch(error => {
                    alert(error.message + " GRESKA u pretrazi promokoda");
                    });
		}
	},
	computed:{
		discountPrice(){
			if(!!this.promoCode){
				return this.membershipType.price*(1 - this.buyerType.discount/100)*(1 - this.promoCode.discount/100);
			} else {
				return this.membershipType.price*(1 - this.buyerType.discount/100);
			}
		}
	},
	mounted () {
            axios.get('rest/MembershipTypeController/' + this.membershipTypeId)
              .then(response => {
					this.membershipType = response.data;
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
                    
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
                    
                    
                    
             axios.get('rest/MembershipController/byBuyer/' + this.userToken.id)
              .then(response => {
					this.membership = response.data;
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
    },
	created(){
		if(!this.userToken){
			this.$router.push('/');
			}
		this.membershipTypeId = this.$route.params.membershipTypeId;
	},
	beforeUpdate(){
		if(!this.userToken){
			this.$router.push('/');
			}
	}
});