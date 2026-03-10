import { createRouter, createWebHistory } from 'vue-router';
import Home from './views/Home.vue';
import RawMaterials from './views/RawMaterials.vue';
import Products from './views/Products.vue';
import Optimization from './views/Optimization.vue';

const routes = [
  { path: '/', component: Home },
  { path: '/raw-materials', component: RawMaterials },
  { path: '/products', component: Products },
  { path: '/optimization', component: Optimization }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
