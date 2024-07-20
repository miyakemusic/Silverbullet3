<template>
	<v-data-table
	  :items="items"
	  :items-per-page="20"
	  class="elevation-1 my-3 mx-auto"

	>
		<template v-slot:item.image="{ item }" >
		      <v-img :src="item.image" 
		             :aspect-ratop="16/9" 
		             height="3vw" 
		             min-height="50px"
		             width="5vw" 
		             min-width="50px" 
		             class="ma-0 pa-0"
		     ></v-img>
		 </template>
	</v-data-table>
</template>
<script setup lang="ts">
import axios from 'axios'
import { ref, watch, onMounted } from 'vue'
import convertUrl from './MyUrl.ts'

const props = defineProps(['projectid', 'nodeid'])

const items = ref();

onMounted(() => {
	retrieve();	
});
watch(() => props.nodeid, () => {
	retrieve();
});

function retrieve() {
	axios.get(convertUrl('/api/asset/v1/testers'))
	.then(function (response) {
		items.value = response.data;
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});		
}
</script>