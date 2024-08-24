<template>
	<v-navigation-drawer permanent>
		<ProjectTree :projectid="projectid" @on-node-select="onNodeSelect" />
	</v-navigation-drawer>
	<ProjectTable v-if="selectedNode==undefined" :projectid="projectid"/>
	<NodeSummary v-else :nodeid="selectedNode"></NodeSummary>
</template>
<script setup>
	import { ref } from 'vue'

	const props = defineProps(['projectid'])

	const projectid = ref(props.projectid)
	const selectedNode = ref();
	
	function onNodeSelect(id) {
		selectedNode.value = id
	}
</script>
