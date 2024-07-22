<template>
	<v-navigation-drawer permanent>
		<AssetFilter></AssetFilter>
	</v-navigation-drawer>
	<v-card>
		<AssetTable :url="'/api/asset/v1/myAssets'"></AssetTable>
	</v-card>
</template>