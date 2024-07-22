<template>
  <v-app>
	<v-system-bar>
		<AppHeader ref="header" @onMenuChanged="onMenuChanged" />
	</v-system-bar>
	
    <v-main>
<!--		<v-container fluid class="fill-height">
			<component :is="MainComponents[`${currentMenu}`]"/>
		</v-container>-->
		
		<Project :projectid="projectid" v-if="currentMenu=='project'"></Project>
		<HumanResource v-if="currentMenu=='hr'"></HumanResource>
		<Asset v-if="currentMenu=='asset'"></Asset>
		<Home v-if="currentMenu=='home'" @onProjectSelect="onProjectSelect"></Home>
		<Test v-if="currentMenu=='test'" @onProjectSelect="onProjectSelect"></Test>
		<TreeTest v-if="currentMenu=='treeTest'"></TreeTest>
		<RegAsset v-if="currentMenu=='regasset'"></RegAsset>
		<EquipmentManage v-if="currentMenu=='equipment'"></EquipmentManage>
		
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
import Home from './components/Home.vue'

const currentMenu = ref('test');
const projectid = ref()
const header = ref()

function onMenuChanged(menu) {
	currentMenu.value = menu;
}

function onProjectSelect(id) {
	header.value.setSelected('project')
	currentMenu.value = 'project'
	projectid.value = id
}

</script>
