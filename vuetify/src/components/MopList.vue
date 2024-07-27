<template>
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
	  :items-per-page="50"
	  :search="search"
	  class="elevation-1 my-3 mx-auto"
	  @click:row="clickRow"
	></v-data-table>
</template>

<script setup lang="ts">
import {onMounted, ref, watch} from 'vue'
import axios from 'axios'
import convertUrl from './MyUrl.ts'
const props = defineProps([''])
const items = ref([])
const search = ref('')
const emit = defineEmits(['onSelect']) 

onMounted(() => {
	retrieve();	
});


function retrieve() {
	axios.get(convertUrl("/api/mop/v1/summary"))
	.then(function (response) {
		items.value = response.data;
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	})
}		


function clickRow(event, row) {
	emit('onSelect', row.item.id)
}
</script>