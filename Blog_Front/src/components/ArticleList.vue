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
          avatar: 'avatar.jpg',
          heat: 80,
          likes: 15,
          views: 150,
          comments: 3
        }
        // 更多文章数据
      ]
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
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);

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
.meta .info span{
  color:#666666;
}

.user{
  font-style: italic;
  font-size: small;
}

.data{
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
