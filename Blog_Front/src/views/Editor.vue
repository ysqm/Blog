<template>
    <ArticleEditor :articleId="$route.params.articleId" />
</template>

<script>
import ArticleEditor from '../components/ArticleEditor.vue';

export default {
  components: {
    ArticleEditor,
  },
};
</script>
