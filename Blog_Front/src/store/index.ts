import { createStore, Store } from 'vuex';
import { InjectionKey } from 'vue';
import myModule from './myModule';
import { RootState } from './types';

export const key: InjectionKey<Store<RootState>> = Symbol();

export const store = createStore<RootState>({
  modules: {
    myModule, // 引入子模块
  },
});

export default store;