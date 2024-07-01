<template>
  <div id="editor">
    <h2>Article Editor</h2>
    <input v-model="title" placeholder="Title">
    <div id="md-editor"></div>
    <button @click="saveArticle">Save Article</button>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import "editor.md/css/editormd.css";
import editormd from "/editor.md";
import api from '@/api';

export default {
  setup() {
    const title = ref('');
    const fileContent = ref('');

    onMounted(() => {
      initializeEditor();
    });

    const initializeEditor = () => {
      editormd("md-editor", {
        width: "90%",
        height: 640,
        path: "/node_modules/editor.md/lib/",
        saveHTMLToTextarea: true,
        onchange: function() {
          fileContent.value = this.getMarkdown();
        },
      });
    };

    const saveArticle = () => {
      const formData = new FormData();
      const blob = new Blob([fileContent.value], { type: 'text/markdown' });
      formData.append('userId', 1); // 示例用户ID
      formData.append('title', title.value);
      formData.append('status', 'draft');
      formData.append('file', blob, 'article.md');

      api.post('/articles/create', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      })
          .then(response => {
            console.log('Article saved:', response.data);
          })
          .catch(error => {
            console.error('Error saving article:', error);
          });
    };

    return {
      title,
      saveArticle,
    };
  },
};
</script>

<style>
#md-editor {
  margin-top: 20px;
}
</style>
