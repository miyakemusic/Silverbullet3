<template>
	<v-row>
		
		<v-col>
			NodeProp
			<NodeProp :nodeid="props.nodeid" @on-mop-update="onMopUpdate"></NodeProp>
		</v-col>
		<v-col>
			<v-card width="500" variant="outlined" class="ma-2">
				ProgressTable
				<ProgressTable :nodeid="props.nodeid" :key="mopid"></ProgressTable>
			</v-card>
		</v-col>
		<v-col>
			<v-card width="300" variant="outlined" class="ma-2">
				ProgressPieChartApex
				<ProgressPieChartApex :nodeid="props.nodeid" :key="mopid"></ProgressPieChartApex>
			</v-card>
		</v-col>
		<v-card width="500" variant="outlined" class="ma-2">
			TestItemProgressPieChartApex
			<TestItemProgressPieChartApex :nodeid="props.nodeid" :key="mopid"></TestItemProgressPieChartApex>
		</v-card>


		<v-card width="500" variant="outlined" class="ma-2">
			TestItemSummary
			<TestItemSummary :nodeid="props.nodeid" :key="mopid"></TestItemSummary>
		</v-card>
		<v-card variant="outlined" class="ma-2">
			TestItemTable
			<TestItemTable :key="mopid" :nodeid="props.nodeid"></TestItemTable>
		</v-card>
	</v-row>
</template>
<script setup lang="ts">
import { ref, watch, onMounted } from 'vue'

const props = defineProps(['nodeid'])
const mopid = ref()

function onMopUpdate(id : string) {
	mopid.value = id
}
</script>