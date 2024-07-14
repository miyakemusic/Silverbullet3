<template>
	<h1>Dashboard</h1>
	<v-row>
		<v-col>
			<ProjectSummary @onProjectSelect="onProjectSelect" :headers="construction_headers" url="/api/project/v1/projectSummary/Construction" />
		</v-col>
		<v-col>
			<ProjectSummary @onProjectSelect="onProjectSelect" :headers="monitoring_headers" url="/api/project/v1/projectSummary/Monitoring"/>
		</v-col>
		
		<v-col>
			<AssetSummary />
		</v-col>
	</v-row>
</template>
<script setup>
	import { ref } from 'vue'

	const emit = defineEmits(['onProjectSelect'])

	const construction_headers = [
	  { title: 'Name', value: 'name' },
	  { title: 'Description', value: 'description' },
	  { title: 'Location', value: 'location' },
	  { title: 'Schedule', value: 'schedule' },
	  { title: 'Progress', value: 'progress' },
	  { title: 'Cost', value: 'cost' }
	]
	
	const monitoring_headers = [
	  { title: 'Name', value: 'name' },
	  { title: 'Description', value: 'description' },
	{ title: 'Location', value: 'location' },
	  { title: 'Schedule', value: 'schedule' },
	{ title: 'Alarm', value: 'alarm' }
	]
	function onProjectSelect(id) {
		emit('onProjectSelect', id);
	}
	
</script>
<script>
</script>