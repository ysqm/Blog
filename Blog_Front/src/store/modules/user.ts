import { Module } from 'vuex';
import { RootState } from '../types';

interface userState {
  // 定义子模块的状态属性
}

const user: Module<userState, RootState> = {
  namespaced: true, // 设置为命名空间模块（可选）
  state() {
    return {
      // 初始化子模块的状态
    };
  },
  getters: {
    // 定义子模块的 getters
  },
  mutations: {
    // 定义子模块的 mutations
  },
  actions: {
    // 定义子模块的 actions
  }
};

export default user;