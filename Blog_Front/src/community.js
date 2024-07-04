import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import axios from "axios";
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import store from './store/modules';


// axios.defaults.baseURL = '/api'; // 请确保此URL正确
// 创建vue实例app
const app = createApp(App)
// 启动我们需要的插件
app.use(router)

app.use(store)
// 配置vue启用axios
app.config.globalProperties.$axios = axios
axios.defaults.withCredentials = false;
app.use(ElementPlus);
// 挂载app
app.mount('#app')

