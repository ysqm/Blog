<template>
  <div class="user-profile">
    <!-- Section title -->
    <h2>基本信息</h2>

    <!-- User ID info item -->
    <div class="info-item">
      <span>ID号：</span>
      <span>3114109</span>
    </div>

    <!-- User points info item -->
    <div class="info-item">
      <span>积分：</span>
      <span>200</span>
    </div>

    <!-- User creation date info item -->
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
/* User profile section styling */
.user-profile {
  padding: 20px;
}

/* Individual info item styling */
.info-item {
  margin-bottom: 10px;
  display: flex;
  justify-content: space-between;
  text-align: left;
  padding-left: 60px;
}
</style>
