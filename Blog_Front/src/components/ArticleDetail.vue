<template>
  <div class="article-detail">
    <Navbar />
    <div class="page-container">
      <div class="main-content">
        <el-card shadow="hover" class="article-card">
          <h1>{{ article.title }}</h1>
          <p><i class="el-icon-user"></i> 作者: {{ article.author }}</p>
          <p><i class="el-icon-time"></i> 发布时间: {{ formatDate(article.publishDate) }}</p>
          <div class="article-content" v-html="renderedContent"></div>
          <CommentSection :articleId="article.id" />
        </el-card>
      </div>
      <div class="sidebar-container">
        <Sidebar />
      </div>
    </div>
  </div>
</template>

<script>
import { onMounted, ref } from 'vue';
import axios from 'axios';
import { marked } from 'marked'; // 导入 marked 库
import dayjs from 'dayjs';
import Navbar from "@/components/Navbar.vue";
import Sidebar from "@/components/self-Sidebar.vue";
import CommentSection from "@/components/CommentSection.vue";
import { useRoute } from 'vue-router';

export default {
  name: 'ArticleDetail',
  components: { CommentSection, Sidebar, Navbar },
  setup() {
    const route = useRoute();
    const articleId = route.params.id; // 从路由参数中获取文章ID

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
      fetchArticleDetail(articleId);
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

.page-container {
  display: flex;
  justify-content: space-between;
}

.main-content {
  flex: 3;
}

.sidebar-container {
  flex: 1;
  margin-left: 20px;
}

.article-card {
  width: 100%;
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
  text-align: left;
}
</style>
