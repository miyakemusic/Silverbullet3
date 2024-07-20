<template>
	Test Progress (Items)
	<apexchart :options="options" :series="series" />
</template>
<script setup lang="ts">
	import axios from 'axios'
	import { ref ,watch, onMounted} from 'vue'
	import convertUrl from './MyUrl.ts'

		const props = defineProps(['nodeid', "projectid"])
	const options = ref({		
			chart: {
				type: 'pie',
			},
					
			labels: ['Pass', 'Fail', 'Rest'],
			colors: ['#00FF00', '#FF0000', '#C4BBAF'],
			dataLabels: {
			  formatter: (val, { seriesIndex, w }) => w.config.series[seriesIndex] // <--- HERE
			}
		})
	const series = ref(
		[1,1,1]
	)
	onMounted(() => {
		retrieve()
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
		axios.get(convertUrl(url))
		.then(function (response) {
			series.value = [response.data.pass, response.data.fail, response.data.rest]
			
		})
		.catch(function (error) {
		  console.log(error);
		})
		.finally(function () {
		});			
	}
	
</script>
