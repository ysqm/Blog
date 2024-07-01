<template>
  <div style="width:100%;height:100%;position: relative">
    <input type="text" v-model="title" placeholder="Enter title" />
    <div id="vditor"></div>
    <button @click="saveDraft">Save Draft</button>
    <button @click="publishArticle">Publish</button>
  </div>
</template>

<script setup>
import {ref, onMounted} from 'vue';
import Vditor from 'vditor';
import 'vditor/dist/index.css';
import axios from 'axios';

const vditor = ref(null);
const title = ref('');
const userId = 1; // 你可以根据实际情况动态获取用户ID
const tagIds = ref([1, 2]); // 示例标签ID数组，根据需要调整

onMounted(() => {
  vditor.value = new Vditor('vditor', {
    // height: '50vh',
    // width: '50vw',
    toolbar: [
      'emoji', 'headings', 'bold', 'italic', 'strike', '|', 'list', 'ordered-list', 'check',
      'quote', 'code', 'inline-code', 'link', 'upload', 'table', '|',  'record', 'edit-mode',
      'both', 'preview' , '|', 'undo','redo', '|', 'fullscreen'
    ],
    after() {
      vditor.value.setValue('');
    }
  });
});

const saveDraft = () => {
  const content = vditor.value.getValue();
  const formData = new FormData();
  formData.append('userId', userId);
  formData.append('title', title.value);
  formData.append('status', 'draft');
  formData.append('file', new Blob([content], {type: 'text/markdown'}));
  formData.append('tagIds', tagIds.value);

  axios.post('/articles/create', formData)
      .then(response => {
        console.log('Draft saved:', response.data);
      })
      .catch(error => {
        console.error('Error saving draft:', error);
      });
};

const publishArticle = () => {
  const content = vditor.value.getValue();
  const formData = new FormData();
  formData.append('userId', userId);
  formData.append('title', title.value);
  formData.append('status', 'published');
  formData.append('file', new Blob([content], {type: 'text/markdown'}));
  formData.append('tagIds', tagIds.value);

  axios.post('/articles/create', formData)
      .then(response => {
        console.log('Article published:', response.data);
      })
      .catch(error => {
        console.error('Error publishing article:', error);
      });
};
</script>

<style scoped>
#vditor {
  margin-bottom: 20px;
}

input[type="text"] {
  width: 100%;
  padding: 10px;
  margin-bottom: 20px;
  box-sizing: border-box;
}
</style>
