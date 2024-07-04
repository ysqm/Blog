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
  width: 10px;
  background: #f8f9fa;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: left;
  padding: 20px;
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
