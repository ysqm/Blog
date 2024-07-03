<template>
  <div>
    <h1>Edit Article</h1>
    <ArticleEditor :articleId="$route.params.articleId" />
  </div>
</template>

<script>
import ArticleEditor from '../components/ArticleEditor.vue';

export default {
  components: {
    ArticleEditor,
  },
};
</script>
