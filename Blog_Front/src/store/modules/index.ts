import { createStore, Store } from 'vuex';
import { InjectionKey } from 'vue';
import { RootState } from '../types';

export const key: InjectionKey<Store<RootState>> = Symbol();

export const store = createStore<RootState>({
  state() {
    return {
      token: '',
      uid: 1,
      username: '',
      avatar: '',
      bio: ''
    };
  },
  getters: {
    getToken(state) {
      return state.token;
    },
    getUid(state) {
      return state.uid;
    },
    getUsername(state) {
      return state.username;
    },
    getAvatar(state) {
      return state.avatar;
    },
    getBio(state) {
      return state.bio;
    }
  },
  mutations: {
    setToken(state, token: string) {
      state.token = token;
    },
    setUid(state, uid: number) {
      state.uid = uid;
    },
    setUsername(state, newUsername: string) {
      state.username = newUsername;
    },
    setBio(state, newBio: string) {
      state.bio = newBio;
    },
    setAvatar(state, newAvatar: string) {
      state.avatar = newAvatar;
    }
  },
  actions: {
    // 可以在此处定义异步的 Vuex actions
  }
});

export default store;
