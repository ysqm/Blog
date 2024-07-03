<template>
  <div>
    <Navbar />
    <ArticleEditor :articleId="$route.params.articleId" />
</template>

<script>
import ArticleEditor from '../components/ArticleEditor.vue';
import Navbar from "@/components/Navbar.vue";

export default {
  components: {
    Navbar,
    ArticleEditor,
  },
};
</script>
