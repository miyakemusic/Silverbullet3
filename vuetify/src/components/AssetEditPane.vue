<template>
	<v-select
	  :items="attributes"
	  label="Category*"
	  multiple
	  persistent-hint
	  v-model="props.item.categories"
	></v-select>
	
	<v-select
	  :items="	manufacturers"
	  label="Manufacturer*"
	  v-model="props.item.manufacturer"
	  required
	></v-select>
	
	<v-text-field
	  label="Product Family"
	  v-model="props.item.family"
	></v-text-field>
	
	<v-text-field
	  label="Model Name*"
	  v-model="props.item.model"
	  required
	></v-text-field>
	
	<v-text-field
	  label="Note"
	  v-model="props.item.note"
	></v-text-field>
	
	<v-text-field
	  label="Image"
	  v-model="props.item.image"
	></v-text-field>

</template>

<script setup lang="ts">
import axios from 'axios'
import { ref, watch, onMounted } from 'vue'
import convertUrl from './MyUrl.ts'

const props = defineProps(['item'])

const attributes = ref()
const manufacturers = ref()

onMounted(() => {
	retrieve();	
});

function retrieve() {
	axios.get(convertUrl('/api/asset/v1/attributes'))
	.then(function (response) {
		attributes.value = response.data
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});		
	
	axios.get(convertUrl('/api/asset/v1/manufacturers'))
	.then(function (response) {
		manufacturers.value = response.data
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});	
}
</script>