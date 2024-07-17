<template>
	<v-data-table
	  :items="items"
	  :items-per-page="5"
	  class="elevation-1 my-3 mx-auto"
	  style="width:1000px;"
	>
		<template v-slot:item.url="{ item }" >
		      <v-img :src="item.url" 
		             :aspect-ratop="16/9" 
		             height="9vw" 
		             min-height="100px"
		             width="16vw" 
		             min-width="160px" 
		             class="ma-0 pa-0"
		     ></v-img>
		 </template>
	</v-data-table>
</template>
<script setup lang="ts">
import axios from 'axios'
import { ref, watch } from 'vue'
import { onMounted } from "vue";
import { reactive, computed } from 'vue'
const props = defineProps(['projectid', 'nodeid'])

const items = ref();

onMounted(() => {
	retrieve();	
});
watch(() => props.nodeid, () => {
	retrieve();
});

function retrieve() {
	axios.get('/api/asset/v1/testers')
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