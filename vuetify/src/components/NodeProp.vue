<template>
	<v-card width="400">
		<v-card-text>
		<v-row>
			<v-col>Node Name</v-col>
			<v-col>{{ dto.name }}</v-col>
		</v-row>
		<v-row>
			<v-col>Node Type</v-col>
			<v-col>{{ dto.type }}</v-col>
		</v-row>
		<v-row v-if="dto.type=='dut'">
			<v-col>
				<v-btn text="MOP" @click="onMopClick"></v-btn>
			</v-col>
			<v-col v-if="dto.mop != null">
				{{ dto.mop.value }}
			</v-col>
		</v-row>
		</v-card-text>
	</v-card>
	
	<MopListDialog :open="open" @on-select="onSelect"></MopListDialog>
</template>

<script setup lang="ts">

import { ref, watch, onMounted } from 'vue'
import axios from 'axios'
import convertUrl from './MyUrl.ts'
import MopListDialog from './MopListDialog.vue';

const props = defineProps(['nodeid'])

const emit = defineEmits<{
	onMopUpdate: [value: string]
}>()

const dto = ref({name:'', type:'', mop:{value:''}})
const open = ref(false)
const mop = ref()

onMounted(() => {
	retrieve()
});

watch(() => props.nodeid, () => {
	retrieve();
});

function onMopClick() {
	open.value = !open.value	
}

function retrieve() {
	axios.get(convertUrl('/api/project/v1/node/' + props.nodeid))
	.then(function (response) {
		dto.value = response.data
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});
}

function onSelect(id) {
	mop.value = id;
	const json = {'id': id}
	axios.put(convertUrl('/api/project/v1/node/' + props.nodeid + '/mop/'), json)
	.then(function (response) {
		dto.value = response.data
		emit('onMopUpdate', id)
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});
}
 </script>