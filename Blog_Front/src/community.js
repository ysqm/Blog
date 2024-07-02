import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import axios from "axios";


axios.defaults.baseURL = 'http://localhost:8080/'; // 请确保此URL正确
// 创建vue实例app
const app = createApp(App)
// 启动我们需要的插件
app.use(router)
// 配置vue启用axios
app.config.globalProperties.$axios = axios
// 挂载app
app.mount('#app')

