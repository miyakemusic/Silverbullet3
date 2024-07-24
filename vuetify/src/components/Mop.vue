<template>
	<v-navigation-drawer permanent>
		<MopNavigation></MopNavigation>
	</v-navigation-drawer>
	<MopTable :mopid="mopid"></MopTable>
</template>

<script setup lang="ts">
import {onMounted, ref} from 'vue'
import axios from 'axios'
import convertUrl from './MyUrl.ts'
const props = defineProps(['mopid'])

const mopid = ref(1)

</script>