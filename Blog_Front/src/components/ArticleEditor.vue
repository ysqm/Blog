<template>
  <div class="article-editor-container">
    <div class="toolbar">
      <div class="title-container">
        <h3>文章标题</h3>
        <input type="text" v-model="title" placeholder="输入标题" class="title-input" />
      </div>

      <div class="tags-container">
        <h3>文章标签</h3>
        <div class="tag-list">
          <div v-for="tag in availableTags" :key="tag.tagId" class="tag-item">
            <input type="checkbox" :value="tag.tagId" v-model="selectedTagIds" class="tag-checkbox" />
            <span class="tag-name">{{ tag.tagName }}</span>
          </div>
          <div class="tag-item">
            <input type="text" v-model="customTag" placeholder="添加自定义标签" @keyup.enter="addCustomTag" class="custom-tag-input" />
          </div>
        </div>
      </div>

      <div class="status-container">
        <h3>文章状态</h3>
        <div class="status-options">
          <div class="status-item">
            <input type="radio" name="status" value="draft" v-model="status" class="status-radio" />
            <span class="status-label">草稿</span>
          </div>
          <div class="status-item">
            <input type="radio" name="status" value="published" v-model="status" class="status-radio" />
            <span class="status-label">发布</span>
          </div>
          <div class="status-item">
            <input type="radio" name="status" value="hidden" v-model="status" class="status-radio" />
            <span class="status-label">隐藏</span>
          </div>
        </div>
      </div>
    </div>

    <div class="edit">
      <h3>文章内容</h3>
      <div id="vditor" class="editor"></div>
    </div>

    <div class="save-button-container">
      <button @click="saveArticle" class="save-button">保存</button>
    </div>

    <p v-if="error" class="error-message">{{ error }}</p>
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
const status = ref('draft');

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

const saveArticle = () => {
  const content = vditor.value.getValue();
  const formData = new FormData();
  formData.append('userId', userId);
  formData.append('title', title.value);
  formData.append('status', status.value);
  const fileName = formatFileName(title.value);
  const blob = new Blob([content], { type: 'text/markdown' });
  formData.append('file', blob, fileName);
  formData.append('tagIds', selectedTagIds.value);

  axios.post('/articles/create', formData)
      .then(response => {
        console.log('Article saved:', response.data);
        error.value = '';
      })
      .catch(err => {
        console.error('Error saving article:', err);
        error.value = 'Error saving article: ' + (err.response?.data?.message || err.message);
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
  margin-right: 150px;
  margin-left: 150px;
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
  white-space: nowrap;
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