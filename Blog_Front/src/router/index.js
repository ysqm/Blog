// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import Editor from '../views/Editor.vue';
import Community from "@/views/Community.vue";
import Login from "@/views/Login.vue";


const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/editor',
        name: 'Editor',
        component: Editor
    },
    {
        path: '/community',
        name: 'Community',
        component: Community
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    }
];

// 解决重复路由报异常问题
const originalPush = createRouter.prototype.push;
createRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err);
};

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
