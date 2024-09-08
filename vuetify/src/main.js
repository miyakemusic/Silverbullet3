/**
 * main.js
 *
 * Bootstraps Vuetify and other plugins then mounts the App`
 */

// Plugins
import { registerPlugins } from '@/plugins'
import router from '@/router'
// Components
import App from '@/App.vue'

// Composables
import { createApp } from 'vue'
import VueApexCharts from "vue3-apexcharts"
import {JsonEditorPlugin} from 'vue3-ts-jsoneditor'
//import VueDatePicker from '@vuepic/vue-datepicker';
//import '@vuepic/vue-datepicker/dist/main.css';

const app = createApp(App)

app.use(VueApexCharts)
app.use(JsonEditorPlugin)
app.use(router)

registerPlugins(app)
//app.component('VueDatePicker', VueDatePicker);
app.mount('#app')
