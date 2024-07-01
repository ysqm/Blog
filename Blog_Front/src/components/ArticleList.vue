<template>
  <div class="article-list">
    <div class="article" v-for="article in articles" :key="article.id">
      <h3>{{ article.title }}</h3>
      <div class="img-preview">
        <img :src="article.avatar" alt="avatar" class="article-img"/>
        <p>{{ article.summary }}</p>
      </div>
      <div class="meta">
        <div class="info">
          <span>{{ article.author }}</span>
          <span>{{ article.date }}</span>
        </div>
        <div class="stats">
          <span><HeatIcon />  {{ article.heat }}</span>
          <span><LikeIcon />  {{ article.likes }}</span>
          <span><ViewIcon />  {{ article.views }}</span>
          <span><CommentIcon />  {{ article.comments }}</span>
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
      articles: [
        {
          id: 1,
          title: 'Vue - 入门',
          summary: '前端目前形势前端的发展史...',
          author: '二价疫苗',
          date: '2024-06-30',
          avatar: 'avatar.jpg',
          heat: 100,
          likes: 20,
          views: 200,
          comments: 5
        },
        {
          id: 2,
          title: 'Python——比 Seaborn 更好的相关性热力图',
          summary: '在Python中我们日常分析数据...',
          author: '多玩我的世界盒子',
          date: '2024-06-30',
          avatar: 'path/to/avatar2.png',
          heat: 80,
          likes: 15,
          views: 150,
          comments: 3
        }
        // 更多文章
      ]
    }
  }
}
    // 获取文章列表
</script>

<style scoped>
.article-list {
  margin-right: 100px; /* 右侧边栏宽度 */
  padding: 20px;
  overflow-y: auto;
  background-color: #e8a9c9; /* 背景色 */
}
.article {
  margin-bottom: 20px;
  border-bottom: 1px solid #ddd;
  padding-bottom: 10px;
}
.article h3 {
  margin: 0 0 10px;
  font-weight: bold;
  font-style: italic;
}
.article .img-preview {
  display: flex;
  align-items: center;
  gap: 10px;
  flex-direction: row;
}
.img-preview .article-img {
  width: 30px;
  height: 30px;
  border-radius: 50%;
}
.article p {
  margin: 0 0 10px;
  font-weight: lighter;
  font-size: xx-small;
}
.meta {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.meta .info {
  display: flex;
  gap: 10px;
  font-style: italic;
  font-size: x-small;
}
.meta .info span {
  display: inline-block;
}
.stats {
  display: flex;
  gap: 10px;
  font-size: xx-small;
}
.stats span {
  display: flex;
  align-items: center;
}
.stats span svg {
  margin-right: 5px;
}
</style>
