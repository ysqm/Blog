<template>
  <nav class="navbar">
    <div class="logo">BLOG</div>
    <ul class="nav-links">
      <li><a href="#">分类</a></li>
      <li><a href="#">精华</a></li>
      <li><a href="#">博问</a></li>
      <li><a href="#">更多</a></li>
    </ul>
    <div class="search">
      <input type="text" placeholder="搜索" />
      <button class="search-btn">
        <SearchIcon />
      </button>
    </div>
    <button class="editor-icon">
      <IconPen />
    </button>
    <div class="auth-buttons">
      <button>登录</button>
      <button>注册</button>
    </div>
  </nav>
</template>

<script>
import SearchIcon from "@/components/icons/IconSearch.vue";
import IconPen from "@/components/icons/IconPen.vue";

export default {
  name: 'Navbar',
  components: { IconPen, SearchIcon }
}
</script>

<style scoped>
.navbar {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  display: flex;
  align-items: center;
  padding: 10px 20px;
  justify-content: space-between;
  background-color: #2c3e50;
  z-index: 1000; /* 确保导航栏在顶部 */
}

.logo {
  font-size: 24px;
  font-weight: bold;
  color: #fff;
}

.nav-links {
  display: flex;
  list-style: none;
  margin: 0;
  padding: 0;
}

.nav-links li {
  margin: 0 10px;
}

.nav-links a {
  text-decoration: none;
  color: #fff;
}

.search {
  display: flex;
  align-items: center;
}

.search input {
  padding: 5px;
  margin-right: 5px;
  border-radius: 4px;
  border: 1px solid #ccc;
}

.search button {
  padding: 5px 5px;
  border: none;
  background-color: #fff;
  border-radius: 4px;
  cursor: pointer;
}

.editor-icon {
  margin-left: 5px;
}

.auth-buttons {
  display: flex;
  margin-left: 5px;
}

.auth-buttons button {
  margin-right: 25px;
  padding: 5px 5px;
  border: none;
  background-color: #fff;
  border-radius: 4px;
  cursor: pointer;
}
</style>
