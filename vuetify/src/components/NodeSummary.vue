<template>
	<v-row>
		<v-col>
			<v-card width="500" variant="outlined" class="ma-2">
				<ProgressTable :nodeid="props.nodeid"></ProgressTable>
			</v-card>
		</v-col>
		<v-col>
			<v-card width="300" variant="outlined" class="ma-2">
				<ProgressPieChartApex :nodeid="props.nodeid"></ProgressPieChartApex>
			</v-card>
		</v-col>
		<v-card width="500" variant="outlined" class="ma-2">
			<TestItemProgressPieChartApex :nodeid="props.nodeid"></TestItemProgressPieChartApex>
		</v-card>


		<v-card width="500" variant="outlined" class="ma-2">
			<TestItemSummary :nodeid="props.nodeid"></TestItemSummary>
		</v-card>
		<v-card width="600" variant="outlined" class="ma-2">
			<TestItemTable :nodeid="props.nodeid"></TestItemTable>
		</v-card>
	</v-row>
</template>
<script setup lang="ts">

const props = defineProps(['nodeid'])

</script>