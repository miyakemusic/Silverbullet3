<template>
	<v-card width="600"	variant="outlined" class="ma-2">
	  <v-card-title>
	    Progress
	  </v-card-title>
	  <v-container >
		<Line :data="data" :options="options" />
	</v-container>
	</v-card>
</template>

<script lang="ts">

</script>
<script setup lang="ts">
	import {
	  Chart as ChartJS,
	  CategoryScale,
	  LinearScale,
	  PointElement,
	  LineElement,
	  Title,
	  Tooltip,
	  Legend
	} from 'chart.js'
	import { Line } from 'vue-chartjs'
	
	ChartJS.register(
	  CategoryScale,
	  LinearScale,
	  PointElement,
	  LineElement,
	  Title,
	  Tooltip,
	  Legend
	)
	const data = {
	  labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
	  datasets: [
	    {
	      label: 'Plan',
	      backgroundColor: 'red',
		  borderColor: 'red',
	      data: [40, 39, 10, 40, 39, 80, 40]
	    },
		{
		  label: 'Real',
		  backgroundColor: 'blue',
		  borderColor: 'blue',
		  data: [30, 29, 20, 50, 19, 10, 20]
		}
	  ]
	}
	
	const options = {
	  responsive: true,
	  maintainAspectRatio: false,
	}
</script>