<template>
  <div class="sidebar">
    <div class="logo">
      <img src="../../public/avatar.jpg" alt="logo" />
    </div>
    <ul>
      <li>账户设置</li>
      <li>会员中心</li>
      <li>个人资料</li>
      <li>屏蔽设置</li>
      <li>博客申请</li>
      <li>Access Tokens</li>
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
  top:0;
  left: 0;
  width: 190px;
  height: 100%;
  margin-left: 0;
  background-color: #F8F8F8;
  box-shadow: 2px 0 5px rgba(0,0,0,0.1);
  flex-shrink: 0;
  border-radius: 10px;
  padding: 20px 0;
}

.logo img {
  height: 50px;
  width: 50px;
  margin-bottom: 20px;
  border-radius: 50%;
}
ul {
  list-style: none;
  padding: 0;
}
li {
  padding: 10px 0;
  cursor: pointer;
}
li:hover {
  background: #e9ecef;
}
</style>
