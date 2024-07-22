<template>
	<AssetTable :url="'/api/asset/v1/assets'" @on-select="onSelect" :update="update"></AssetTable>
	<AssetDialog :open="dialog" :item="selectedItem" @on-update="onUpdate"></AssetDialog>
</template>
<script setup lang="ts">
import axios from 'axios'
import { ref, watch, onMounted } from 'vue'
import convertUrl from './MyUrl.ts'

const props = defineProps([''])

const selectedItem = ref()
const dialog = ref(0)
const update = ref(true)

onMounted(() => {
//	retrieve();	
});

function onSelect(item) {
	selectedItem.value = item
	dialog.value = dialog.value + 1
}

function onUpdate() {
	update.value = !update.value
}
</script>