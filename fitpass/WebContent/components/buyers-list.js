Vue.component("buyers-list", {
	data: function () {
		    return {
				buyers : []
		    }
	},
	template: 
` 
	<div>
		<p class="text-center" v-for="buyer in buyers" :key="buyer.id">{{buyer.name}} {{buyer.surname}}</p>
	</div>
`
	, 
	methods : {
	
	},
	computed: {
		
	},
	mounted () {
		axios.get('rest/buyers')
              .then(response => {
					this.buyers = response.data;
				})
              .catch(error => {
                    alert(error.message + " GRESKA");
                    });
    }
});