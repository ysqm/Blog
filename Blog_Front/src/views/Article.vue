<template>
  <div class="article">
    <Navbar />

    <div class="page-container">

      <div class="content-container">
<!--        <img class="cover-image" :src="article.coverImage" alt="Cover Image">-->
        <div class="article-header">
          <h1>{{ article.title }}</h1>
          <div class="article-meta">
            <span class="meta-item">发布于 {{ article.publishDate }}</span>
            <span class="meta-item">分类: {{ article.category }}</span>
            <span class="meta-item">浏览量: {{ article.views }}</span>
          </div>
        </div>
        <div class="article-content" v-html="article.content"></div>
        <CommentSection :articleId="article.id" />
      </div>

      <Sidebar class="sidebar" />

    </div>
  </div>
</template>

<script>
import axios from 'axios';
import CommentSection from '@/components/CommentSection.vue';
import Navbar from "@/components/Navbar.vue";
import Sidebar from "@/components/self-Sidebar.vue";

import {store} from '@/store/modules/index'

import { request } from "@/request";
import {createArticle} from "@/api/article.js"
import { useRoute, useRouter } from 'vue-router'

export default {
  name: 'ArticleView',
  components: {
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
.article {
  display: flex;
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
  width: 25%;
  margin-right: 20px;
}

.cover-image {
  width: 100%;
  border-radius: 8px;
  margin-bottom: 20px;
}

.content-container {
  flex: 1;
  padding-left: 20px;
}

.article-header {
  margin-bottom: 20px;
}

.article-meta {
  display: flex;
  flex-wrap: wrap;
}

.meta-item {
  margin-right: 15px;
  color: #666;
}

.article-content {
  margin-bottom: 40px;
}
</style>
