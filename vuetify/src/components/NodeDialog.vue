<template>
  <div class="pa-4 text-center">
    <v-dialog
      v-model="dialog"
      max-width="800"
    >

      <v-card
        prepend-icon="mdi-account"
        title="Node"
      >

        <v-divider></v-divider>
		<v-text-field v-model="nodeName" label="Node Name"></v-text-field>
		<v-text-field v-model="nodeType" label="Node Type"></v-text-field>
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
  const props = defineProps(['open', 'name', 'type']) 
  const dialog = shallowRef(false)
  const emit = defineEmits(['onOk'])
  
  const nodeName = ref(props.name)
  const nodeType = ref(props.type)
  
  watch(() => props.open, () => {
  	dialog.value = true
  });
  
	watch(() => props.name, () => {
	nodeName.value = props.name
   });
   watch(() => props.type, () => {
    	nodeType.value = props.type
    }); 
  function onOK() {
	dialog.value = false
	
	emit('onOk', 
		{
			name: nodeName.value,
			type: nodeType.value
		})
  }
  </script>
