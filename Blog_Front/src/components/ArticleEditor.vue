<template>
  <div>
    <input v-model="title" placeholder="Title" />
    <select v-model="status">
      <option value="draft">Draft</option>
      <option value="published">Published</option>
    </select>
    <div id="editor"></div>
    <button @click="saveArticle">Save</button>
  </div>
</template>

<script>
import { createArticle, updateArticle, getArticleById } from '../api/article';
import { mapState } from 'vuex';

export default {
  props: {
    articleId: {
      type: Number,
      default: null,
    },
  },
  data() {
    return {
      title: '',
      status: 'draft',
      editor: null,
    };
  },
  computed: {
    ...mapState(['userId']),
  },
  mounted() {
    this.initEditor();
    if (this.articleId) {
      this.loadArticle();
    }
  },
  methods: {
    initEditor() {
      this.editor = editormd("editor", {
        width: "90%",
        height: 640,
        path: "/editor.md/lib/",
        saveHTMLToTextarea: true,
      });
    },
    loadArticle() {
      getArticleById(this.articleId).then(response => {
        const article = response.data.data;
        this.title = article.title;
        this.status = article.status;
        this.editor.setMarkdown(article.content);
      });
    },
    saveArticle() {
      const file = new Blob([this.editor.getMarkdown()], { type: 'text/markdown' });
      if (this.articleId) {
        updateArticle(this.articleId, this.title, this.status, file).then(() => {
          this.$router.push('/my-articles');
        });
      } else {
        createArticle(this.userId, this.title, this.status, file).then(() => {
          this.$router.push('/my-articles');
        });
      }
    },
  },
};
</script>
