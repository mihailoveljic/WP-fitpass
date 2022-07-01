Vue.component("manager-facility-buyers", {
	name:"manager-facility-buyers",
	data:function(){
		return {
			buyers : [],
			manager : null
		}
	},
	template:
`
<div>
	<template>
		<v-row>
			<v-col cols="3" class="text-center"></v-col> 
			<v-col cols="6">
				<v-simple-table >
				    <template v-slot:default>
				      	<thead>
					        <tr>
					         	<th class="text-left text-h6 flex-row-reverse">
						        	Name
						        </th>
						        <th class="text-left text-h6 flex-row-reverse">
						            Surname
						        </th>
					        </tr>
				      	</thead>
				      	<tbody>
					        <tr
					          v-for="buyer in buyers"
					          :key="buyer.username">
					          <td>{{ buyer.name }}</td>
					          <td>{{ buyer.surname }}</td>
				        	</tr>
						</tbody>
			    	</template>
			  	</v-simple-table>
			  </v-col>
			<v-col cols="3">
			</v-col>
		</v-row>
	</template>
</div>
`,

	props:['userToken'],
	methods: {
		
	},
	mounted() {
		 axios.get('rest/managers/' + this.userToken.id)
              .then(response => {
					this.manager = response.data;
					axios.get('rest/buyers/getBuyersWhoVisitedCertainSportFacility/' + this.manager.sportsFacilityId)
			              .then(response => {
							this.buyers = response.data;
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