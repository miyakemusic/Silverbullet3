<template>
	{{ nodeName }}
	<v-data-table 
		:items="testItems" density="compact">
	</v-data-table>
	
	<MopContent v-if="mopid != null" :mopid="mopid"></MopContent>
</template>
<script setup lang="ts">
import { ref, watch, onMounted } from 'vue'
import axios from 'axios'
import convertUrl from './MyUrl.ts'

const props = defineProps(['nodeid'])
const testItems = ref([])
const nodeName = ref()
const mopid = ref()

watch(() => props.nodeid, () => {
	retrieve();
});

onMounted(() => {
	retrieve()
});

function retrieve() {
	axios.get(convertUrl('/api/project/v1/nodeDetail/' + props.nodeid))
	.then(function (response) {
		nodeName.value = response.data.name
		testItems.value = response.data.testItems
		mopid.value = response.data.mopid
	})
	.catch(function (error) {
		console.log(error);
	})
	.finally(function () {
	});	
}
</script>