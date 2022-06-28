Vue.component("users-list", {
	name: "users-list",
	data: function () {
		    return {
				users : [],
				buyers : [],
				coaches : [],
				managers : []
			}
	},
	props:
		['mode']
	,
	template: 
` 
<div v-if="mode=='ADMINISTRATOR'">
	<template>
		<v-row>
			<v-col cols="3" class="text-center">
		 	</v-col> 
			<v-col cols="6">
				<v-simple-table >
				    <template v-slot:default>
				      	<thead>
					        <tr>
					          <th class="text-left">
					            Name
					          </th>
					          <th class="text-left">
					            Surname
					          </th>
					        </tr>
				      	</thead>
				      	<tbody>
					        <tr
					          v-for="user in users"
					          :key="user.username">
					          <td>{{ user.name }}</td>
					          <td>{{ user.surname }}</td>
					          <td><v-btn @click="deleteUser(user)">delete</v-btn></td>
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
`
, 
	methods : {
		
		deleteUser(user){
			if(user.role == "KUPAC") {
				axios.delete('rest/buyers/' + user.id)
	              .then(response => {
						if(response.data == false){
							alert("Failed to delete buyer!");
							return false;
						} 
	              })
	              .catch(error => {
	                    alert(error.message + " GRESKA");
	                    });
	                    this.reloadPage();
	                    return true;
			} else if(user.role == "TRENER"){
				axios.delete('rest/coaches/' + user.id)
	              .then(response => {
						if(response.data == false){
							alert("Failed to delete coach!");
							return false;
						} 
	              })
	              .catch(error => {
	                    alert(error.message + " GRESKA");
	                    });
	                    this.reloadPage();
	                    return true;
			} else {
				axios.delete('rest/managers/' + user.id)
	              .then(response => {
						if(response.data == false){
							alert("Failed to delete manager!");
							return false;
						} 
	              })
	              .catch(error => {
	                    alert(error.message + " GRESKA");
	                    });
	                    this.reloadPage();
	                    return true;
			}
			
			
		},
		reloadPage(){
    		let promiseBuyers = axios.get('rest/buyers')
              .then(response => {
					this.buyers = response.data;
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
	        let promiseCoaches =  axios.get('rest/coaches')
	              .then(response => {
						this.coaches = response.data;
					})
	              .catch(error => {
	                    alert(error.message + " GRESKA");
	                    });
	     	let promiseManagers = axios.get('rest/managers')
	              .then(response => {
						this.managers = response.data;
					})
	              .catch(error => {
	                    alert(error.message + " GRESKA");
	                    });
	       Promise.all([promiseBuyers, promiseCoaches, promiseManagers]).then(() => {
			  this.users = this.managers.concat(this.coaches).concat(this.buyers);
			});
  		},
	},
	watch: {
	},
	computed: {
	},
	created() {
		let promiseBuyers = axios.get('rest/buyers')
              .then(response => {
					this.buyers = response.data;
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
        let promiseCoaches =  axios.get('rest/coaches')
              .then(response => {
					this.coaches = response.data;
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
     	let promiseManagers = axios.get('rest/managers')
              .then(response => {
					this.managers = response.data;
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
       Promise.all([promiseBuyers, promiseCoaches, promiseManagers]).then(() => {
		  this.users = this.managers.concat(this.coaches).concat(this.buyers);
		});
       
	},
	mounted () {
              
    }
});