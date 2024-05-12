<template>
  <v-app>
	<v-system-bar>
		<AppHeader @onMenuChanged="onMenuChanged" />
	</v-system-bar>
	
    <v-main>
		<v-container fluid class="fill-height">
			<component :is="MainComponents[`${currentMenu}`]"/>
		</v-container>
    </v-main>

	<v-footer color="primary" app>
		<AppFooter />
	</v-footer>
    
  </v-app>
</template>

<script setup>
import { computed, ref, shallowReactive } from 'vue'
import Project from './components/Project.vue'
import HumanResource from './components/HumanResource.vue'
import Asset from './components/Asset.vue'

const currentMenu = ref('project');
function onMenuChanged(menu) {
	currentMenu.value = menu;
	
}

const MainComponents = shallowReactive({
	project: Project,
	hr: HumanResource,
	asset: Asset
})
</script>
