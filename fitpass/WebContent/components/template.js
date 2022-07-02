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
	<div>
	
	</div>
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