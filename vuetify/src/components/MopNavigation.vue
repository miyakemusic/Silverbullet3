<template>
<!--
	<v-container>
	<v-row>
		<v-col>
			<v-btn
				text="New"
				prepend-icon="mdi-open-in-new"
			></v-btn>
		</v-col>
	</v-row>
	<v-row>
		<v-btn
			text="MOP List"
			prepend-icon="mdi-open-in-app"
		></v-btn>
	</v-row>
	<v-row>
		<v-btn
			text="Export JSON"
			prepend-icon="mdi-cloud-download"
		></v-btn>
	</v-row>
	<v-row>
		<v-btn
			text="Import JSON"
			prepend-icon="mdi-cloud-upload"
		></v-btn>
	</v-row>
	</v-container>
-->


	<v-list nav>
	  <v-list-item prepend-icon="mdi-open-in-new" title="Init" value="init" @click="onInit"></v-list-item>
	  <v-list-item prepend-icon="mdi-open-in-new" title="Delete" value="delete" @click="onDelete"></v-list-item>
	  <v-list-item prepend-icon="mdi-open-in-new" title="New" value="new"></v-list-item>
	  <v-list-item prepend-icon="mdi-cloud-upload" title="Import JSON" value="import"></v-list-item>
	</v-list>
		
	<v-list nav>
	  <v-list-item prepend-icon="mdi-open-in-app" title="MOP List" value="list" @click="onList"></v-list-item>
	  <v-list-item prepend-icon="mdi-table-edit" title="Edit" value="edit" @click="emit('onEdit')"></v-list-item>
	  <v-list-item prepend-icon="mdi-text-box-outline" title="View" value="view" @click="emit('onView')"></v-list-item>
	  
	</v-list>
	
	<v-card
	  class="mx-auto pa-2"
	  max-width="300"
	>

	<v-list>
		<v-list-subheader>Contents</v-list-subheader>
		<v-list-item v-if="props.mop != null" v-for="section in props.mop.sections" 

			:value="section.title"
			:title="section.title">

		</v-list-item>
	</v-list>	
	
	</v-card>
</template>
<script setup lang="ts">
import {onMounted, ref, watch} from 'vue'
import axios from 'axios'
import convertUrl from './MyUrl.ts'

const mop = ref({
				id: '',
				title: '',
				sections: []
			})
const mops = ref([''])
const select = ref()
const map = new Map<string, string>()
const emit = defineEmits(['onList', "onEdit", "onView"]) 
const props = defineProps(['mop'])

function onList() {
	emit('onList')
}
onMounted(() => {
//	retrieveTitle()
});

watch(select, (newSelect) => {
//	retrieve(map.get(newSelect))
})
function onDuplicate() {
	axios.post(convertUrl("/api/mop/v1/mop"), props.mop)
		.then(res=>{

		}).catch(err=>{
			console.log(err)
		})
}

function onInit() {
	axios.get(convertUrl("/api/mop/v1/debug/init"))
		.then(res=>{
	
		}).catch(err=>{
			console.log(err)
		})	
}
function onDelete() {
	axios.delete(convertUrl("/api/mop/v1/debug/delete"))
		.then(res=>{
	
		}).catch(err=>{
			console.log(err)
		})	
}
</script>