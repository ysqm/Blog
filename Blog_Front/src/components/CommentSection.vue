<template>
  <div class="article-page">
    <div class="article-content">
      <h1>{{ articleTitle }}</h1>
      <div v-html="articleBody"></div>
    </div>
    <div class="comment-section">
      <h3>全部评论 ({{ comments.length }})</h3>
      <form @submit.prevent="submitComment">
        <div class="input-row">
          <input type="text" v-model="newComment.nickname" placeholder="昵称 必填" required />
          <input type="email" v-model="newComment.email" placeholder="邮箱 必填" required />
<!--          <input type="url" v-model="newComment.website" placeholder="网址 选填" />-->
        </div>
        <textarea v-model="newComment.content" placeholder="发表一个友善的评论吧..." required></textarea>
        <button type="submit">发送</button>
      </form>
      <ul class="comment-list">
        <li v-for="comment in comments" :key="comment.id" class="comment-item">
          <div class="comment-avatar">
            <img :src="comment.avatar || defaultAvatar" alt="avatar" />
          </div>
          <div class="comment-content">
            <div class="comment-header">
              <span class="comment-author">{{ comment.nickname }}</span>
              <span class="comment-date">{{ comment.createTime }}</span>
            </div>
            <p>{{ comment.content }}</p>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'CommentSection',
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
        nickname: '',
        email: '',
        website: '',
        content: ''
      },
      defaultAvatar: 'path/to/default/avatar.png',
      articleTitle: '文章标题', // 示例标题
      articleBody: '<p>文章内容</p>' // 示例内容
    };
  },
  created() {
    this.fetchComments();
  },
  methods: {
    async fetchComments() {
      try {
        const response = await axios.get(`/comment/article/${this.articleId}`);
        this.comments = response.data.data;
      } catch (error) {
        console.error("Error fetching comments:", error);
      }
    },
    async submitComment() {
      try {
        const response = await axios.post(`/comment/add`, {
          ...this.newComment,
          articleId: this.articleId
        });
        this.comments.push(response.data.data);
        this.newComment.nickname = '';
        this.newComment.email = '';
        this.newComment.website = '';
        this.newComment.content = '';
      } catch (error) {
        console.error("Error submitting comment:", error);
      }
    }
  }
};
</script>

<style scoped>
.article-page {
  max-width: 800px; /* 设置页面最大宽度 */
  margin: 0 auto; /* 水平居中 */
  padding: 20px; /* 页面内边距 */
  background: #fff; /* 背景色 */
  border-radius: 8px; /* 边框圆角 */
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); /* 阴影效果 */
}

.article-content {
  margin-bottom: 40px; /* 文章内容与评论区之间的间距 */
}

.article-content h1 {
  margin-bottom: 20px; /* 标题与正文之间的间距 */
}

.comment-section {
  padding: 20px; /* 评论区内边距 */
  background: #f9f9f9; /* 评论区背景色 */
  border-radius: 8px; /* 评论区边框圆角 */
}

.comment-section h3 {
  margin-bottom: 20px; /* 评论区标题与表单之间的间距 */
}

.comment-section form {
  margin-bottom: 40px; /* 表单与评论列表之间的间距 */
}

.input-row {
  display: flex; /* 水平排列输入框 */
  justify-content: space-between; /* 输入框之间的空隙 */
  margin-bottom: 10px; /* 输入框与文本域之间的间距 */
}

.input-row input {
  flex: 1; /* 输入框均分宽度 */
  margin-right: 10px; /* 输入框之间的间距 */
}

textarea {
  width: 100%; /* 文本域宽度 */
  margin-bottom: 10px; /* 文本域与按钮之间的间距 */
}

button {
  display: inline-block; /* 按钮为行内块元素 */
  padding: 10px 20px; /* 按钮内边距 */
  background-color: #007bff; /* 按钮背景色 */
  color: #fff; /* 按钮文字颜色 */
  border: none; /* 按钮无边框 */
  border-radius: 4px; /* 按钮圆角 */
  cursor: pointer; /* 鼠标悬停样式 */
}

button:hover {
  background-color: #0056b3; /* 鼠标悬停时的按钮背景色 */
}

.comment-list {
  list-style: none; /* 无序列表无样式 */
  padding: 0; /* 列表内边距 */
}

.comment-item {
  display: flex; /* 评论项水平排列 */
  align-items: flex-start; /* 评论项顶部对齐 */
  padding: 10px; /* 评论项内边距 */
  border-bottom: 1px solid #ddd; /* 评论项底部分割线 */
  margin-bottom: 10px; /* 评论项之间的间距 */
  border-radius: 4px; /* 评论项圆角 */
  background: #fff; /* 评论项背景色 */
}

.comment-avatar {
  flex-shrink: 0; /* 防止头像缩小 */
  margin-right: 10px; /* 头像与评论内容之间的间距 */
}

.comment-avatar img {
  width: 40px; /* 头像宽度 */
  height: 40px; /* 头像高度 */
  border-radius: 50%; /* 头像圆形 */
}

.comment-content {
  flex: 1; /* 评论内容占满剩余空间 */
}

.comment-header {
  margin-bottom: 5px; /* 评论头部与内容之间的间距 */
}

.comment-author {
  font-weight: bold; /* 评论作者加粗 */
  margin-right: 10px; /* 评论作者与日期之间的间距 */
}

.comment-date {
  color: #999; /* 评论日期颜色 */
  font-size: 0.9em; /* 评论日期字体大小 */
}
</style>
