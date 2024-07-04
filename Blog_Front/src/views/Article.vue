<template>
  <div class="article">
    <Navbar />

    <div class="page-container">

      <div class="content-container">
        <div class="article-header">
          <h1>{{ article.title }}</h1>
          <div class="article-meta">
            <span>发布于 {{ article.publishDate }}</span>
            <span>分类: {{ article.category }}</span>
            <span>浏览量: {{ article.views }}</span>
          </div>
        </div>

        <div class="article-content" v-html="article.content"></div>
        <CommentSection :articleId="article.id" />

      </div>

      <Sidebar />

    </div>

  </div>
</template>

<script>
import axios from 'axios';
import CommentSection from '@/components/CommentSection.vue';
import Navbar from "@/components/Navbar.vue";
import Sidebar from "@/components/self-Sidebar.vue";
import PostList from "@/components/self-PostList.vue";

export default {
  name: 'ArticleView',
  components: {
    PostList,
    Sidebar,
    Navbar,
    CommentSection
  },
  data() {
    return {
      article: {}
    };
  },
  created() {
    this.fetchArticle();
  },
  methods: {
    async fetchArticle() {
      try {
        const response = await axios.get(`/api/articles/${this.$route.params.id}`);
        this.article = response.data;
      } catch (error) {
        console.error("Error fetching article:", error);
      }
    }
  }
};
</script>

<style scoped>
.article-view {
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.page-container {
  display: flex;
  max-width: 1200px;
  margin: 20px auto;
  padding: 20px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.sidebar {
  width: 250px;
}

.content-container {
  flex: 1;
  padding-left: 20px;
}

.article-header {
  margin-bottom: 20px;
}

.article-meta span {
  margin-right: 15px;
  color: #666;
}

.article-content {
  margin-bottom: 40px;
}
</style>
