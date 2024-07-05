<template>
  <div>
    <Navbar />
    <Top />
    <h2>知识库</h2>
    <p>这是知识库页面的内容。</p>
  </div>
</template>

<script>
import Top from "@/components/self-Top.vue";
import Navbar from "@/components/Navbar.vue";

export default {
  name: 'KnowledgeBase',
  components: {Navbar, Top}
};
</script>

<style>
/* 添加一些基本样式 */
</style>
