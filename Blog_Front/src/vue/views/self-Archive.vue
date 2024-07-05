<template>
  <div>
    <Navbar />
    <Top />
    <h2>归档</h2>
    <p>这是归档页面的内容。</p>
  </div>
</template>

<script>
import Top from "@/components/self-Top.vue";
import Navbar from "@/components/Navbar.vue";

export default {
  name: 'Archive',
  components: {Navbar, Top}
};
</script>

<style>
/* 添加一些基本样式 */
</style>
