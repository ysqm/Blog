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
import { ElMessage } from 'element-plus';
import { getAllTags, createTag } from '@/api/tag.ts';
import { createArticle } from '@/api/article.ts';
import { useStore } from 'vuex' // 引入useStore 方法
const store = useStore()  // 该方法用于返回store 实例
import axios from 'axios'
const vditor = ref(null);
const title = ref('');
const userId = localStorage.getItem('uid'); // 使用 Vuex 中存储的用户 ID
const availableTags = ref([]);
const selectedTagIds = ref([]);
const customTag = ref('');
const error = ref('');
const status = ref('draft');

const loadTags = () => {
  getAllTags()
      .then(response => {
        availableTags.value = response.data.data.filter(tag =>
            tag.status === 'approved' || tag.status === 'official'
        );
      })
      .catch(err => {
        console.error('Error loading tags:', err);
        if (err.response) {
          console.error('Request Headers:', err.config.headers);
          console.error('Response Headers:', err.response.headers);
          console.error('Response Data:', err.response.data);
        } else {
          console.error('Error Message:', err.message);
        }
      });
};

const addCustomTag = () => {
  return new Promise((resolve, reject) => {
    if (customTag.value.trim() !== '') {
      createTag(customTag.value)
          .then(response => {
            const newTag = response.data.data;
            availableTags.value.push(newTag);
            selectedTagIds.value.push(newTag.tagId);
            customTag.value = '';
            resolve();
          })
          .catch(err => {
            console.error('Error creating custom tag:', err);
            reject(err);
          });
    } else {
      resolve();
    }
  });
};

onMounted(() => {
  console.log(store)
  console.log('Token in store on mount:', store.state.token);
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

const saveArticle = () => {
  addCustomTag().then(() => {
    const content = vditor.value.getValue();
    const fileName = formatFileName(title.value);
    const blob = new Blob([content], { type: 'text/markdown' });

    // 读取文件内容并转换为Base64
    const reader = new FileReader();
    reader.onload = () => {
      const base64Content = reader.result.split(',')[1];

      const articleDTO = {
        userId: parseInt(localStorage.getItem('uid'), 10),
        title: title.value,
        content: base64Content,
        status: status.value,
        tagIds: selectedTagIds.value
      };

      createArticle(articleDTO)
          .then(response => {
            console.log('Article saved:', response.data);
            error.value = '';
            ElMessage.success('Article saved successfully');
          })
          .catch(err => {
            console.error('Error saving article:', err);
            error.value = 'Error saving article: ' + (err.response?.data?.message || err.message);
            ElMessage.error('Error saving article: ' + (err.response?.data?.message || err.message));
          });
    };

    reader.readAsDataURL(blob);
  }).catch(err => {
    console.error('Error adding custom tag:', err);
    error.value = 'Error adding custom tag: ' + (err.response?.data?.message || err.message);
    ElMessage.error('Error adding custom tag: ' + (err.response?.data?.message || err.message));
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
  margin-top: 0;
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

.editor {
  flex-grow: 1;
  text-align: left;
}

.save-button-container {
  text-align: right;
  margin-top: 20px;
}

</style>
