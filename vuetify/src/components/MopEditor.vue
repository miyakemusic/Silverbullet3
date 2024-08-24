<template>
	<v-btn text="Overwrite" @click="onOverwrite"></v-btn>
	<v-btn text="Save New" @click="onDuplicate"></v-btn>
	<v-btn text="Delete" @click="onDelete"></v-btn>
	<json-editor
	  mode="tree"
	  v-model:json="jsonData" 
	/>
<!--
	<v-textarea :model-value="JSON.stringify(props.mop)">
	</v-textarea>
-->
	
	
</template>

<script setup lang="ts">
import {onMounted, ref, watch} from 'vue'
import axios from 'axios'
import convertUrl from './MyUrl.ts'

const props = defineProps(['mop', 'mopid'])
const jsonData = ref(props.mop)
//const id = props.mopid

onMounted(() => {
//	delete props.mop.id
//	jsonData.value = props.mop
});

function onOverwrite() {
	post(props.mopid)
}

function onDuplicate() {
	post('-1')
}

function onDelete() {
	var url = convertUrl('/api/mop/v1/mop/' + props.mopid)
	
	axios.delete(url)
		.then(res=>{
			console.log(res)
	
		}).catch(err=>{
			alert(err.response.data)
		})	
}

function post(path: string) {
	var url = convertUrl('/api/mop/v1/mop/' + path)
	
	axios.post(url, jsonData.value)
		.then(res=>{
			console.log(res)
	
		}).catch(err=>{
			console.log(err)
		})
}
</script>