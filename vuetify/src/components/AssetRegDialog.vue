<template>
  <div class="pa-4 text-center">
    <v-dialog
      v-model="dialog"
      max-width="800"
    >

      <v-card
        prepend-icon="mdi-account"
        title="Register Asset"
      >

	  {{ props.item.manufacturer }} {{ props.item.family }} {{ props.item.model }}
        <v-divider></v-divider>
		<v-text-field v-model="props.item.serialNumber" label="Serial Number"></v-text-field>
		
        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
            text="Close"
            variant="plain"
            @click="dialog = false"
          ></v-btn>
		  
          <v-btn
            color="primary"
            text="OK"
            variant="tonal"
            @click="onOK"
          ></v-btn>
		  
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script setup>
  import { ref, shallowRef, watch} from 'vue'
  import axios from 'axios'
  import convertUrl from './MyUrl.ts'
  const props = defineProps(['open', 'item']) 
  const dialog = shallowRef(false)
  const item =   ref()
  const emit = defineEmits(['onUpdate'])
  watch(() => props.open, () => {
  	dialog.value = true
  });
  
  
  function onOK() {
	dialog.value = false
	debugger
	axios.post(convertUrl("/api/asset/v1/myAsset"), props.item)
		.then(res=>{
			console.log(res)
			
			emit('onUpdate')
		}).catch(err=>{
			console.log(err)
		})
  }
  </script>
