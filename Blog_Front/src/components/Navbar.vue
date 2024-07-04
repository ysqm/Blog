<template xmlns="">
  <nav class="navbar">
    <router-link to="/" class="link"><div class="logo">WeBlog</div></router-link>
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
      <router-link to="/Editor"><IconPen /></router-link>
    </button>
    <div v-if="isLogged" class="">
      <LoginAndRegister />
    </div>
    <div v-else class="avatarSetting">
      <SelfCenterEntry />
    </div>
  </nav>
</template>

<script>
import SearchIcon from "@/components/icons/IconSearch.vue";
import IconPen from "@/components/icons/IconPen.vue";
import LoginAndRegister from "@/components/LoginAndRegister.vue";
import SelfCenterEntry from "@/components/SelfCenterEntry.vue";

export default {
  name: 'Navbar',
  components: {LoginAndRegister, IconPen, SearchIcon , SelfCenterEntry},
  data(){
    return{
      isLogged: false
    }
  }
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
  background-color: #1E90FF;
  z-index: 1000; /* 确保导航栏在顶部 */
}
.link{
  text-decoration: none;
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
  background-color: #84FAD1FF;
  border-radius: 4px;
  cursor: pointer;
}

.editor-icon {
  margin-left: 5px;
  background-color: #84FAD1FF;
  border: none;
  border-radius: 4px;
}

.auth-buttons button {
  margin-right: 25px;
  padding: 5px 5px;
  border: none;
  color: #FFFFFF;
  background-color: #84fad1;
  border-radius: 4px;
  cursor: pointer;
  text-decoration: none;
}
.avatarSetting{
  display: flex;
  align-items: center;
  width: 50px;
  margin-right: 40px;
}
</style>
