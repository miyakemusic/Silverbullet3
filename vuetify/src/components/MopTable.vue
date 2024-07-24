<template>

	<v-card 
		:title="items.title"
		:subtitle="items.description">
		
		<v-img
		  :lazy-src="items.image"
		  max-height="1000"
		  max-width="600"
		  :src="items.image"
		</v-img>
		
	</v-card>

	
	<v-card  :title="section.title" v-for="section in items.sections">
		<v-img
		  :lazy-src="section.image"
		  max-height="1000"
		  max-width="600"
		  :src="section.image"
		></v-img>
		<v-data-table
		  :items="section.lines"
		  :items-per-page="20"
		  :search="search"
		  class="elevation-1 my-3 mx-auto"
		  @click:row="clickRow"
		>
		
			<template v-slot:item.operation="{item}">
				<v-label :text="item.operation">			
					
				</v-label>
			</template>
			
			<template v-slot:item.image="{ item }" >
			      <v-img :src="item.image" 
			             :aspect-ratop="16/9" 
			             height="9vw" 
			             min-height="50px"
			             width="16vw" 
			             min-width="50px" 
			             class="ma-0 pa-0"
			     ></v-img>
			 </template>
		</v-data-table>
	</v-card>
</template>

<script setup="ts">
import {onMounted, ref} from 'vue'
import axios from 'axios'
import convertUrl from './MyUrl.ts'
const props = defineProps(['mopid'])
const items = ref({items:[]})

onMounted(() => {
	retrieve();	
});

function retrieve() {
	axios.get(convertUrl('/api/project/v1/mops/' + props.mopid))
	.then(function (response) {
		items.value = response.data
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});	
}

function clickRow(event, row) {
	
}
</script>