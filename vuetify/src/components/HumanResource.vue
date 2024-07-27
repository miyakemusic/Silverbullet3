<template>
	<v-btn text="Init" @click="onInit"></v-btn>
	<v-btn text="Delete" @click="onDelete"></v-btn>
	<v-text-field
	  v-model="search"
	  label="Search"
	  prepend-inner-icon="mdi-magnify"
	  variant="outlined"
	  hide-details
	  single-line
	></v-text-field>
	
	<v-data-table
	  :items="items"
	  :items-per-page="20"
	  :search="search"
	  class="elevation-1 my-3 mx-auto"
	  @click:row="clickRow"
	>
	
		<template v-slot:item.categories="{item}">
			<v-label v-for="cat in item.categories" :text="cat + '.'">			
				
			</v-label>
		</template>
		
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
	</v-data-table></template>

<script setup lang="ts">
import axios from 'axios'
import { ref, watch, onMounted } from 'vue'
import convertUrl from './MyUrl.ts'

const items = ref([])
const search = ref()

onMounted(() => {
	retrieve();	
});

function retrieve() {
	axios.get(convertUrl("/api/user/v1/users"))
	.then(function (response) {
		items.value = response.data;
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});		
}

function onInit() {
	axios.get(convertUrl("/api/user/v1/init"))
	.then(function (response) {
		items.value = response.data;
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});	
}

function onDelete() {
	axios.delete(convertUrl("/api/user/v1/debug/delete"))
	.then(function (response) {
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