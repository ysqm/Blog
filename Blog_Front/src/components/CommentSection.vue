<template>
  <div class="comment-section">
    <h3>全部评论 ({{ comments.length }})</h3>

    <form @submit="submitComment">
      <textarea v-model="newComment.content" placeholder="发表一个友善的评论吧..." required></textarea>
      <button type="submit">发送</button>
    </form>

    <ul class="comment-list">
      <li v-for="comment in comments" :key="comment.commentId" class="comment-item" v-if=1>
        <div class="comment-avatar">
          <img :src="comment.avatar || defaultAvatar" alt="avatar" />
          <span class="comment-author">{{ comment.userId || '请刷新界面' }}</span>
        </div>
        <div class="comment-content">
          <div class="comment-header">
            <span class="comment-date">{{ comment.commentDate }}</span>
          </div>
          <p>{{ comment.content }}</p>
          <LikeButton :commentId="comment.commentId" :initialLikes="comment.likes || 0" />
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import { getCommentsByArticleId, addComment } from '@/api/comment'; // 确保导入路径正确
import LikeButton from './LikeButton.vue'; // 导入点赞组件

export default {
  name: 'CommentSection',
  components: { LikeButton },
  props: {
    articleId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      comments: [],
      newComment: {
        content: ''
      },
      defaultAvatar: '../../public/profile.png', // 定义默认头像路径
      articleId: Number(this.$route.params.id)
    };
  },
  created() {
    this.fetchComments();
  },
  methods: {
    async fetchComments() {
      try {
        const response = await getCommentsByArticleId(this.articleId);
        if (response && response.data) {
          this.comments = response.data.data || []; // 确保数据正确赋值并且是数组
        } else {
          console.error('No data returned from API');
        }
      } catch (error) {
        console.error('Error fetching comments:', error);
      }
    },
    async submitComment() {
      try {
        const userId = localStorage.getItem('uid');
        if (!userId) {
          console.error('User is not logged in.');
          return;
        }
        const response = await addComment(userId, this.newComment.content, this.articleId);
        if (response && response.data) {
          this.comments.push(response.data); // 直接将新评论添加到当前评论列表
          this.newComment.content = ''; // 清空评论输入框
          console.log('New comment added:', response.data);
        } else {
          console.error('Failed to add comment');
        }
      } catch (error) {
        console.error('Error submitting comment:', error);
      }
    },
    formatDate(dateArray) {
      if (!dateArray || dateArray.length < 6) return '';
      const [year, month, day, hour, minute, second] = dateArray;
      return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
    }
  }
};
</script>

<style scoped>
.article-page {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.article-content {
  margin-bottom: 40px;
}

.comment-section {
  padding: 20px;
  background: #f9f9f9;
  border-radius: 8px;
}

.comment-section h3 {
  margin-bottom: 20px;
}

.comment-section form {
  margin-bottom: 40px;
}

textarea {
  width: 100%;
  margin-bottom: 10px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

button {
  display: inline-block;
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

.comment-list {
  list-style: none;
  padding: 0;
}

.comment-item {
  display: flex;
  align-items: flex-start;
  padding: 10px;
  border-bottom: 1px solid #ddd;
  margin-bottom: 10px;
  border-radius: 4px;
  background: #fff;
  position: relative;
}

.comment-avatar {
  flex-shrink: 0;
  margin-right: 10px;
  display: flex;
  align-items: center;
}

.comment-avatar img {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin-right: 8px;
}

.comment-content {
  flex: 1;
}

.comment-header {
  margin-bottom: 5px;
}

.comment-author {
  font-weight: bold;
  margin-right: 10px;
}

.comment-date {
  color: #999;
  font-size: 0.9em;
}

.comment-item .like-button {
  position: absolute;
  bottom: 10px;
  right: 10px;
}

.like-button {
  display: inline-block;
  padding: 3px 8px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 0.9em;
}

.like-button:hover {
  background-color: #0056b3;
}
</style>
