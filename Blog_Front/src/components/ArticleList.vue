<template>
  <div class="article-list">
    <div class="article" v-for="article in articles" :key="article.id">
      <h3 @click="openArticle(article.id)">{{ article.title }}</h3>
      <div class="img-preview">
        <img :src="article.avatar" alt="avatar" class="article-img"/>
        <p>{{ article.summary }}</p>
      </div>
      <div class="meta">
        <div class="info">
          <span class="user">{{ article.author }}</span>
          <span class="data">{{ article.date }}</span>
        </div>
        <div class="stats">
          <span><HeatIcon/>  {{ article.heat }}</span>
          <span><LikeIcon/>  {{ article.likes }}</span>
          <span><ViewIcon/>  {{ article.views }}</span>
          <span><CommentIcon/>  {{ article.comments }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import HeatIcon from './icons/IconHeat.vue'
import LikeIcon from './icons/IconLike.vue'
import ViewIcon from './icons/IconView.vue'
import CommentIcon from './icons/IconComment.vue'

export default {
  name: 'ArticleList',
  components: {
    HeatIcon,
    LikeIcon,
    ViewIcon,
    CommentIcon
  },
  data() {
    return {
      articles: [],
      selectedArticle: {}
    };
  },
  mounted() {
    this.getNewArticles();
  },
  methods: {
    getNewArticles() {
      getLatestArticles().then(response => {
        this.articles = response.data;
      });
    },
    openArticle(articleId) {
      getArticleById(articleId).then(response => {
        this.selectedArticle = response.data;
      });
    }
  }
}
</script>

<style scoped>
.article-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
  margin-left: 120px;
  margin-right: calc(200px + 20px);
}

.article {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  color: #F8F8F8;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);

}

.article h3 {
  color: #1E90FF;
  margin: 0;
  text-align: left;
}

.img-preview {
  display: flex;
  align-items: center;
  gap: 10px;
}

.img-preview p {
  margin: 0;
  color: #333333;
}

.article-img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  object-fit: cover;
}

.meta {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}

.meta .info {
  display: flex;
  gap: 10px;
  align-items: center;
}

.meta .info span {
  color: #666666;
}

.user {
  font-style: italic;
  font-size: small;
}

.data {
  font-size: xx-small;
}

.meta .stats {
  display: flex;
  gap: 10px;
  align-items: center;
}

.meta .stats span {
  display: flex;
  align-items: center;
  gap: 5px;
  color: #333333;
}
</style>
