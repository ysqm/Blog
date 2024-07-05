<template>
  <div class="user-profile">
    <h2>基本信息</h2>
    <div class="info-item">
      <span>ID号：</span>
      <span>3114109</span>
    </div>
    <div class="info-item">
      <span>积分：</span>
      <span>200</span>
    </div>
    <div class="info-item">
      <span>创建时间：</span>
      <span>2023-02-23</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'UserProfile'
}
</script>

<style scoped>
.user-profile {
  padding: 20px;
}
.info-item {
  margin-bottom: 10px;
  display: flex;
  justify-content: space-between;
  text-align: left;
  padding-left: 60px;
}
</style>
