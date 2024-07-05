<template>
  <div>
    <el-container>
      <el-aside width="200px">
        <el-collapse v-model="activeNames" @change="handleChange">
          <el-collapse-item name="1" class='asideMenu'>
            <template #title>
              <el-icon>
                <Menu/>
              </el-icon>
            </template>
            <el-menu default-active="dashboard" @select="handleMenuSelect">
              <el-menu-item index="dashboard" :class="{'active': currentPath === '/dashboard'}"
                            @click="handleTabClick('dashboard')">
                <el-icon>
                  <Monitor/>
                </el-icon>
                仪表盘
              </el-menu-item>
              <el-menu-item index="article" :class="{'active': currentPath === '/article'}   "
                            @click="handleTabClick('article')">
                <el-icon>
                  <Management/>
                </el-icon>
                文章管理
              </el-menu-item>
              <el-menu-item index="tag" :class="{'active': currentPath === '/tag'}" @click="handleTabClick('tag')">
                <el-icon>
                  <PriceTag/>
                </el-icon>
                标签管理
              </el-menu-item>


              <el-menu-item index="user" :class="{'active': currentPath === '/user'} " @click="handleTabClick('user')">
                <router-link to="/AdminDial/user">
                  <el-icon>
                    <User/>
                  </el-icon>
                  用户管理
                </router-link>
              </el-menu-item>
            </el-menu>
          </el-collapse-item>
        </el-collapse>
      </el-aside>
      <el-container>
        <el-header>
          <!-- 头部内容 -->
        </el-header>
        <el-main>
          <component :is="currentComponent"></component>
          <router-view/>
        </el-main>
        <el-footer>
          <!-- 底部内容 -->
        </el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import UserManage from '@/components/UserManage.vue';
import {ref} from 'vue';

export default {
  name: 'AdminDial',
  components: {
    UserManage
  },
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
<style>
.active {
  background-color: #73767a;
  color: #95d475;
  font-weight: bold;
}

el-aside{
  position: fixed;
  top: 0; /* 确保导航栏的高度 */
  left: 0;
  width: 100px;
  background-color: #F8F8F8;
  box-shadow: 2px 0 5px rgba(0,0,0,0.1);
  flex-shrink: 0;
  overflow-y: auto;
  margin-left: 0;
}

.asideMenu {
  background-color: #79bbff;
}
</style>
