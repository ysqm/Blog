<template>
  <div class="sidebar">
    <UserProfile />
    <div class="categories">
      <h3>分类</h3>
      <ul>
        <li>Java</li>
        <li>前端</li>
        <li>Postman</li>
        <li>Spring Boot</li>
      </ul>
    </div>
  </div>
</template>

<script>
import UserProfile from './self-UserProfile.vue';

export default {
  name: 'Sidebar',
  components: {
    UserProfile
  }
};
</script>

<style>
.sidebar {
  width: 250px; /* 固定 Sidebar 的宽度 */
  margin-left: 20px; /* Sidebar 与主要内容之间的间距 */
}

.sidebar .categories {
  background-color: #fff; /* 分类部分背景色 */
  padding: 20px; /* 分类部分内边距 */
  border: 1px solid #ddd; /* 分类部分边框 */
  border-radius: 8px; /* 分类部分圆角 */
}

.sidebar .categories h3 {
  margin: 0 0 10px 0; /* 标题与列表之间的间距 */
  font-size: 16px; /* 标题字体大小 */
}

.sidebar .categories ul {
  list-style: none; /* 列表无样式 */
  padding: 0; /* 列表内边距 */
  margin: 0; /* 列表外边距 */
}

.sidebar .categories ul li {
  padding: 5px 0; /* 列表项内边距 */
  font-size: 14px; /* 列表项字体大小 */
  color: #333; /* 列表项文字颜色 */
}
</style>
