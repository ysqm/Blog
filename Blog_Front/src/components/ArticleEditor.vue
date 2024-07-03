<template>
  <div style="width:100%;height:100%;position: relative">
    <h1><input type="text" v-model="title" placeholder="请输入标题" /></h1>
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
      'both', 'preview' , '|', 'undo','redo', '|', 'fullscreen','outline','export'
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
.article-editor-container {
  display: flex;
  flex-direction: column;
  height: 100%;
  background-color: #f8f8f8;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.toolbar {
  display: flex;
  flex-direction: column;
  padding: 24px;
  background-color: #ffffff;
  border-bottom: 1px solid #e0e0e0;
  border-radius: 8px 8px 0 0;
}

.title-container,
.tags-container,
.status-container {
  display: flex;
  align-items: center;
  gap: 12px;
}

.title-input {
  flex-grow: 1;
  padding: 8px 12px;
  border: 1px solid #e0e0e0;
  border-radius: 4px;
  font-size: 16px;
}

.tag-list,
.status-options {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.tag-item,
.status-item {
  display: flex;
  align-items: center;
  gap: 5px;
}

.tag-checkbox,
.status-radio {
  width: 16px;
  height: 16px;
}

.tag-name,
.status-label {
  font-size: 14px;
  color: #333333;
}

.custom-tag-input {
  padding: 6px 10px;
  border: 1px solid #e0e0e0;
  border-radius: 4px;
  font-size: 14px;
}

.edit {
  display: flex;
  align-items: center;
  gap: 14px;
  padding: 24px;
  background-color: #ffffff;
  border-radius: 0 0 8px 8px;
}

.edit h3{
  margin-right:24px;
}
.editor {
  flex-grow: 1;
  text-align: left;

}

.save-button-container {
  padding: 16px 24px;
  text-align: right;
}

.save-button {
  padding: 8px 16px;
  background-color: #1890ff;
  color: #ffffff;
  border: none;
  border-radius: 4px;
  font-size: 14px;
  cursor: pointer;
}

.error-message {
  color: #ff4d4f;
  margin-top: 10px;
  font-size: 14px;
}
</style>
