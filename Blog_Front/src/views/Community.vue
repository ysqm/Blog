<template>
  <div id="app">
    <Navbar />
    <div class="main-content">
      <Sidebar />
      <ArticleList />
      <RightSidebar />
    </div>
  </div>
</template>

<script>
import Navbar from '../components/Navbar.vue'
import Sidebar from '../components/Sidebar.vue'
import ArticleList from '../components/ArticleList.vue'
import RightSidebar from '../components/RightSidebar.vue'

export default {
  name: 'App',
  components: {
    Navbar,
    Sidebar,
    ArticleList,
    RightSidebar
  }
}
</script>

<style>
#app {
  display: flex;
  flex-direction: column;
  height: 100vh;
  background-repeat: no-repeat; /* 确保背景图片不会重复 */
  background-color:#E594CB;
}

.main-content {
  display: flex;
  flex: 1;
  padding: 20px;
  gap: 20px;
}
</style>
