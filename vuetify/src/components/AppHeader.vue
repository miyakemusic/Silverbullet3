<template>
  <v-app-bar dark>
    <v-app-bar-nav-icon />
	<v-btn-toggle v-model="toggle" divided>
		<v-btn @click="$emit('onMenuChanged', 'home')">Home</v-btn>
		<v-btn @click="$emit('onMenuChanged', 'project')">Project</v-btn>
		<v-btn @click="$emit('onMenuChanged', 'hr')">Human Resource</v-btn>
		<v-btn @click="$emit('onMenuChanged', 'asset')">Assets</v-btn>
		<v-btn @click="$emit('onMenuChanged', 'test')">Test</v-btn>
	</v-btn-toggle>
	
    <v-spacer />
    <v-btn icon>
      <v-icon>mdi-home</v-icon>
    </v-btn>
    <v-btn icon>
      <v-icon>mdi-magnify</v-icon>
    </v-btn>
    <v-btn icon>
      <v-icon>mdi-account</v-icon>
    </v-btn>
    <v-btn icon>
      <v-icon>mdi-dots-vertical</v-icon>
    </v-btn>
  </v-app-bar>
</template>

<script setup>
	import { ref } from 'vue'
	const toggle = ref(0)
	const buttons =['home', 'project', 'hr', 'asset']
	defineEmits(['onMenuChanged'])
	
	function setSelected(menu) {
		const id = 		buttons.indexOf(menu);
		toggle.value = id;
	}
	
	defineExpose({
		setSelected,
	});
</script>

<script>

</script>