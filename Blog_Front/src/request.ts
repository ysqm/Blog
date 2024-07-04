import axios, { AxiosRequestConfig, AxiosResponse } from 'axios';
import store from './store/modules';

export interface MyResponse{
    data:any;
    msg:string;
    code:number;
}

const instance = axios.create({
  // 可以在这里设置通用的请求配置，如baseURL、请求拦截器等
  // headers: {
  //   common: {
  //     'token': store.state.token
  //   }
  // }
});

// 请求拦截器，在每次请求前，动态获取 store 中的 token 并添加到请求头
instance.interceptors.request.use(
    config => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers['token'] = ` ${token}`;
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);


export function request<T>(config: AxiosRequestConfig): Promise<AxiosResponse<T>> {
    return instance.request<T>(config);
}