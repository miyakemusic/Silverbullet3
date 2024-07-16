<template>
	<v-data-table 
		:items="testItems" density="compact">
	</v-data-table>
</template>
<script setup lang="ts">
import { ref, watch, onMounted } from 'vue'
import axios from 'axios'

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
	axios.get('/api/project/v1/testItemSummary?nodeid=' + props.nodeid)
	.then(function (response) {
		testItems.value = response.data
	})
	.catch(function (error) {
		console.log(error);
	})
	.finally(function () {
	});	
}
</script>