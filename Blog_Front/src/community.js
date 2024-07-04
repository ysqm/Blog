import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import axios from "axios";
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

// axios.defaults.baseURL = '/api'; // 请确保此URL正确
// 创建vue实例app
const app = createApp(App)
// 启动我们需要的插件
app.use(router)
// 配置vue启用axios
app.config.globalProperties.$axios = axios
axios.defaults.withCredentials = false;
app.use(ElementPlus);
// 挂载app
app.mount('#app')
//导入所有图标并进行全局注册
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}