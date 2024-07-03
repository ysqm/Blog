<template>
  <Navbar />
  <Top />
  <div>
    <h2>标签</h2>
    <p>这是标签页面的内容。</p>
  </div>
</template>

<script>
import Top from "@/components/self-Top.vue";
import Navbar from "@/components/Navbar.vue";

export default {
  name: 'Tag',
  components: {Navbar, Top}
};
</script>

<style>
/* 添加一些基本样式 */
</style>
