<style scoped>
.v-data-table {
  white-space: pre-line;
}
</style>
<template>
	<div v-if="mop != null">
		<v-btn @click="publish">Publish</v-btn>
		<v-card 
			:title="mop.title"
			:subtitle="mop.description">
	
			<v-img
			  :lazy-src="mop.image"
			  max-height="1000"
			  max-width="600"
			  :src="mop.image">
			</v-img>
			
		</v-card>
		<v-card :title="section.title" v-for="section in mop.sections">
			
			<v-row>
				<v-col v-for="image in section.images">
					<v-img 
					  :lazy-src="image"
					  max-height="1000"
					  max-width="600"
					  :src="image"
					></v-img>
					
				</v-col>
			</v-row>
			
	
			<v-data-table 			tableStyle="min-width: 50rem"
			  :items="section.lines"
			  :items-per-page="50"
			  class="elevation-1 my-3 mx-auto"
			  @click:row="clickRow"
			>
			
				<template v-slot:item.operation="{item}">
					{{ item.operation }}

				</template>
				
				<template v-slot:item.tool="{ item }" >
					<div v-if="item.tool != null">
						
						{{ item.tool.title }}
						<v-img  v-if="item.tool.image != null"
								:src="item.tool.image" 
						       :aspect-ratop="16/9" 
						       height="9vw" 
						       min-height="50px"
						       width="16vw" 
						       min-width="50px" 
						       class="ma-0 pa-0">
						</v-img>	
					</div>
				 </template>
				 
				 <template v-slot:item.target="{ item }" >
					 <div v-if="item.target != null">
					 	{{ item.target.title }}
					 	<v-img  v-if="item.target.image != null"
					 			:src="item.target.image" 
					 	       :aspect-ratop="16/9" 
					 	       height="9vw" 
					 	       min-height="50px"
					 	       width="16vw" 
					 	       min-width="50px" 
					 	       class="ma-0 pa-0">
					 	</v-img>	
					 </div>
				 </template>
				 
				 <template v-slot:item.criteria="{ item }" >
					<v-row v-if="item.criteria != null" v-for="criteria in item.criteria.criteria">
						{{ item.criteria.criteria }}						
					</v-row>
				 </template>
			</v-data-table>
		</v-card>
	</div>
</template>

<script setup="ts">
import {onMounted, ref, watch} from 'vue'
import axios from 'axios'
import convertUrl from './MyUrl.ts'
const props = defineProps(['mopid', "mop"])
const mop = ref({items:[]})

onMounted(() => {
//	mop.value = props.mop
//	
	if (props.mopid != null) {
		retrieve();	
	}
});

watch(() => props.mopid, () => {
	retrieve();
});


watch(() => props.mop, (newMop) => {
	mop.value = newMop
  }
)

function retrieve() {
	axios.get(convertUrl('/api/mop/v1/mops/' + props.mopid))
	.then(function (response) {
		mop.value = response.data
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});	
}

function publish() {
	axios.get(convertUrl('/api/mop/v1/mop/publish/' + mop.value.id))
	.then(function (response) {
		mop.value = response.data
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});	
}

function clickRow(event, row) {
	
}
</script>
