<template>
  <div class="post-detail">
    <Navbar />
    <Top />
    <h2>文章标题</h2>
    <p>这是一个简单的文章内容页面。</p>
  </div>
</template>

<script>
import Top from "@/components/self-Top.vue";
import Navbar from "@/components/Navbar.vue";

export default {
  name: 'PostDetail',
  components: {Navbar, Top}
};
</script>

<!--<style scoped>-->
<!--.post-detail {-->
<!--  padding: 20px;-->
<!--  max-width: 800px;-->
<!--  margin: 0 auto;-->
<!--}-->

<!--.post-detail h2 {-->
<!--  font-size: 28px;-->
<!--  margin-bottom: 10px;-->
<!--}-->

<!--.post-detail p {-->
<!--  font-size: 16px;-->
<!--  line-height: 1.6;-->
<!--}-->
<!--</style>-->
