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
  flex: 1;
  margin-left: 20px;
}

.sidebar .categories {
  background-color: #fff;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
}

.sidebar .categories h3 {
  margin: 0 0 10px 0;
  font-size: 16px;
}

.sidebar .categories ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.sidebar .categories ul li {
  padding: 5px 0;
  font-size: 14px;
  color: #333;
}
</style>
