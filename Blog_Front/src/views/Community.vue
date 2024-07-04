<template>
  <div id="community">
    <Navbar />
    <div class="main-content">
      <Sidebar />
      <div class="article-container">
        <PostList />
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
import PostList from "@/components/self-PostList.vue";

export default {
  name: 'Community',
  components: {
    PostList,
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

.sidebar {
  flex: 0 0 250px; /* 固定侧边栏宽度 */
  padding: 20px;
  box-sizing: border-box;
}

.article-container {
  flex: 0.93; /* 中间内容区域占据剩余空间 */
  overflow-y: auto;
  padding: 20px;
  box-sizing: border-box;
}

.right-sidebar {
  flex: 0 0 250px; /* 固定侧边栏宽度 */
  padding: 20px;
  box-sizing: border-box;
}

.post-list {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  margin-top: 20px; /* 与上方组件的间距 */
}

@media (min-width: 768px) {
  .post-list {
    justify-content: space-between;
  }
}

article {
  margin-bottom: 20px;
}
</style>
