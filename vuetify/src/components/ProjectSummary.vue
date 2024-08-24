<template>
	  <v-card variant="outlined" class="ma-2">
		<template v-slot:title>
			<v-row>
				<v-col>
					{{ props.title }}
				</v-col>
				<v-col cols="4">
					<v-text-field label="Search" v-model="search"></v-text-field>
				</v-col>
				<v-col cols="2">
					<v-btn @click="onAddNew">Add New</v-btn>
				</v-col>
			</v-row>
		</template>
	    <v-data-table 
			:headers="headers" 
			v-model:search="search" :items="items"  @click:row="handleClick">
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
				  :text="'Used: ' + item.cost.expenditure + ' ' + item.cost.unit"
				></v-chip>
				<br>
				<v-chip label size="small" variant="text"
				  :text="'Budget: ' + item.cost.budget + ' ' + item.cost.unit"
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
  import { ref, onMounted } from 'vue'
  import axios from 'axios'
  import convertUrl from './MyUrl.ts'
  const color = ref({
    no: 'green',
    warning: 'yellow',
  error: 'red'
  })
  const props = defineProps(['headers', 'url', 'title'])
  const emit = defineEmits(['onProjectSelect'])
  const search = ref('')
  const items = ref()
  const title = ref(props.title)
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
	onMounted(() => {
		retreive()
	});

  function handleClick(event, row) {
	const id = row.item.id;
	emit('onProjectSelect', id);
  }
  
  function onAddNew() {
	const obj = {name: 'New Project', type: 'Construction', description: 'descritpion', area: 'area' }
	axios.post(convertUrl("/api/project/v1/project")	, obj)
	.then(function (response) {
		retreive()
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});	
  }
  
  function retreive() {
	  axios.get(convertUrl(props.url))
	  .then(function (response) {
	  	items.value = response.data;
	  })
	  .catch(function (error) {
	    console.log(error);
	  })
	  .finally(function () {
	  });
  }	
</script>

<script>

</script>