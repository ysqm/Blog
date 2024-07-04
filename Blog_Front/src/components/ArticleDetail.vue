<template>
  <div class="article-detail">
    <el-card shadow="hover" class="article-card">
      <h1>{{ article.title }}</h1>
      <p><i class="el-icon-user"></i> 作者: {{ article.author }}</p>
      <p><i class="el-icon-time"></i> 发布时间: {{ formatDate(article.publishDate) }}</p>
      <div class="article-content" v-html="renderedContent"></div>
    </el-card>
  </div>
</template>

<script>
import { onMounted, ref } from 'vue';
import axios from 'axios';
import {marked} from 'marked'; // 导入 marked 库
import dayjs from 'dayjs';

export default {
  name: 'ArticleDetail',
  props: {
    id: {
      type: Number,
      required: true
    }
  },
  setup(props) {
    const article = ref({});
    const renderedContent = ref(''); // 使用 ref 来定义响应式变量

    // 获取文章详情
    const fetchArticleDetail = (articleId) => {
      axios.get(`/api/articles/${articleId}`)
          .then(response => {
            article.value = response.data.data;
            renderMarkdownContent(article.value.content); // 调用渲染 Markdown 内容的方法
          })
          .catch(error => {
            console.error('Error fetching article detail:', error);
          });
    };

    // 格式化日期
    const formatDate = (timestamp) => {
      return dayjs(timestamp).format('YYYY-MM-DD HH:mm:ss');
    };

    // 渲染 Markdown 内容
    const renderMarkdownContent = (content) => {
      if (content) {
        renderedContent.value = marked(content); // 使用 v-html 渲染 Markdown 内容
      }
    };

    // 在组件加载时获取文章详情
    onMounted(() => {
      fetchArticleDetail(props.id);
    });

    return {
      article,
      renderedContent,
      formatDate
    };
  }
};
</script>

<style scoped>
.article-detail {
  padding: 20px;
}

.article-card {
  width: 80%;
  margin: auto;
}

.article-card h1 {
  font-size: 24px;
  margin-bottom: 10px;
}

.article-card p {
  margin-bottom: 5px;
  font-size: 14px;
  color: #666;
}

.article-content {
  margin-top: 20px;
  font-size: 16px;
  line-height: 1.8;
}
</style>
