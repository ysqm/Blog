<template>
  <div>
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
