<template>
	  <v-card width="800" variant="outlined" class="ma-2">
	    <v-card-title>
	      Construction Status
	    </v-card-title>
	
	    <v-divider></v-divider>
	    <v-data-table 
			:headers="headers" 
			v-model:search="search" :items="items" hide-default-footer @click:row="handleClick">
<!--			<template v-slot:item.name="{ item }">
				<v-btn 	@click="$emit('onProjectSelect', [`${item.id}`])"
				  :text="item.name"
				></v-btn>
			</template>-->
						
			<template v-slot:item.schedule="{ item }">
			  <div class="text-end">
			    <v-chip label size="small" variant="text"
			      :text="'Start: ' + item.schedule.start"
			    ></v-chip>
				<br>
				<v-chip label size="small"	variant="text"
				  :text="'End: ' + item.schedule.deadline"
				></v-chip>
			  </div>
			</template>
					
			<template v-slot:item.progress="{ item }">
			  <div class="text-end">
			    <v-chip
			      :color="item.progress.type!='delay' ? 'green' : 'red'"
			      :text="item.progress.type + ': ' + item.progress.days + ' DAYS'"
			      class="text-uppercase"
				  size="small"
				  chip-max-width
			      label
			    ></v-chip>
				<br>
				<v-chip
				  :color="item.cost.budget > item.cost.expenditure ? 'green' : 'red'"
				  :text="'Remaining: ' + item.cost.unit + (item.cost.budget - item.cost.expenditure)"
				  class="text-uppercase"
				  size="small"
				  chip-max-width
				  label
				></v-chip>
				<br>
			  </div>
			</template>
			
			<template v-slot:item.cost="{ item }">
			  <div class="text-end">
				<v-chip label size="small" variant="text"
				  :text="'Used: ' + item.cost.unit + item.cost.expenditure"
				></v-chip>
				<br>
				<v-chip label size="small" variant="text"
				  :text="'Budget: ' + item.cost.unit + item.cost.budget"
				></v-chip>
			  </div>
			</template>
			
			<template v-slot:item.alarm="{ item }">
			  <div class="text-end">
			    <v-chip
			      :color=color[item.alarm.type]
			      :text="item.alarm.message"
			      class="text-uppercase"
				  size="small"
				  chip-max-width
			      label
			    ></v-chip>
				<br>
			  </div>
			</template>
	    </v-data-table>
	  </v-card>
</template>

<script setup>
  import { ref } from 'vue'
  import axios from 'axios'
  
  const color = ref({
    no: 'green',
    warning: 'yellow',
  error: 'red'
  })
  const props = defineProps(['headers', 'url'])
  const emit = defineEmits(['onProjectSelect'])
  const search = ref('')
  const items = ref()
  
  const headers =   props.headers;
/*  
  const headers = [
    { title: 'Name', value: 'name' },
    { title: 'Description', value: 'description' },
	{ title: 'Location', value: 'location' },
    { title: 'Schedule', value: 'schedule' },
    { title: 'Progress', value: 'progress' },
	{ title: 'Cost', value: 'cost' }
  ]
*/
  function handleClick(event, row) {
	const id = row.item.id;
	emit('onProjectSelect', id);
  }
  
  axios.get(props.url)
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