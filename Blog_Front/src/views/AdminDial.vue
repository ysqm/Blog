<template>
  <div class="common-layout">
    <el-container style="height: 100%;">
      <el-aside width="200px" style="height: 100%;">
        <template #title>
          <el-icon><Menu /></el-icon>
        </template>
        <el-menu default-active="dashboard" @select="handleMenuSelect">
          <el-menu-item index="dashboard" :class="{'active': currentPath === '/dashboard'}" @click="handleTabClick('dashboard')">
            <el-icon><Monitor /></el-icon>仪表盘
          </el-menu-item>
          <el-menu-item index="article" :class="{'active': currentPath === '/article'}" @click="handleTabClick('article')">
            <el-icon><Management /></el-icon>文章管理
          </el-menu-item>
          <el-menu-item index="tag" :class="{'active': currentPath === '/tag'}" @click="handleTabClick('tag')">
            <el-icon><PriceTag /></el-icon>标签管理
          </el-menu-item>
          <el-menu-item index="user" :class="{'active': currentPath === '/user'}" @click="handleTabClick('user')">
            <router-link to="/AdminDial/user">
              <el-icon><User /></el-icon>用户管理
            </router-link>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main class="main-content">
          <component :is="currentComponent"></component>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { ref } from 'vue';

export default {
  name: 'AdminDial',
  setup() {
    const activeTab = ref('');
    const handleTabClick = (tab) => {
      activeTab.value = tab;
    };
    return {
      activeTab,
      handleTabClick,
    };
  },
};
</script>

<style scoped>
.common-layout {
  height: 100vh;
}

.el-container {
  height: 100%; /* Ensure the container fills the parent height */
}

.el-aside {
  height: 100%; /* Ensure the aside fills the container height */
}

.main-content {
  background-color: #79bbff;
  height: 90%; /* Ensure the main content fills the remaining space */
  overflow: auto; /* Allow scrolling if content overflows */
}

.active {
  background-color: #73767a;
  color: #95d475;
  font-weight: bold;
}

.asideMenu {
  background-color: #79bbff;
}
</style>