<template>
	<v-dialog
	  v-model="dialog"
	  max-width="800"
	>
		<v-card
		  prepend-icon="mdi-account"
		  title="Select MOP"
		>
			<MopList @on-select="onSelect"></MopList>

						<v-card-actions>
			  <v-spacer></v-spacer>
			
			  <v-btn
			    text="Close"
			    variant="plain"
			    @click="dialog = false"
			  ></v-btn>
			  			  
			</v-card-actions>		
		</v-card>
	</v-dialog>
</template>

<script setup lang="ts">
import {onMounted, ref, watch} from 'vue'

const props = defineProps(['open']) 
const emit = defineEmits(['onSelect'])
const dialog = ref(false)

watch(() => props.open, () => {
	dialog.value = true
});

function onSelect(id) {
	dialog.value = false
	emit('onSelect', id)
}
</script>