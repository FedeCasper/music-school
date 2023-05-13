Vue.createApp({
	data() {
	return {

	}
	},

	created() {
        const params = new Proxy(new URLSearchParams(window.location.search),{
            get: (searchParams, prop) => searchParams.get(prop),
        });
        let token = params.token;
        axios.patch("music-school-production.up.railway.app/api/activateClient/" + token)
	},
	

	methods: {

	},
	computed: {
		
	}
	},
).mount("#app")