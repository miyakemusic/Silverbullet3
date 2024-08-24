<script setup>
import { GoogleMap, HeatmapLayer } from 'vue3-google-map'
import {  ref, watch, onMounted  } from 'vue';
import axios from 'axios'
import convertUrl from './MyUrl.ts'
const center = { lat: 37.774546, lng: -122.433523 }
const { VITE_GMAP_API_KRY } = import.meta.env;

const heatmapData = [
  { location: { lat: 37.782, lng: -122.447 }, weight: 0.5 },
//  { lat: 37.782, lng: -122.445 },
  { location: { lat: 37.782, lng: -122.443 }, weight: 2 },
  { location: { lat: 37.782, lng: -122.441 }, weight: 3 },
  { location: { lat: 37.782, lng: -122.439 }, weight: 2 },
// { lat: 37.782, lng: -122.437 },
  { location: { lat: 37.782, lng: -122.435 }, weight: 0.5 },

  { location: { lat: 37.785, lng: -122.447 }, weight: 3 },
  { location: { lat: 37.785, lng: -122.445 }, weight: 2 },
//  { lat: 37.785, lng: -122.443 },
  { location: { lat: 37.785, lng: -122.441 }, weight: 0.5 },
//  { lat: 37.785, lng: -122.439 },
  { location: { lat: 37.785, lng: -122.437 }, weight: 2 },
  { location: { lat: 37.785, lng: -122.435 }, weight: 3 },
]
const radius = 30;
const heatmapLayerData = {data : heatmapData, radius: radius}
const ready = ref(false)
const renderKey = ref(0)

onMounted(() => {
	retrieve();	
});
function retrieve() {
	axios.get(convertUrl("/api/geo/v1/heatmap"))
	.then(function (response) {
		
		center.lat = response.data.center.lat
		center.lng = response.data.center.lng
		
		heatmapLayerData.data = response.data.heatmap
		
		ready.value = true
		
		renderKey.value = renderKey.value + 1
	})
	.catch(function (error) {
	  console.log(error);
	})
	.finally(function () {
	});		
}
</script>

<template>
<v-btn @click="retrieve">Update</v-btn>
  <GoogleMap 
  	:key="renderKey"
    :api-key="VITE_GMAP_API_KRY"
    :libraries="['visualization']"
    style="width: 100%; height: 500px"
    :center="center"
    :zoom="13"
  >
  	<HeatmapLayer  :options="heatmapLayerData" />
  </GoogleMap>
</template>