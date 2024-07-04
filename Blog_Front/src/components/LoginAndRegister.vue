<script setup>

</script>

<template>
  <div class="auth-buttons">
    <router-link to="/login"><button>登录</button></router-link>
    <router-link to="/register"><button>注册</button></router-link>
  </div>
</template>

<style scoped>
.auth-buttons {
  display: flex;
  margin-right: 20px;
}

.auth-buttons button {
  margin-right: 25px;
  padding: 5px 5px;
  border: none;
  color: #FFFFFF;
  background-color: #84fad1;
  border-radius: 4px;
  cursor: pointer;
  text-decoration: none;
}
</style>
