import Vue from 'vue';
import VueRouter, { RouteConfig } from 'vue-router';
import LoginRegistrationView from '@/views/LoginRegistrationView.vue';
import Registration from '@/components/registration/Registration.vue';
import Login from '@/components/login/Login.vue';
import HomeView from '@/views/HomeView.vue';

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: '/',
    name: 'Registration',
    component: LoginRegistrationView,
    children: [
      {
        path: 'register',
        component: Registration,
      },
      {
        path: 'login',
        component: Login,
      },
    ],
  },
  {
    path: '/home',
    name: 'Home',
    component: HomeView,
  },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

export default router;
