<template>
Test Progress (Time)
<v-table density="compact">
	<thead>
		<tr>
			<td>Start</td><td>Deadline</td><td>Estimated Completion</td><td>Progress</td><td>Delay days</td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>{{date.startDate}}</td>
			<td>{{date.deadline }}</td>
			<td><v-chip color="red"	label>{{date.estimatedCompletionDate  }}</v-chip></td>
			<td><v-chip color="red"	label>{{date.type  }}</v-chip></td>
			<td><v-chip color="red"	label>{{date.days  }}</v-chip></td>
		</tr>
	</tbody>
</v-table>
</template>

<script setup lang="ts">
import {ref, onMounted, watch} from 'vue'

import axios from 'axios'
import convertUrl from './MyUrl.ts'
const props = defineProps(['nodeid', 'projectid'])

const date = ref({
	startDate: '2024/1/3',
	deadline: '2024/9/1',
	estimatedCompletionDate: '2024/10/5',
	
	type: 'delay',
	days: -4,

	})
	
	onMounted(() => {
		retrieve();	
	});
	watch(() => props.nodeid, () => {
		retrieve();
	});
	
	function retrieve() {
		var url = '/api/project/v1/progress/date?'
		if (props.nodeid == undefined) {
			url += 'projectid=' + props.projectid
		}
		else {
			url += 'nodeid=' + props.nodeid
		}
		axios.get(convertUrl(url))
		.then(function (response) {
			date.value = response.data
		})
		.catch(function (error) {
		  console.log(error);
		})
		.finally(function () {
		});	
	}
</script>