<template>
  <div class="sidebar">
    <!-- Log out button with icon -->
    <router-link to="/"><IconLogOutLeft class="logOut" /></router-link>

    <!-- User avatar -->
    <div class="logo">
      <img src="../../public/avatar.jpg" alt="logo" />
    </div>

    <!-- Navigation items -->
    <ul>
      <li class="nav-item">账户设置</li>
      <li class="nav-item">会员中心</li>
      <li class="nav-item">个人资料</li>
      <li class="nav-item">屏蔽设置</li>
      <li class="nav-item">Access Tokens</li>
    </ul>
  </div>
</template>

<script>
import IconLogOutLeft from "@/components/icons/IconLogOutLeft.vue";

export default {
  name: 'Sidebar',
  components: { IconLogOutLeft }
}
</script>

<style scoped>
/* Sidebar container styling */
.sidebar {
  position: fixed;
  top: 0;
  left: 0;
  width: 190px;
  height: 100%;
  margin-left: 0;
  background-color: #F8F8F8;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
  flex-shrink: 0;
  border-radius: 10px;
  padding: 20px 0;
  display: flex;
  flex-direction: column;
  align-items: center; /* Center align items */
}

/* Log out icon styling */
.logOut {
  position: absolute;
  top: 20px;
  left: 20px;
  cursor: pointer;
}

/* User avatar styling */
.logo img {
  height: 80px;
  width: 80px;
  margin-bottom: 20px;
  border-radius: 50%;
  border: 2px solid #ddd;
}

/* Navigation items styling */
ul {
  list-style: none;
  padding: 0;
  width: 100%; /* Ensure the nav items take full width */
}

/* Individual navigation item styling */
.nav-item {
  padding: 15px 20px;
  margin: 5px 0;
  text-align: center;
  border-radius: 5px;
  transition: background-color 0.3s ease;
}

.nav-item:hover {
  background: #e9ecef;
}
</style>
