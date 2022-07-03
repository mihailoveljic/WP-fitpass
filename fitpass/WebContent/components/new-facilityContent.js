Vue.component("new-facilityContent", {
	name: "new-facilityContent",
	data: function () {
		    return {
				manager : null,
				sportFacility : null,
				facilityContents : [],
				isUserAddingNewContent : false,
				nameForNewContent : ""
			}
	},
	props:
		['userToken']
	,
	template: 
` 
<div>
	<template>
		<v-row>
			<v-col cols="3" class="text-center"></v-col> 
			<v-col cols="5">
				<v-simple-table >
				    <template v-slot:default>
				      	<thead>
					        <tr>
					         	<th class="text-left text-h6 flex-row-reverse">
						        	Name
						        </th>
					        </tr>
				      	</thead>
				      	<tbody>
					        <tr
					          v-for="content in facilityContents"
					          :key="content.id">
					          <td>{{ content }}</td>
					          <td><v-btn>delete</v-btn></td>
				        	</tr>
						</tbody>
			    	</template>
			  	</v-simple-table>
			  </v-col>
			<v-col cols="1"></v-col>
			<v-col cols="2">
			<v-btn @click="showFormForAddingNewContent" color="primary" class="ma-4" centered width="200" height="50">Add new content</v-btn>
			<v-btn @click="goBack" color="primary" class="ma-4" centered width="200" height="50">Cancel</v-btn>
			<v-card v-if="isUserAddingNewContent">
				<v-text-field class="ma-2" v-model="nameForNewContent" label="Name" required></v-text-field>
				<v-btn class="ma-2" @click="createNewFacilityContent" color="primary" centered width="100" height="50">Submit</v-btn>
				<v-btn class="ma-2" @click="hideFormForAddingNewContent" color="primary" centered width="100" height="50">Cancel</v-btn>
			</v-card>
			</v-col>
			<v-col cols="1"></v-col>
		</v-row>
	</template>
</div>
`
	,
	methods : {
		goBack(){
			this.$router.push('/manager-facility');
		},
		showFormForAddingNewContent(){
			this.isUserAddingNewContent = true;
		},
		hideFormForAddingNewContent(){
			this.isUserAddingNewContent = false;
		},
		createNewFacilityContent(){
			
			let facilityContent = {
				id: -1,
				name : this.nameForNewContent,
				isDeleted: false
			}
			
			axios.post('rest/FacilityContentController/', facilityContent)
              .then(response => {
					facilityContent = response.data;
					axios.put('rest/SportsFacilityController/' + this.manager.sportsFacilityId, facilityContent)
			              .then(response => {
								axios.get('rest/managers/' + this.userToken.id)
					              .then(response => {
										this.manager = response.data;
										axios.get('rest/SportsFacilityController/' + this.manager.sportsFacilityId)
								              .then(response => {
												this.sportFacility = response.data;
												this.facilityContents = response.data.facilityContents;
												})
								              .catch(error => {
								                    alert(error.message + " GRESKA");
								              });
									})
					              .catch(error => {
					                    alert(error.message + " GRESKA");
					              });
							})
			              .catch(error => {
			                    alert(error.message + " GRESKA");
			              });
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
              });
		}
	},
	mounted () {
           axios.get('rest/managers/' + this.userToken.id)
              .then(response => {
					this.manager = response.data;
					axios.get('rest/SportsFacilityController/' + this.manager.sportsFacilityId)
			              .then(response => {
							this.sportFacility = response.data;
							this.facilityContents = response.data.facilityContents;
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