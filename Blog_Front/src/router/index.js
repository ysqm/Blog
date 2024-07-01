import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';
import EditArticle from '../views/EditArticle.vue';
import MyArticles from '../views/MyArticles.vue';

Vue.use(VueRouter);

export default new Router({
    routes: [
        {
            path: '/',
            name: 'Home',
            component: Home,
        },
        {
            path: '/edit/:articleId?',
            name: 'EditArticle',
            component: EditArticle,
        },
        {
            path: '/my-articles',
            name: 'MyArticles',
            component: MyArticles,
        },
    ],
});
