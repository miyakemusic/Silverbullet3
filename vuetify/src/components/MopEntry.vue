<template>
	<v-app>
	<v-system-bar>
	</v-system-bar>
	
	  <v-main>
		<MopContent :mop="mop"></MopContent>
	  </v-main>
	
	<v-footer color="primary" app>
		<AppFooter />
	</v-footer>
	  
	</v-app>
</template>
<script setup lang="ts">
import {onMounted, ref} from 'vue'
import axios from 'axios'
import convertUrl from './MyUrl.ts'
import { useRoute } from "vue-router"

const mop = ref()

const route = useRoute();
const id : string = String(route.params.id)

onMounted(() => {
	retrieve(id)
})

function retrieve(id: string) {
	var url = convertUrl('/api/mop/v1/mops/' + id)
	axios.get(url)
	.then(function (response) {
		mop.value = response.data
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});			
}
</script>