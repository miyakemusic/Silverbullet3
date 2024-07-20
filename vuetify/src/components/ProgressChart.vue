<template>
	Test Progress (Items)
	<Doughnut :data="data" :options="options" />
</template>
<script setup lang="ts">
	import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js'
	import { Doughnut } from 'vue-chartjs'
	import axios from 'axios'
	import { ref ,watch, onMounted} from 'vue'
	import chartjsPluginDatalabels from 'chartjs-plugin-datalabels'
	import convertUrl from './MyUrl.ts'
	ChartJS.register(ArcElement, Tooltip, Legend, chartjsPluginDatalabels)

	const props = defineProps(['nodeid', "projectid"])
			
	onMounted(() => {
		retrieve()
	});
	
	watch(() => props.nodeid, () => {
		retrieve();
	});
	
	function retrieve() {
		var url = convertUrl('/api/project/v1/progress?')
		if (props.nodeid == undefined) {
			url += 'projectid=' + props.projectid
		}
		else {
			url += 'nodeid=' + props.nodeid
		}
		axios.get(url)
		.then(function (response) {
			data.value = {
				labels: ['Pass', 'Fail', 'Rest'],
				datasets : [{ 
					backgroundColor: ['green', 'red', 'lightblue'],
					data: [response.data.pass, response.data.fail, response.data.rest]
				}]}
		})
		.catch(function (error) {
		  console.log(error);
		})
		.finally(function () {
		});			
	}
	
	const data = ref({
	  labels: ['Pass', 'Fail', 'Rest'],
	  datasets: [
	    {
	      backgroundColor: ['green', 'red', 'lightblue'],
	      data: [10, 10, 10]
	    }
	  ]
	})
	
	const options = {
	  responsive: false,
	  maintainAspectRatio: false
	}
</script>
