<template>
	Test Progress (Item)
  <apexchart v-bind="{ options, series }" />
</template>

<script setup lang="ts">
import { ref, watch, onMounted } from 'vue'
import axios from 'axios'
import convertUrl from './MyUrl.ts'
const options = ref({
  chart: {
	type: 'bar',
	stacked: true
  },
  plotOptions: {
	bar: {
		horizontal: true,
	}
  },
  xaxis: {
    categories: [1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998]
  },
})

const series = ref([
{
  name: 'Pass',
  data: [30, 40, 45, 50, 49, 60, 70, 91]
},
{
  name: 'Fail',
  data: [30, 40, 45, 50, 49, 60, 70, 91]
},
{
  name: 'Rest',
  data: [30, 40, 45, 50, 49, 60, 70, 91]
}
])

const props = defineProps(['nodeid'])

watch(() => props.nodeid, () => {
	retrieve();
});

onMounted(() => {
	retrieve()
});

function retrieve() {
	axios.get(convertUrl('/api/project/v1/testItemSummary?nodeid=' + props.nodeid))
	.then(function (response) {
		const xaxis = []
		const pass = []
		const fail = []
		const rest = []
		response.data.forEach((d) => {
			xaxis.push(d.node)
			pass.push(d.pass)
			fail.push(d.fail)
			rest.push(d.rest)
		})
		options.value = {

		  chart: {
			type: 'bar',
			stacked: true
		  },
		  plotOptions: {
			bar: {
				horizontal: true,
			}
		  },
		  colors: ['#00FF00', '#FF0000', '#C4BBAF'],
		  xaxis: {
		    categories: xaxis
		  },
		}
		series.value = [
			{
				name: "Pass",
				data: pass,
			},
			{
				name: "Fail",
				data: fail,
			},
			{
				name: "Rest",
				data: rest,
			},		
		]
	})
	.catch(function (error) {
		console.log(error);
	})
	.finally(function () {
	});	
}
</script>

