<template>
  <div class="user-security">
    <h2>账号与安全</h2>
    <div class="info-item">
      <span>头像</span>
      <img src="../../public/avatar.jpg" class="avatar"/>
    </div>
    <div class="info-item">
      <span>登录用户名</span>
      <span>不会取名的人</span>
    </div>
    <div class="info-item">
      <span>密码</span>
      <span>*********</span>
    </div>
    <div class="info-item">
      <span>显示昵称</span>
      <span>不会取名的人</span>
    </div>
    <div class="info-item">
      <span>邮箱</span>
      <span>3089748482@qq.com</span>
    </div>
    <div class="info-item">
      <span>手机号</span>
      <span>+86 19979219353</span>
    </div>
    <div class="info-item">
      <span>两步验证</span>
      <span>关闭</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'UserSecurity'
}
</script>

<style scoped>
.user-security {
  padding: 20px;
}
.info-item {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}
.avatar {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}
</style>
