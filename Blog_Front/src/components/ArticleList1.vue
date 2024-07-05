<template>
  <div class="article-list">
    <div class="article" v-for="article in articles" :key="article.articleId">
      <h3 @click="openArticle(article.articleId)">{{ article.title }}</h3>
      <div class="img-preview">
        <img :src="article.avatar" alt="avatar" class="article-img"/>
        <p>{{ article.summary }}</p>
      </div>
      <div class="meta">
        <div class="info">
          <span class="user">{{ article.author }}</span>
          <span class="data">{{ formatDate(article.publishDate) }}</span>
        </div>
        <div class="stats">
          <span><HeatIcon/>  {{ article.heat }}</span>
          <span><LikeIcon/>  {{ article.likes }}</span>
<!--          <span><ViewIcon/>  {{ article.views }}</span>-->
          <span><CommentIcon/>  {{ article.comments }}</span>
        </div>
      </div>
    </div>
    <div class="pagination-container">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[10, 20, 30, 50]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
      ></el-pagination>
    </div>

  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import axios from 'axios'; // Assuming axios is imported correctly
import { getArticlesByPage } from '@/api/article'; // Assuming correct import path
import HeatIcon from './icons/IconHeat.vue'
import LikeIcon from './icons/IconLike.vue'
import ViewIcon from './icons/IconView.vue'
import CommentIcon from './icons/IconComment.vue'
import dayjs from 'dayjs'; // Import dayjs for date formatting
import { useRouter } from 'vue-router'; // Import Vue Router

export default {
  name: 'ArticleList',
  props: {
    // Define props if necessary
  },
  components: {
    HeatIcon,
    LikeIcon,
    ViewIcon,
    CommentIcon
  },
  data() {
    return {
      articles: [],
      total: 0,
      currentPage: 1,
      pageSize: 10,
    };
  },
  setup() {
    const articles = ref([]);
    const currentPage = ref(1);
    const pageSize = ref(10);
    const total = ref(0);
    const router = useRouter(); // Initialize Vue Router

    // Fetch articles on component mount
    onMounted(() => {
      fetchArticles();
    });

    const fetchArticles = (page = 1) => {
      console.log(page)
      console.log(pageSize.value)
      axios.post('/api/articles/page', {
        page: page,
        pageSize: pageSize.value,
        title: '',
        // Assuming 'title' is an optional parameter in your API
      })
          .then(response => {
            articles.value = response.data.list; // Assuming 'list' contains the articles
            total.value = response.data.total; // Assuming 'total' is the total count of articles
            currentPage.value = page; // Update currentPage after fetching
          })

          .catch(error => {
            console.error('Error fetching articles:', error);
          });
    };

    const handleSizeChange = (val) => {
      pageSize.value = val;
      fetchArticles(currentPage.value);
    };

    const handleCurrentChange = (val) => {
      currentPage.value = val;
      fetchArticles(val);
    };

    const openArticle = (articleId) => {
      // Navigate to article details page
      console.log('Open article:', articleId);
      router.push({ name: 'ArticleDetail', params: { id: articleId } });
    };

    const formatDate = (timestamp) => {
      // Assuming 'timestamp' is the time in milliseconds from backend
      // Using dayjs to format date
      return dayjs(timestamp).format('YYYY-MM-DD HH:mm:ss');
    };
    return {
      articles,
      currentPage,
      pageSize,
      total,
      fetchArticles,
      handleSizeChange,
      handleCurrentChange,
      openArticle,
      formatDate
    };
  },
};
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

.pagination-container{
  text-align: center;
  margin: auto;
}
</style>
