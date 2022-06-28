Vue.component("users-list", {
	name: "users-list",
	data: function () {
		    return {
				users : [],
				usersBackup : [],
				buyers : [],
				coaches : [],
				managers : [],
				administrators : [],
				userNameSearched : "",
				userSurnameSearched : "",
				usernameSearched : "",
				buyerTypes : [],
				userRoles : ['KUPAC', 'TRENER', 'MENADZER', 'ADMINISTRATOR'],
				userRoleSearched : []
				
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
			<v-col cols="3" class="text-center"></v-col> 
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
				<v-card width="300" class="mx-auto pa-4 mt-8 text-center" outlined  rounded="8">
					<v-text-field @keyup.enter="filterUsers" v-model="userNameSearched" label="Name" outlined clearable></v-text-field>
					<v-text-field @keyup.enter="filterUsers" v-model="userSurnameSearched" label="Surname" outlined clearable></v-text-field>
					<v-text-field @keyup.enter="filterUsers" v-model="usernameSearched" label="Username" outlined clearable></v-text-field>
					<v-combobox @change="filterUsers" v-model="userRoleSearched" :items="userRoles" label="User role" clearable outlined small-chips></v-combobox>
				</v-card>
			</v-col>
		</v-row>
	</template>
</div>
`
, 
	methods : {
		filterUsers(){
				this.filterUsersByRole();
		},
		filterUsersByRole(){
			this.users=JSON.parse(JSON.stringify(this.usersBackup));
			if(this.userRoleSearched == "" || this.userRoleSearched == null) return;
			
			this.users = this.users.filter(user => {
				if(this.userRoleSearched == user.role) return true;
				return false;
			});
		},
		
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
	       let promiseAdministrators = axios.get('rest/administrators')
	              .then(response => {
						this.administrators = response.data;
					})
	              .catch(error => {
	                    alert(error.message + " GRESKA");
	                    });             
	       Promise.all([promiseBuyers, promiseCoaches, promiseManagers, promiseAdministrators]).then(() => {
			  this.users = this.managers.concat(this.coaches).concat(this.buyers).concat(this.administrators);
			  this.usersBackup=JSON.parse(JSON.stringify(this.users));
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
       let promiseAdministrators = axios.get('rest/administrators')
	              .then(response => {
						this.administrators = response.data;
					})
	              .catch(error => {
	                    alert(error.message + " GRESKA");
	                    });             
	       Promise.all([promiseBuyers, promiseCoaches, promiseManagers, promiseAdministrators]).then(() => {
			  this.users = this.managers.concat(this.coaches).concat(this.buyers).concat(this.administrators);
			  this.usersBackup=JSON.parse(JSON.stringify(this.users));
			});
       
	},
	mounted () {
              
    }
});