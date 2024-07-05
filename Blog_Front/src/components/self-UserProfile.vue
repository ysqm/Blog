<template>
  <div class="user-profile">
    <img src="../../public/profile.png" alt="Profile" class="profile-img" />
    <h2>用户1</h2>
    <p>工程实训</p>
    <div class="stats">
      <div>
        <strong>2</strong> 文章
      </div>
      <div>
        <strong>4</strong> 分类
      </div>
      <div>
        <strong>0</strong> 标签
      </div>
      <div>
        <strong>11</strong> 总访问量
      </div>
    </div>

    <div class="social-links">
      <a href="https://github.com/"><img src="/src/assets/images/github.png" alt="GitHub" class="social-icon" /></a>
      <a href="https://www.google.com/"><img src="/src/assets/images/google.png" alt="Google" class="social-icon" /></a>
      <a href="https://www.zhihu.com/"><img src="/src/assets/images/zhihu.png" alt="Zhihu" class="social-icon" /></a>
      <a href="https://www.csdn.net/"><img src="/src/assets/images/CSDN.png" alt="CSDN" class="social-icon" /></a>
      <a href="https://stackoverflow.com/"><img src="/src/assets/images/SOF.png" alt="SOF" class="social-icon" /></a>
    </div>
  </div>
</template>

<script>
export default {
  name: 'UserProfile'
};
</script>

<style>
.user-profile {
  background-color: #fff;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  text-align: center;
}

.user-profile .profile-img {
  width: 100px;
  height: 100px;
  border-radius: 50%;
}

.user-profile h2 {
  font-size: 20px;
  margin: 10px 0;
}

.user-profile p {
  font-size: 14px;
  color: #666;
}

.user-profile .stats {
  font-size: 13px;
  display: flex;
  justify-content: space-around;
  margin: 20px 0;
}

.user-profile .social-links {
  display: flex;
  justify-content: center;
  gap: 10px;
}

.user-profile .social-icon {
  width: 30px;
  height: 30px;
}
</style>
