/**
 * main.js
 *
 * Bootstraps Vuetify and other plugins then mounts the App`
 */

// Plugins
import { registerPlugins } from '@/plugins'

// Components
import App from './App.vue'

// Composables
import { createApp } from 'vue'
import VueApexCharts from "vue3-apexcharts"
import {JsonEditorPlugin} from 'vue3-ts-jsoneditor'

const app = createApp(App)

app.use(VueApexCharts)
app.use(JsonEditorPlugin)

registerPlugins(app)

app.mount('#app')
