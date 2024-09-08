<template>
	<v-navigation-drawer permanent>
		<MopNavigation :mop="mop" @on-list="onList" @on-edit="onEdit" @on-view="onView"></MopNavigation>
	</v-navigation-drawer>
	
	<MopList v-if="listEnabled==true" @on-select="onSelectMop"></MopList>
	<MopContent v-if="contentEnabled==true" :mop="mop"></MopContent>
	<MopEditor v-if="editorEnabled==true" :mop="mop" :mopid="mopid"></MopEditor>
</template>

<script setup lang="ts">
import {onMounted, ref} from 'vue'
import axios from 'axios'
import convertUrl from './MyUrl.ts'
const props = defineProps(['mopid'])

const mop = ref()
const mopid = ref()
const listEnabled = ref(true)
const contentEnabled = ref(false)
const editorEnabled= ref(false)

function onList() {
	listEnabled.value = true;
	contentEnabled.value = false
	editorEnabled.value = false
}

function onView() {
	listEnabled.value = 	false;
	contentEnabled.value = true
	editorEnabled.value = false
}

function onSelectMop(id) {
	mopid.value = id
	listEnabled.value = false;
	contentEnabled.value = true
	editorEnabled.value = false
	
	retrieve(id)
}

function onEdit() {
	listEnabled.value = false;
	contentEnabled.value = false
	editorEnabled.value = true
}

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