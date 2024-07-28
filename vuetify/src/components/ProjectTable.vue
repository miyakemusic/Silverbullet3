<template>
	{{ projectName }}
	<v-row>
		<v-col>
			<v-card width="500" variant="outlined" class="ma-2">
				<ProgressTable :projectid="projectid"></ProgressTable>
			</v-card>
		</v-col>
		<v-col>
			<v-card width="300" variant="outlined" class="ma-2">
				<ProgressPieChartApex :projectid="projectid" :nodeid="selectedNode"></ProgressPieChartApex>
			</v-card>
		</v-col>
		<v-col>
			<v-card width="600" variant="outlined" class="ma-2">
				<TimeProgressTable :projectid="projectid"></TimeProgressTable>
			</v-card>
		</v-col>
		<v-col>
			<v-card width="300" variant="outlined" class="ma-2">
				Test Progress (Cost)
				<v-table density="compact">
					<thead>
						<tr>
							<td>Budget</td><td>Expenditure</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>{{ progress.cost.unit }} {{progress.cost.budget}}</td>
							<td>{{progress.cost.unit }} {{ progress.cost.expenditure }} ({{ progress.cost.expenditure*100/progress.cost.budget }} %)</td>
						</tr>
					</tbody>
				</v-table>
			</v-card>
		</v-col>
	</v-row>	
	
	<v-row>
		<v-col>
			<v-card width="400" variant="outlined" class="ma-2">
				DUT
				<v-table density="compact">
					<thead>
						<tr>
							<td>Type</td><td>Quantity</td><td>Completed</td>
						</tr>
					</thead>
					<tbody>
						<tr v-for="(item, index) in duts" :key="id">
						  <td>{{ item.name }}</td>
						  <td>{{ item.quantity }}</td>
						  <td>{{ item.done }} ( {{ item.done *100 / item.quantity }} %)</td>

						</tr>
					</tbody>
				</v-table>
			</v-card>
		</v-col>		
		
		<v-col>
			<v-card width="400" variant="outlined" class="ma-2">
				Testers
				<v-data-table 
					:items="testers" hide-default-footer density="compact" @click:row="handleClick">
				</v-data-table>
			</v-card>
		</v-col>
		
		<v-col>
			<v-card width="400" variant="outlined" class="ma-2">
				Human Resource
				<v-data-table 
					:items="humanresource" hide-default-footer density="compact" @click:row="handleClick">
				</v-data-table>
			</v-card>
		</v-col>
	</v-row>

	<v-card width="800" variant="outlined" class="ma-2">
		<ProgressLineChart :projectid="projectid" :url="'/api/project/v1/progress/cost/history'" :title="'Cost'" :unit="'MYen'"></ProgressLineChart>
	</v-card>
	<v-card width="800" variant="outlined" class="ma-2">
		<ProgressLineChart :projectid="projectid" :url="'/api/project/v1/progress/item/history'" :title="'Burndown'" :unit="'Items'"></ProgressLineChart>
	</v-card>
</template>

<script setup>
  import { ref, watch } from 'vue'
  import axios from 'axios'
  import convertUrl from './MyUrl.ts'
  
  const props = defineProps(['projectid', 'selectedNode'])
  
  const projectid = ref(props.projectid)
  const selectedNode = ref(props.selectedNode)
  const projectName = ref();
  const search = ref('')
  const items = [ ]
  const nodeName = ref()
  
  watch(() => props.selectedNode, () => {
	axios.get(convertUrl('/api/project/v1/nodeDetail/' + props.selectedNode))
	.then(function (response) {

	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});	
  });

  const progress = ref({
	items: {
		testPoints: 1024,
		pass: 80,
		fail: 22,
		completedTestPoints:102,
		percentage: 12
	},
	
	date: {
		startTest: '2024/1/3',
		deadline: '2024/9/1',
		estimatedCompletionDate: '2024/10/5',
		
		progressStatus: 'delay',
		diffDays: -4
	},
	cost: {
		budget: 100000,
		expenditure: 1000,
		unit: '$',
	}
  })
    
  const duts = ref([
  	{name: 'Cable', quantity: 20, done: 1, percent: 10},
	{name: 'Fibers', quantity: 120, done: 20, percent: 10},
	{name: 'Ports', quantity: 2400, done: 200, percent: 10}
  ])
  
  const testers = ref([ 
		{name: 'OTDR', used: 4, assigned: 5},
		{name: 'OPM', used: 18, assigned: 20},
		{name: 'OSA', used: 4, assigned: 5},
		{name: '100G Tester', used: 4, assigned: 5},
		{name: '400G Tester', used: 5, assigned: 5},
	])
	
  const humanresource = ref([
		{id: 1, name: 'Andre Young', role: 'Team Leader'},
		{id: 2, name: 'Snoop Dogg', role: 'Technician'},
		{id: 3, name: 'Ice Cube', role: 'Engineer'},
		{id: 4, name: 'Easy E', role: 'Engineer'},
		{id: 5, name: 'Tupac Amaru Shakur', role: 'Technician'},
	])
	
	axios.get(convertUrl('/api/project/v1/project/' + 	projectid.value))
	.then(function (response) {
		projectName.value = response.data.name;
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});		
</script>

<script>

</script>