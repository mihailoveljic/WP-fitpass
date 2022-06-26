Vue.component("coaches-list", {
	data: function () {
		    return {
				coaches : []
		    }
	},
	template: 
` 
	<div>
		<p class="text-center" v-for="coach in coaches" :key="coach.id">{{coach.name}} {{coach.surname}}</p>
	</div>
`
	, 
	methods : {
	
	},
	computed: {
		
	},
	mounted () {
		axios.get('rest/coaches')
              .then(response => {
					this.coaches = response.data;
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
    }
});