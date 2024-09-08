import { createWebHistory, createWebHashHistory,createRouter } from "vue-router"
import Mop from "@/components/MopEntry.vue"
import Test from "@/components/Geo.vue"
import Top from "@/Top.vue"
const routes = [
  {
    path: "/",
    name: "Top",
    component: Top,
  },
  {
    path: "/mop/:id",
    name: "Mop",
    component: Mop,
  },
];

const router = createRouter({
//  history: createWebHistory(),
history: createWebHashHistory(),
  routes,
});

export default router;