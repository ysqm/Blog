// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import Editor from '../views/Editor.vue';
import Community from "@/views/Community.vue";
import Login from "@/views/Login.vue";
import Register from "@/views/Register.vue";

import Home from '../views/self-Home.vue';
import Article from '../views/Article.vue';
import Category from '../views/self-Category.vue';
import Tag from '../views/self-Tag.vue';
import Archive from '../views/self-Archive.vue';
import KnowledgeBase from '../views/self-KnowledgeBase.vue';
import NewPost from '../views/self-NewPost.vue';
import AccountCenter from "@/views/AccountCenter.vue";

const routes = [
    {
        path: '/self-home',
        name: 'self-Home',
        component: Home
    },
    {
        path: '/Article/:id',
        name: 'Article',
        component: Article
    },
    {
        path: '/self-category',
        name: 'Category',
        component: Category
    },
    {
        path: '/self-tag',
        name: 'Tag',
        component: Tag
    },
    {
        path: '/self-archive',
        name: 'Archive',
        component: Archive
    },
    {
        path: '/self-knowledge-base',
        name: 'KnowledgeBase',
        component: KnowledgeBase
    },
    {
        path: '/self-new-post',
        name: 'NewPost',
        component: NewPost
    },
    {
        path: '/editor',
        name: 'Editor',
        component: Editor
    },
    {
        path: '/Community',
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
    },
    {
        path:  '/',
        redirect: '/Community'
    },
    {
        path:'/account',
        name:'AccountCenter',
        component: AccountCenter
    },
    {
        path: '/404',
        name: '404',
        component: 404
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
