<template>
  <div style="width:100%;height:100%;position: relative">
    <input type="text" v-model="title" placeholder="Enter title" />
    <div id="vditor"></div>
    <div>
      <h3>Select Tags</h3>
      <div v-for="tag in availableTags" :key="tag.tagId">
        <label>
          <input type="checkbox" :value="tag.tagId" v-model="selectedTagIds" /> {{ tag.tagName }}
        </label>
      </div>
      <input type="text" v-model="customTag" placeholder="Add custom tag" @keyup.enter="addCustomTag" />
    </div>
    <button @click="saveDraft">Save Draft</button>
    <button @click="publishArticle">Publish</button>
    <p v-if="error">{{ error }}</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import Vditor from 'vditor';
import 'vditor/dist/index.css';
import axios from 'axios';

const vditor = ref(null);
const title = ref('');
const userId = 1; // 你可以根据实际情况动态获取用户ID
const availableTags = ref([]);
const selectedTagIds = ref([]);
const customTag = ref('');
const error = ref('');

const loadTags = () => {
  axios.get('/tag/all')
      .then(response => {
        availableTags.value = response.data.data;
      })
      .catch(err => {
        console.error('Error loading tags:', err);
      });
};

const addCustomTag = () => {
  if (customTag.value.trim() !== '') {
    axios.post('/tag/create', { tagName: customTag.value })
        .then(response => {
          availableTags.value.push(response.data.data);
          selectedTagIds.value.push(response.data.data.tagId);
          customTag.value = '';
        })
        .catch(err => {
          console.error('Error creating custom tag:', err);
        });
  }
};

onMounted(() => {
  vditor.value = new Vditor('vditor', {
    height: '50vh',
    width: '100%',
    toolbar: [
      'emoji', 'headings', 'bold', 'italic', 'strike', '|', 'list', 'ordered-list', 'check',
      'quote', 'code', 'inline-code', 'link', 'upload', 'table', '|', 'record', 'edit-mode',
      'both', 'preview', '|', 'undo', 'redo', '|', 'fullscreen', 'outline', 'export'
    ],
    after() {
      vditor.value.setValue('');
    }
  });
  loadTags();
});

const formatFileName = (title) => {
  return title.replace(/[^a-zA-Z0-9]/g, '_') + '.md';
};

const saveDraft = () => {
  const content = vditor.value.getValue();
  const formData = new FormData();
  formData.append('userId', userId);
  formData.append('title', title.value);
  formData.append('status', 'draft');
  const fileName = formatFileName(title.value);
  const blob = new Blob([content], {type: 'text/markdown'});
  formData.append('file', blob, fileName);
  formData.append('tagIds', selectedTagIds.value);

  axios.post('/articles/create', formData)
      .then(response => {
        console.log('Draft saved:', response.data);
        error.value = '';
      })
      .catch(err => {
        console.error('Error saving draft:', err);
        error.value = 'Error saving draft: ' + (err.response?.data?.message || err.message);
      });
};

const publishArticle = () => {
  const content = vditor.value.getValue();
  const formData = new FormData();
  formData.append('userId', userId);
  formData.append('title', title.value);
  formData.append('status', 'published');
  const fileName = formatFileName(title.value);
  const blob = new Blob([content], {type: 'text/markdown'});
  formData.append('file', blob, fileName);
  formData.append('tagIds', selectedTagIds.value);

  axios.post('/articles/create', formData)
      .then(response => {
        console.log('Article published:', response.data);
        error.value = '';
      })
      .catch(err => {
        console.error('Error publishing article:', err);
        error.value = 'Error publishing article: ' + (err.response?.data?.message || err.message);
      });
};
</script>

<style scoped>
#vditor {
  margin-bottom: 20px;
  text-align: left;
}

input[type="text"] {
  font-size: 24px;
  font-weight: bold;
  padding: 10px;
  margin-bottom: 20px;
  box-sizing: border-box;
  width: 100%;
}

button {
  margin-right: 10px;
}

p {
  color: red;
}
</style>