<template>
  <div class="user-profile">
    <h2>基本信息</h2>
    <div class="info-item">
      <span>园号：</span><span>3114109</span>
    </div>
    <div class="info-item">
      <span>园豆：</span><span>200</span>
    </div>
    <div class="info-item">
      <span>入园时间：</span><span>2023-02-23</span>
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
}
</style>
