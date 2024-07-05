<template>
  <div class="login-container">
    <div class="login-box">
      <div class="logo">
        <img src="../../public/avatar.jpg" alt="logo" />
      </div>
      <h2>WeBLOG登录</h2>
      <p class="tagline">代码改变世界</p>
      <div class="tabs">
        <button :class="{ active: isPasswordLogin }" @click="isPasswordLogin = true">密码登录</button>
      </div>
      <form v-if="isPasswordLogin" @submit.prevent="login">
        <input type="text" placeholder="登录用户名 / 邮箱" v-model="username" />
        <input type="password" placeholder="密码" v-model="password" />
        <div class="remember-me">
          <input type="checkbox" id="remember" v-model="rememberMe" />
          <label for="remember">记住我</label>
        </div>
        <button type="submit" class="login-button">登录</button>
      </form>
      <div class="third-party-login">
        <p>第三方登录/注册</p>
        <div class="icons">
          <a href="#"><IconWeChat></IconWeChat></a>
          <a href="#"><IconQQ></IconQQ></a>
          <a href="#"><IconGithub></IconGithub></a>
        </div>
      </div>
      <router-link to="/register" class="toRegister">没有账号，立即注册</router-link>
    </div>
  </div>
</template>

<script>
import IconGithub from "@/components/icons/IconGithub.vue";
import IconQQ from "@/components/icons/IconQQ.vue";
import IconWeChat from "@/components/icons/IconWeChat.vue";
import axios from "axios";

import {store} from '@/store/modules/index'
import { request } from "@/request";
import {login} from "@/api/user"
import { useRoute, useRouter } from 'vue-router'

const router = useRouter();


export default {
  components: { IconGithub, IconQQ, IconWeChat },
  data() {
    return {
      isPasswordLogin: true,
      username: '',
      password: '',
      rememberMe: false,
      phone: '',
      verificationCode: '',
    };
  },
  methods: {
    login() {
      login(this.username, this.password).then(response => {
        if (response.data.code == 1) {
          store.commit('setBio', response.data.data.bio);
          store.commit('setToken', response.data.data.token);
          store.commit('setUsername', response.data.data.username);
          store.commit('setUid', response.data.data.userId);
          store.commit('setAvatar', response.data.data.avatar);
          localStorage.setItem('token', response.data.data.token);
          localStorage.setItem('uid', response.data.data.userId);
          localStorage.setItem('isLoggedOut', 'false');
          console.log("登录中...");
          this.$router.push('/community');
        } else {
          alert(response.data.msg);
        }
      }).catch(error => {
        console.error(error);
      });
      console.log("登录中...");
    },
  },
};
</script>

<style scoped>
/* 保持现有样式 */
.login-container {
  top: 0;
  left: 0;
  width: 100%;
  position: fixed;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
}

.login-box {
  width: 360px;
  padding: 40px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.logo img {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  margin-bottom: 10px;
}

h2 {
  margin: 10px 0;
  font-size: 24px;
}

.tagline {
  margin: 5px 0 20px;
  color: #999;
}

.tabs {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.tabs button {
  background: none;
  border: none;
  font-size: 16px;
  padding: 10px 20px;
  cursor: pointer;
}

.tabs button.active {
  border-bottom: 2px solid #007bff;
  color: #007bff;
}

form {
  display: flex;
  flex-direction: column;
}

input {
  margin: 10px 0;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 14px;
}

.remember-me {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.remember-me label {
  margin-left: 5px;
  font-size: 14px;
  color: #666;
}

.login-button {
  padding: 10px;
  background-color: #007bff;
  border: none;
  border-radius: 4px;
  color: white;
  font-size: 16px;
  cursor: pointer;
}

.third-party-login {
  margin-top: 20px;
}

.third-party-login p {
  margin-bottom: 10px;
}

.icons {
  display: flex;
  justify-content: center;
}

.icons a {
  margin: 0 10px;
}

.icons img {
  width: 30px;
  height: 30px;
}

.toRegister {
  display: block;
  margin-top: 20px;
  color: #007bff;
  text-decoration: none;
}
</style>
