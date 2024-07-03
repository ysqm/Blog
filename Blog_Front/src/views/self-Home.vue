<template>
  <div class="home">
    <Top />
    <Announcement />
    <div class="main-content">
      <PostList />
      <Sidebar />
    </div>
  </div>
</template>

<script>
import Announcement from '../components/self-Announcement.vue';
import PostList from '../components/self-PostList.vue';
import Sidebar from '../components/self-Sidebar.vue';
import Top from '../components/self-Top.vue';

export default {
  name: 'Home',
  components: {
    Announcement,
    PostList,
    Sidebar,
    Top
  }
};
</script>

<style>
.home {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.main-content {
  display: flex;
  width: 100%;
  max-width: 1200px;
  margin: 20px auto;
}
</style>
