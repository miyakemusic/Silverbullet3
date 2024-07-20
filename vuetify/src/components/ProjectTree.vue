<template>
	<v-btn @click="initTree">Init</v-btn>
	<v-btn @click="getTree">Get</v-btn>
	<v-btn @click="deleteNode">Delete</v-btn>
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
  <template v-slot:prepend="{ item, open }">
    <v-icon v-if="item.file === 'node'">
      {{ open ? 'mdi-folder-open' : 'mdi-folder' }}
    </v-icon>
    <v-icon v-else>
      {{ files[item.file] }}
    </v-icon>
  </template>

  </v-treeview>
</template>

<script setup>
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
    {
		id: 1,
      title: '.git',
	  file: 'project'
    },
    {
		id: 2,
      title: 'node_modules',
	  file: 'project'
    },
	{
		id: 5,
	  title: 'node_modules',
	  file: 'project'
	},
  ])
  const selected = computed(() => {
    console.log(active.value)
    if (!active.value.length) return undefined
    const id = active.value[0]
	return id;
    //return users.value.find(user => user.id === id)
  })  
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
  	  .then(function (response) {
		items.value = response.data;
  	    console.log(response);
  	  })
  	  .catch(function (error) {
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
	
	var id = item[0];
	emit('onNodeSelect', id)
  }
</script>

<script>

</script>
