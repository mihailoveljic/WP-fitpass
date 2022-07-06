Vue.component("comments-administrator", {
	name: "comments-administrator",
	data: function () {
		    return {
				comments : [],			
			}
	},
	props:
		['userToken']
	,
	template: 
` <div :v-if="userToken.mode=='ADMINISTRATOR'">
<v-row>
	<v-col cols="2" class="text-center">
	</v-col>
	<v-col cols="8">
		<v-simple-table>
			<template v-slot:default>
				<thead>
					<tr>
						<th class="text-left text-h6 flex-row-reverse" width="300">
							Sport Facility
						</th>
						<th class="text-left text-h6 flex-row-reverse" width="300">
							Buyer
						</th>
						<th class="text-left text-h6 flex-row-reverse" width="500">
							Comment
						</th>
						<th class="text-left text-h6 flex-row-reverse" width="100">
							Rating
						</th>
						<th class="text-left text-h6 flex-row-reverse" width="150">
							Status
						</th>
						<th width="150">
						</th>
						<th width="150">
						</th>
					</tr>
				</thead>
				<tbody>
					<tr v-for="comment in comments" :key="comment.id">
						<td>{{ comment.sportsFacility.name }}</td>
						<td>{{ comment.buyerFullName }}</td>
						<td> {{ comment.comment }} </td>
						<td> {{ comment.rating }} </td>
						<td v-if="comment.approvalStatus == 'WAITING_FOR_APPROVAL'"> waiting for approval </td>
						<td v-if="comment.approvalStatus == 'APPROVED'"> approved </td>
						<td v-if="comment.approvalStatus == 'DISAPPROVED'"> disapproved </td>
						
						<td v-show="comment.approvalStatus == 'WAITING_FOR_APPROVAL'">
							<v-btn @click="approve(comment)">Approve</v-btn>
						</td>
						<td v-show="comment.approvalStatus == 'WAITING_FOR_APPROVAL'">
							<v-btn @click="disapprove(comment)">Disapprove</v-btn>
						</td>
					</tr>
				</tbody>
			</template>
		</v-simple-table>
	</v-col>
	<v-col cols="2">
	</v-col>
</v-row>
</div>
`
	,
	methods : {
		approve(comment){
			axios.put('rest/GuestbookController/approve/' + comment.id)
              .then(response => {
					if(response.data == false){
						alert("Neuspesna izmena komentara!");
					}
					this.reloadData();
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
              });
		},
		disapprove(comment){
			axios.put('rest/GuestbookController/disapprove/' + comment.id)
              .then(response => {
					if(response.data == false){
						alert("Neuspesna izmena komentara!");
					}
					this.reloadData();
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
              });
		},
		reloadData(){
			 axios.get('rest/GuestbookController')
              .then(response => {
					this.comments = response.data;
					
					this.comments.forEach(comment =>{
						comment.buyerFullName = comment.buyer.name + " " + comment.buyer.surname;
					})
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
              });
		}
	},
	mounted () {
              axios.get('rest/GuestbookController')
              .then(response => {
					this.comments = response.data;
					
					this.comments.forEach(comment =>{
						comment.buyerFullName = comment.buyer.name + " " + comment.buyer.surname;
					})
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