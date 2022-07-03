Vue.component("comments-carousel", {
	name: "comments-carousel",
	data: function () {
		    return {
				model: 0,
				comments: null		
			}
	},
	props:
		['userToken', 'sportsFacilityId']
	,
	template: 
` 
	<v-carousel v-model="model" class="mt-4">
   		<v-carousel-item v-for="comment in comments" :key="comment.id">
    		<v-sheet height="100%" tile>
    			<v-container fill-height fluid>
		        	<v-row align="center" justify="center">
		        		<v-col class="my-auto" align="center" justify="center">
		        			<div v-if="userToken.role == 'MENADZER' || userToken.role == 'ADMINISTRATOR'">
			        			<div v-if="comment.approvalStatus == 'APPROVED'" class="text-overline green--text">
					          		odobren
				            	</div>
				            	<div v-else-if="comment.approvalStatus == 'DISAPPROVED'" class="text-overline red--text">
					          		neodobren
				            	</div>
				            	<div v-else class="text-overline blue--text">
					          		ceka na odobrenje
				            	</div>
			            	</div>
		        			<div class="text-h5">
				          		{{ comment.comment }}
			            	</div>
			            	<div class="text-subtitle-1">
						    	{{ comment.buyer.name }} {{ comment.buyer.surname }}
						    </div>
						    <div>
						    	<v-rating :value="comment.rating"></v-rating>
						    </div>    
			        	</v-col>
		          	</v-row>
	    		</v-container>
	      	</v-sheet>
		</v-carousel-item>
  	</v-carousel>
`
	,
	methods : {
		
	},
	mounted () {
         	axios.get('rest/GuestbookController/')
              .then(response => {
					let comments = response.data;
					axios.get('rest/buyers/getBuyersWhoVisitedCertainSportFacility/' + this.sportsFacilityId)
			              .then(response => {
							let buyers = response.data;
							comments.forEach(comment => {
								comment.buyer = buyers.find(buyer => buyer.id == comment.buyerId);
								
							});
							this.comments = comments;
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