<template>
  <nav>
    <div class="navbar">
      <div class="logo">BLOG</div>
      <ul class="nav-links">
        <li><a href="#">分类</a></li>
        <li><a href="#">精华</a></li>
        <li><a href="#">博问</a></li>
        <li><a href="#">更多</a></li>
      </ul>
      <div class="search-login">
        <input type="text" placeholder="搜索">
        <button class="search-btn">
          <SearchIcon />
        </button>
        <button>登录</button>
        <button>注册</button>
      </div>
    </div>
  </nav>
</template>

<script>
import SearchIcon from "@/components/icons/IconSearch.vue";

export default {
  name: 'Navbar',
  components: {SearchIcon}
}
</script>

<style scoped>
.navbar {
  top: 0;
  left: 0;
  width: 100%;;
  display: flex;
  align-items: center;
  padding: 10px 20px;
  position: fixed;
  justify-content: space-between;
  background-color: #2c3e50;
}
.logo {
  font-size: 24px;
  font-weight: bold;
  color: #fff;
}
.nav-links {
  display: flex;
  list-style: none;
}
.nav-links li {
  margin: 0 10px;

}
.nav-links a {
  text-decoration: none;
  color: #fff;
}
.search-login {
  display: flex;
  align-items: center;
}
.search-login input {
  padding: 5px;
  margin-right: 10px;
}
.search-login button {
  margin-left: 5px;
  padding: 5px 10px;
}
</style>
