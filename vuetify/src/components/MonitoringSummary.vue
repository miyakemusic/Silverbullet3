<template>
	  <v-card width="800" variant="outlined" class="ma-2">
	    <v-card-title>
	      Network Monitoring Status
	    </v-card-title>
	
	    <v-divider></v-divider>
	    <v-data-table :headers="headers" 
				v-model:search="search" :items="items" hide-default-footer>
			<template v-slot:item.schedule="{ item }">
			  <div class="text-end">
			    <v-label
			      :text="'Start: ' + item.schedule.start"
			    ></v-label>
				<br>
				<v-label
				  :text="'End: ' + item.schedule.deadline"
				></v-label>
			  </div>
			</template>
					

	    </v-data-table>
	  </v-card>
</template>

<script setup>
  import { ref } from 'vue'
  import axios from 'axios'
  import convertUrl from './MyUrl.ts'
  
  const color = ref({
    no: 'green',
    warning: 'yellow',
	error: 'red'
  })
  const headers = [
    { title: 'Name', value: 'name' },
    { title: 'Description', value: 'description' },
  { title: 'Location', value: 'location' },
    { title: 'Schedule', value: 'schedule' },
  { title: 'Alarm', value: 'alarm' }
  ]
  
  const search = ref('')
  const items = ref([
    {
      name: 'FTTH#001',
	  description: 'FTTH Construction',
	  location: 'Tokyo Kita Jujo Area',
      schedule: {start: '2024/4/1', complete: '2024/5/31'},
	  alarm: {type: 'no', message: 'Normal'},
    },
	{
	  name: 'FTTA#001',
	  description: '5G Tower Construction',
	  location: 'Kanagawa Atsugi Area',
	  schedule: {start: '2023/12/1', complete: '2024/6/31'},
	  alarm: {type: 'warning', message: 'Latency'},
	},
	{
	  name: 'METRO#001',
	  description: 'Metro Construction',
	  location: 'Tokyo-Yokohama-Saitama Area',
	  schedule: {start: '2023/12/1', complete: '2024/6/31'},
	  alarm: {type: 'error', message: 'Fiber disconnect'},
	},
  ])
  
  axios.get(convertUrl('/api/project/v1/projectSummary/Monitoring'))
  .then(function (response) {
  	items.value = response.data;
  })
  .catch(function (error) {
    console.log(error);
  })
  .finally(function () {
  });	
</script>

<script>

</script>