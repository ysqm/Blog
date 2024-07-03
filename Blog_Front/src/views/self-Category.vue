<template>
  <div>
    <Navbar />
    <Top />
    <h2>分类</h2>
    <p>这是分类页面的内容。</p>
  </div>
</template>

<script>
import Top from "@/components/self-Top.vue";
import Navbar from "@/components/Navbar.vue";

export default {
  name: 'Category',
  components: {Navbar, Top}
};
</script>

<style>
/* 添加一些基本样式 */
</style>
