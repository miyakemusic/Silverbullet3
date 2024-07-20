<template>
	<v-btn @click="init">Init</v-btn>
	Filter  {{ all }}
	<v-expansion-panels focusable multiple>
		<v-expansion-panel
			v-for="item in items"
			:key="item.title"
		>
			<v-expansion-panel-title>{{item.title}}</v-expansion-panel-title>
			<v-expansion-panel-text>
				<v-row>
					<v-col><v-checkbox label="Select All" :id="item.title" @change="changed(item)" :value="item.title" v-model="all" hide-details></v-checkbox></v-col>
				</v-row>
				<v-card>
				<v-checkbox
					hide-details
					v-for="optionEnabled in item.options"
					:label="optionEnabled.title"
					v-model="optionEnabled.enabled"
					 />			
				</v-card>      
			</v-expansion-panel-text>
		</v-expansion-panel>
	</v-expansion-panels>
</template>
<script setup lang="ts">
import {onMounted, ref} from 'vue'
import axios from 'axios'
import convertUrl from './MyUrl.ts'

const items = ref([])
const selected = ref([])
const all = ref(['',''])

function changed(item) {
	const found = items.value.find((element) => element.title == item.title);
	found.options.forEach(function(o) {
		o.enabled = all.value.includes(item.title)
	})
}
onMounted(() => {
	retrieve()
});
function retrieve() {
	var url = convertUrl('/api/asset/v1/filter')
	axios.get(url)
	.then(function (response) {
		items.value = response.data
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});			
}

function init() {
	var url = convertUrl('/api/asset/v1/init')
	axios.get(url)
	.then(function (response) {
		retrieve();
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});			
}
</script>