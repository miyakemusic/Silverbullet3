<template>
	Test Progress (Items)
	<v-table density="compact">
		<thead>
			<tr>
				<td>Total</td><td>Pass</td><td>Fail</td><td>Completed</td><td>Rest</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>{{items.total}}</td>
				<td>{{ items.pass }}</td>
				<td>{{ items.fail }}</td>
				<td>{{ 	completed }} ({{ percentage }}%)</td>
				<td>{{ rest  }}</td>
			</tr>
		</tbody>
	</v-table>
</template>
<script setup lang="ts">
	import axios from 'axios'
	import { ref, watch } from 'vue'
	import { onMounted } from "vue";
	import { reactive, computed } from 'vue'
	const props = defineProps(['projectid', 'nodeid'])

	const items = ref();
	
	const completed = computed(() => {
	  return items.value.total - (items.value.pass + items.value.fail)
	})
	
	const percentage = computed(() => {
	  return (items.value.pass + items.value.fail) * 100 / items.value.total
	})
	
	const rest = computed(() => {
	  return items.value.total - (items.value.pass + items.value.fail)
	})
	
	onMounted(() => {
		retrieve();	
	});
	watch(() => props.nodeid, () => {
		retrieve();
	});
	
	function retrieve() {
		var url = '/api/project/v1/progress?'
		if (props.nodeid == undefined) {
			url += 'projectid=' + props.projectid
		}
		else {
			url += 'nodeid=' + props.nodeid
		}
		axios.get(url)
		.then(function (response) {
			items.value = response.data
		})
		.catch(function (error) {
		  console.log(error);
		})
		.finally(function () {
		});	
	}
</script>