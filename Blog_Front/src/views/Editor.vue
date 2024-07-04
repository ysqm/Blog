<template>
  <div id="editor">
    <Navbar />
    <ArticleEditor :articleId="$route.params.articleId" />
  </div>
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
