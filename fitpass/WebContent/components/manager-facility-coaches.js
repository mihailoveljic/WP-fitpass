Vue.component("manager-facility-coaches", {
	name:"manager-facility-coaches",
	data:function(){
		return {
			coaches : [],
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
					          v-for="coach in coaches"
					          :key="coach.username">
					          <td>{{ coach.name }}</td>
					          <td>{{ coach.surname }}</td>
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
					axios.get('rest/TrainingController/getAllCoachesInCertainSportFacility/' + this.manager.sportsFacilityId)
			              .then(response => {
							this.coaches = response.data;
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