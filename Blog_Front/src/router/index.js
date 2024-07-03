// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import Editor from '../views/Editor.vue';
import Community from "@/views/Community.vue";
import Login from "@/views/Login.vue";
import Register from "@/views/Register.vue";


const routes = [
    {
        path: '/editor',
        name: 'Editor',
        component: Editor
    },
    {
        path: '/',
        name: 'Community',
        component: Community
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
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
