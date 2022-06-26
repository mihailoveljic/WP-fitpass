Vue.component("managers-list", {
	data: function () {
		    return {
				managers : []
		    }
	},
	template: 
` 
	<div>
		<p class="text-center" v-for="manager in managers" :key="manager.id">{{manager.name}} {{manager.surname}}</p>
	</div>
`
	, 
	methods : {
	
	},
	computed: {
		
	},
	mounted () {
		axios.get('rest/managers')
              .then(response => {
					this.managers = response.data;
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
    }
});