<template>
  <div class="pa-4 text-center">
    <v-dialog
      v-model="dialog"
      max-width="800"
    >
      <template v-slot:activator="{ props: activatorProps }">
        <v-btn
          class="text-none font-weight-regular"
          prepend-icon="mdi-account"
          text="New Profile"
          variant="tonal"
          v-bind="activatorProps"
        ></v-btn>
      </template>

      <v-card
        prepend-icon="mdi-account"
        title="Asset Profile"
      >

		<AssetEditPane :item="item"></AssetEditPane>
		
        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>

          <v-btn
            text="Close"
            variant="plain"
            @click="dialog = false"
          ></v-btn>

		  <v-btn
		    color="primary"
		    text="Create New"
		    variant="tonal"
		    @click="onCreateNew"
		  ></v-btn>
		  
          <v-btn
            color="primary"
            text="Overrite"
            variant="tonal"
            @click="onOverwrite"
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
  
  watch(() => props.item, () => {
  	item.value = JSON.parse(JSON.stringify(props.item))
  });
  
  function onOverwrite() {
	dialog.value = false
	
	axios.post(convertUrl("/api/asset/v1/assets"), item.value)
		.then(res=>{
			console.log(res)
			
			emit('onUpdate')
		}).catch(err=>{
			console.log(err)
		})
  }
  
  function onCreateNew() {
	dialog.value = false
	
	item.value.id = undefined
	axios.post(convertUrl("/api/asset/v1/assets"), item.value)
		.then(res=>{
			console.log(res)
			emit('onUpdate')
		}).catch(err=>{
			console.log(err)
		})
  }
</script>
