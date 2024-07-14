<template>
	<v-row>
		<v-card width="300" variant="outlined" class="ma-2">
			<HorizontalStackChart></HorizontalStackChart>
		</v-card>
	</v-row>
	<v-row>
		<v-col>
			<v-card width="500" variant="outlined" class="ma-2">
				<ProgressTable :nodeid="props.nodeid"></ProgressTable>
			</v-card>
		</v-col>
		<v-col>
			<v-card width="300" variant="outlined" class="ma-2">
				<ProgressChart :nodeid="props.nodeid"></ProgressChart>
			</v-card>
		</v-col>
	</v-row>
	<v-row>
		<v-card width="500" variant="outlined" class="ma-2">
			<TestItemSummary :nodeid="props.nodeid"></TestItemSummary>
		</v-card>
	</v-row>
	<v-row>
		<TestItemTable :nodeid="props.nodeid"></TestItemTable>
	</v-row>
</template>
<script setup lang="ts">

const props = defineProps(['nodeid'])

</script>