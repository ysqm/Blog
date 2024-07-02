<template>
  <div class="login-container">
    <div class="login-box">
      <h2>用户登录</h2>
      <div class="input-group">
        <label for="username">用户名:</label>
        <input type="text" id="username" v-model="username" required>
      </div>
      <div class="input-group">
        <label for="password">密码:</label>
        <input type="password" id="password" v-model="password" required>
      </div>
      <div class="checkbox-group">
        <input type="checkbox" id="remember" v-model="remember">
        <label for="remember">记住我</label>
      </div>
      <button @click="login">登录</button>
      <div class="third-party-login">
        <p>第三方登录:</p>
        <div class="icons">
          <a href="#"><i class="icon-wechat"></i></a>
          <a href="#"><i class="icon-qq"></i></a>
          <a href="#"><i class="icon-github"></i></a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      username: '',
      password: '',
      remember: false
    }
  },
  methods: {
    login() {
      // 执行登录逻辑
      console.log(`Username: ${this.username}, Password: ${this.password}, Remember: ${this.remember}`);
    }
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f0f0;
}

.login-box {
  background-color: white;
  padding: 30px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.input-group {
  margin-bottom: 20px;
  text-align: left;
}

.checkbox-group {
  margin-bottom: 20px;
  text-align: left;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.third-party-login {
  margin-top: 20px;
}

.icons a {
  margin: 0 10px;
  color: #333;
}
</style>