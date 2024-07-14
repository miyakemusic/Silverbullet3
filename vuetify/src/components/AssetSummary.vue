<template>
	<v-card width="600"	variant="outlined" class="ma-2">
	  <v-card-title>
	    Asset Usage Status
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
	      label: 'Data One',
	      backgroundColor: 'lightgray',
	      data: [40, 39, 10, 40, 39, 80, 40]
	    }
	  ]
	}
	
	const options = {
	  responsive: true,
	  maintainAspectRatio: false,
	}
</script>