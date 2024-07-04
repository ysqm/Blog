<template>
  <div class="header-component">
    <img class="avatar" src="../../public/avatar.jpg" alt="User Avatar" @click="toggleMenu">
    <div v-show="menuVisible" class="dropdown-menu" @mouseover="resetTimer" @mouseleave="startHideTimer">
      <router-link to="/self-home" class="choice">个人博客</router-link>
      <router-link to="/account" class="choice">用户中心</router-link>
      <IconLogOutRight @click="logOut"/>
    </div>
  </div>
</template>

<script>
import IconLogOutRight from "@/components/icons/IconLogOutRight.vue";
import { store } from '@/store/modules/index'; // 确保正确导入 Vuex store
import { logout } from "@/api/user.ts";

export default {
  name: 'HeaderComponent',
  components: {IconLogOutRight},
  data() {
    return {
      menuVisible: false,
      hideTimer: null,
      userId: localStorage.getItem('uid')
    };
  },
  methods: {
    toggleMenu() {
      this.menuVisible = !this.menuVisible;
      if (this.menuVisible) {
        this.startHideTimer();
      } else {
        clearTimeout(this.hideTimer);
      }
    },
    startHideTimer() {
      clearTimeout(this.hideTimer);
      this.hideTimer = setTimeout(() => {
        this.menuVisible = false;
      }, 1000);
    },
    resetTimer() {
      clearTimeout(this.hideTimer);
    },
    logOut() {
      // logout(parseInt(this.userId)).then(response => {
      //   if (response.data.code === 1) {
      //     localStorage.setItem('isLoggedOut', 'true');
      //     localStorage.removeItem('token');
      //     localStorage.removeItem('uid');
      //     localStorage.removeItem('username');
      //     store.commit('setIsLoggedOut', true);
      //     console.log("退出登录...");
      //     location.reload();
      //   }
      // });
      localStorage.setItem('isLoggedOut', 'true');
      localStorage.removeItem('token');
      localStorage.removeItem('uid');
      localStorage.removeItem('username');
      store.commit('setIsLoggedOut', true);
      location.reload();
    }
  }
};
</script>

<style scoped>
.header-component {
  position: relative;
}

.avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  cursor: pointer;
  margin-right: 40px;
}

.dropdown-menu {
  display: block;
  position: absolute;
  background-color: white;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
  z-index: 1;
  top: 60px;
  right: 0;
  min-width: 130px;
}

.dropdown-menu a {
  display: block;
  padding: 12px 15px;
  text-decoration: none;
  color: black;
  text-align: center;
}

.dropdown-menu a:hover {
  background-color: #f1f1f1;
}
</style>
