import { createStore, Store } from 'vuex';
import { InjectionKey } from 'vue';
import { RootState } from '../types';

export const key: InjectionKey<Store<RootState>> = Symbol();

export const store = createStore<RootState>({
  state() {
    return {
      token:String,
      uid:Number,
      username:String,
      avatar:String,
      bio:String
    };
  },
  getters: {
    
  },
  mutations: {
    setToken(state, token: string) {
      state.token=token
    },
    setUid(state,uid:Number) {
      state.uid=uid
    },
    setUsername(state,newUsername: string) {
      state.username = newUsername
    },
    setBio(state,newBio: string) {
      state.bio = newBio
    },
    setAvatar(state,newAvatar: string) {
      state.newAvatar = newAvatar
    }
  },
  actions: {
  }
});

export default store;