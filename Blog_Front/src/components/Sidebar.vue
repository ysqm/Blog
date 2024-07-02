<template>
  <div class="sidebar">
    <ul>
      <li><router-link to="/subscribe">订阅</router-link></li>
      <li><router-link to="/follow">关注</router-link></li>
      <li><router-link to="/my-comments">我评</router-link></li>
      <li><router-link to="/my-likes">我赞</router-link></li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'Sidebar'
}
</script>

<style scoped>
.sidebar {
  position: fixed;
  top: 50px; /* 确保导航栏的高度 */
  left: 0;
  width: 100px;
  height: calc(100vh - 50px); /* 调整高度以排除导航栏 */
  background-color: #f9f9f9;
  padding: 20px;
  box-shadow: 2px 0 5px rgba(0,0,0,0.1);
  flex-shrink: 0;
  overflow-y: auto;
}
.sidebar ul {
  list-style: none;
  padding: 0;
}
.sidebar ul li {
  margin-bottom: 10px;
}
.sidebar ul li a {
  text-decoration: none;
  color: black;
}
</style>
