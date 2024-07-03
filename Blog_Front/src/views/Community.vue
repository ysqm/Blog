<template>
  <div id="community">
    <Navbar />
    <div class="main-content">
      <Sidebar />
      <div class="article-container">
        <ArticleList />
      </div>
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
  name: 'Community',
  components: {
    Navbar,
    Sidebar,
    ArticleList,
    RightSidebar
  }
}
</script>

<style>
#community {
  display: flex;
  flex-direction: column;
  height: 100vh;
  background-color: #F0F8FF;
}

.main-content {
  display: flex;
  flex: 1;
  margin-top: 50px; /* 留出导航栏的高度 */
}

.article-container {
  flex: 1;
  overflow-y: auto;
  padding: 20px;
}

article {
  margin-bottom: 20px;
}
</style>
