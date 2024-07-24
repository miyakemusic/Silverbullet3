<template>
	<v-select :items="mops" v-model="select"></v-select>
	
	<v-card
	  class="mx-auto pa-2"
	  max-width="300"
	>
	<v-list>
		<v-list-subheader>Contents</v-list-subheader>
		<v-list-item v-for="section in mop.sections" 

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

const mop = ref({title: 'title',
				sections: []
			})
const mops = ref([''])
const select = ref()
const map = new Map<string, string>()

function changed(item) {

}
onMounted(() => {
	retrieveTitle()
});

watch(select, (newSelect) => {
	retrieve(map.get(newSelect))
})

function retrieveTitle() {
	var url = convertUrl('/api/project/v1/mops/titles')
	axios.get(url)
	.then(function (response) {
		const arr = []
		response.data.forEach((mop) => {
			map.set(mop.value, mop.key)
			arr.push(mop.value)
		})
		
		mops.value = arr
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});			
}

function retrieve(id: string) {
	var url = convertUrl('/api/project/v1/mops/' + id)
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