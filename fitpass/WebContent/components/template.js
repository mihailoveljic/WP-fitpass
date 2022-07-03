Vue.component("TEMPLATE", {
	name: "TEMPLATE",
	data: function () {
		    return {
			
				
			}
	},
	props:
		['userToken']
	,
	template: 
` 
	<v-row>
		<v-col cols="3">
		
		</v-col>
		
		<v-col cols="6">
		
		</v-col>
		
		<v-col cols="3">
		
		</v-col>
	</v-row>
`
	,
	methods : {
		
	},
	mounted () {
              
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