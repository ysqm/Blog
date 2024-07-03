import axios, { AxiosRequestConfig, AxiosResponse } from 'axios';
import store from './store/modules';

export interface MyResponse{
    data:any;
    msg:string;
    code:number;
}

const instance = axios.create({
  // 可以在这里设置通用的请求配置，如baseURL、请求拦截器等
  headers: {
    common: {
      'token': store.state.token
    }
  }
});

export function request<T>(config: AxiosRequestConfig): Promise<AxiosResponse<T>> {
    return instance.request<T>(config);
}