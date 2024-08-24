<template>
	<v-btn v-if="debug==true" @click="initTree">Init</v-btn>
	<v-btn v-if="debug==true" @click="getTree">Get</v-btn>
	<v-btn v-if="debug==true" @click="deleteNode">Delete All</v-btn>
	<v-btn @click="onAddNode" append-icon="mdi-folder-plus"></v-btn>
	<v-btn @click="onAddDut" append-icon="mdi-file-document-plus-outline"></v-btn>
	<v-btn @click="onDeleteNode" append-icon="mdi-trash-can-outline"></v-btn>
	<v-btn @click="onEditNode" append-icon="mdi-pencil"></v-btn>
  <v-treeview
  	v-model:activated="active"
    v-model="tree"
    :items="items"
	item-value="id"
	activatable
	hoverable
	open-on-click
	return-object
	@click:select="onNodeClicked"
	@update:open="onNodeClicked"
	@update:activated="onNodeClicked"
  >
  <template v-slot:prepend="{ item }">
    <v-icon v-if="item.file === 'folder'">

    </v-icon>
    <v-icon v-else>
      {{ files[item.file] }}
    </v-icon>
  </template>

  </v-treeview>
  
  <NodeDialog @on-ok="onOk" :open="dialog" :target="target" :name="nodeName" :type="nodeType"></NodeDialog>
</template>

<script setup lang="ts">
  import { ref } from 'vue'
  import { VTreeview } from 'vuetify/labs/VTreeview'
  import axios from 'axios'
  import { onMounted } from 'vue'
  import { computed } from 'vue' 
  import convertUrl from './MyUrl.ts'
  const emit = defineEmits(['onNodeSelect'])
  const props = defineProps(['projectid'])
  const active = ref([])
  const files = ref({
    html: 'mdi-language-html5',
    js: 'mdi-nodejs',
    dut: 'mdi-switch',
    testPoint: 'mdi-connection',
    pdf: 'mdi-file-pdf-box',
    png: 'mdi-file-image',
    txt: 'mdi-file-document-outline',
    project: 'mdi-dresser-outline',
  })
  const tree = ref([])
  const items = ref([
  ])
  
  const current = ref()
  
  const dialog = ref(false)
  const nodeName = ref('Node Name')
  const nodeType = ref("folder")
  const target = ref('new')
  
  const debug = ref(false)
  onMounted(() => {
	getTree();
  })
  
  
  function initTree() {
  	axios.get(convertUrl('/api/project/v1/initTree'))
  	  .then(function (response) {
  	    console.log(response);
  	  })
  	  .catch(function (error) {
  	    console.log(error);
  	  })
  	  .finally(function () {
  	  });
  }
  
  function getTree() {
  	axios.get(convertUrl('/api/project/v1/tree/' + props.projectid))
  	  .then(response => {
		items.value = response.data;
  	  })
  	  .catch(error => {
  	    console.log(error);
  	  })
  	  .finally(function () {
  	  });			
  }
  
  function deleteNode() {
	axios.delete(convertUrl('/api/project/v1/treeAll'))
	.then(function (response) {
		
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});	
  }
 
  function onNodeClicked(item) {
	var id = item[0]//.id;
	if (id != null) {
		current.value = id
	}
	else {
		//current.value = 	
	}
	emit('onNodeSelect', id)
  }
  
  function onAddNode() {
	target.value = 'new'
	nodeType.value = "folder"
	dialog.value = !dialog.value
  }
  
  function onAddDut() {
  	target.value = 'new'
	nodeType.value= "dut"
  	dialog.value = !dialog.value
  }
  
  function onDeleteNode() {
	  axios.delete(convertUrl('/api/project/v1/node/' + current.value))
	  .then(function (response) {
	  	getTree();
	  })
	  .catch(function (error) {
	    console.log(error);
	  })
	  .finally(function () {
	  });	
  }
  
  function onEditNode() {
	axios.get(convertUrl('/api/project/v1/node/' + current.value))
	.then(function (response) {
		nodeName.value = response.data.name
		nodeType.value = response.data.type
		
		target.value = 'edit'
		dialog.value = !dialog.value
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});	
	

  }
  
  function onOk(nodeInfo) {
	const obj = { name: nodeInfo.name, type: nodeInfo.type }
	var path;
	if (target.value == 'new') {
		path = 'childNode'
	}
	else {
		path = 'node'
	}
	axios.post(convertUrl('/api/project/v1/' + path + '/' + current.value), obj)
	.then(function (response) {
		getTree();
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});	
  }
</script>

