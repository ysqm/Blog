<template>
  <div id="top">
    <header>
      <div class="container">
        <h1>郭叶盛的演示博客</h1>
        <nav>
          <router-link to="/self-home">个人首页</router-link>
          <router-link to="/self-category">分类</router-link>
          <router-link to="/self-tag">标签</router-link>
          <router-link to="/self-archive">归档</router-link>
          <router-link to="/self-knowledge-base">知识库</router-link>
        </nav>
        <input type="text" placeholder="搜索文章" />
      </div>
    </header>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'Top'
};
</script>


<style>
@import '/src/style/self-style/global.css';

#top {
  width: 100%;
  background-color: #f8f9fa;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

header {
  width: 100%;
  background-color: #ebf5ff; /* 设置为淡蓝色 */
}

.container {
  max-width: 1200px; /* 设置一个合适的最大宽度 */
  margin: 0 auto; /* 使容器居中 */
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 0; /* 设置内边距 */
}

header h1 {
  font-size: 24px;
  margin: 0;
  color: #333;
}

header nav {
  display: flex;
  align-items: center;
}

header nav a {
  margin: 0 10px;
  text-decoration: none;
  color: #333;
  font-size: 16px;
}

header nav a.router-link-exact-active {
  font-weight: bold;
  color: #42b983;
}

header input {
  padding: 5px 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 14px;
}
</style>
