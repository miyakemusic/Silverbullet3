<template>
	{{ nodeName }}
	<v-data-table 
		:items="testItems" density="compact">
	</v-data-table>
</template>
<script setup lang="ts">
import { ref, watch, onMounted } from 'vue'
import axios from 'axios'
import convertUrl from './MyUrl.ts'

const props = defineProps(['nodeid'])
const testItems = ref([])
const nodeName = ref()

watch(() => props.nodeid, () => {
	retrieve();
});

onMounted(() => {
	retrieve()
});

function retrieve() {
	axios.get(convertUrl('/api/project/v1/nodeSummary/' + props.nodeid))
	.then(function (response) {
		nodeName.value = response.data.name
		testItems.value = response.data.testItems
	})
	.catch(function (error) {
		console.log(error);
	})
	.finally(function () {
	});	
}
</script>