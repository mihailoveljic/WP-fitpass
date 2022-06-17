const header = { template: '<header></header>' }
const footer = { template: '<footer></footer>' }

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', component: header},
	    { path: '/footer', component: footer}
	  ]
});

var app = new Vue({
	router,
	el: '#app'
});