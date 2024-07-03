<template>
  <div class="article-editor-container">
    <el-row :gutter="20">
      <el-col :span="24">
        <el-card>
          <el-form>
            <el-form-item label="文章标题">
              <el-input v-model="title" placeholder="输入标题" />
            </el-form-item>

            <el-form-item label="文章标签">
              <el-checkbox-group v-model="selectedTagIds">
                <el-checkbox
                    v-for="tag in availableTags"
                    :key="tag.tagId"
                    :label="tag.tagId"
                >{{ tag.tagName }}</el-checkbox>
              </el-checkbox-group>
              <el-input
                  v-model="customTag"
                  placeholder="添加自定义标签"
                  @keyup.enter="addCustomTag"
                  style="margin-top: 10px;"
              />
            </el-form-item>

            <el-form-item label="文章状态">
              <el-radio-group v-model="status">
                <el-radio label="draft">草稿</el-radio>
                <el-radio label="published">发布</el-radio>
                <el-radio label="hidden">隐藏</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>

      <el-col :span="24">
        <el-card>
          <h3>文章内容</h3>
          <div id="vditor" class="editor"></div>
        </el-card>
      </el-col>

      <el-col :span="24" class="save-button-container">
        <el-button type="primary" @click="saveArticle">保存</el-button>
      </el-col>
    </el-row>

    <el-alert
        v-if="error"
        title="Error"
        type="error"
        :description="error"
        show-icon
        style="margin-top: 20px;"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import Vditor from 'vditor';
import 'vditor/dist/index.css';
import axios from 'axios';
import { ElMessage } from 'element-plus';

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

.editor {
  flex-grow: 1;
  text-align: left;
}

.save-button-container {
  text-align: right;
  margin-top: 20px;
}
</style>