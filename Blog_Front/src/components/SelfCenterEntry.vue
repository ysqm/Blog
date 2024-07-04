<template>
  <div class="header-component">
    <img class="avatar" src="../../public/avatar.jpg" alt="User Avatar" @click="toggleMenu">
    <div v-show="menuVisible" class="dropdown-menu" @mouseover="resetTimer" @mouseleave="startHideTimer">
      <router-link to="/self-home" class="choice">个人博客</router-link>
      <router-link to="/account" class="choice">用户中心</router-link>
      <IconLogOutRight @click=""/>
    </div>
  </div>
</template>

<script>
import IconLogOutRight from "@/components/icons/IconLogOutRight.vue";
import IconLogOutLeft from "@/components/icons/IconLogOutLeft.vue";

export default {
  name: 'HeaderComponent',
  components: {IconLogOutLeft, IconLogOutRight},
  data() {
    return {
      menuVisible: false,
      hideTimer: null
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
      }, 10000);
    },
    resetTimer() {
      clearTimeout(this.hideTimer);
    }
  },
  beforeDestroy() {
    clearTimeout(this.hideTimer);
  },
  logOut(){

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
