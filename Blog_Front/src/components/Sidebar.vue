<template>
  <div class="sidebar">
    <ul>
      <li><a href="#">订阅</a></li>
      <li><a href="#">关注</a></li>
      <li><a href="#">我评</a></li>
      <li><a href="#">我赞</a></li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'Sidebar'
}
</script>

<style scoped>
.sidebar {
  position: fixed;
  top: 60px; /* Adjust based on the height of the navbar */
  left: 0;
  width: 100px;
  background-color: #f0f0f0;
  padding: 20px 0;
  height: calc(100vh - 60px); /* Adjust based on the height of the navbar */
  box-shadow: 2px 0 5px rgba(0,0,0,0.1);
  color: #76DAC0;
}
.sidebar ul {
  list-style: none;
  text-align: center;
  padding: 0;
}
.sidebar ul li {
  margin: 20px 0;
  border-bottom: 1px solid #ddd;
}
.sidebar ul li a {
  text-decoration: none;
  color: #333;
}
</style>