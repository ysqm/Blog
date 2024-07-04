<template>
  <div class="article-page">
    <div class="article-content">
      <h1>{{ articleTitle }}</h1>
      <div v-html="articleBody"></div>
    </div>

    <div class="comment-section">

      <h3>全部评论 ({{ comments.length }})</h3>

      <form @submit.prevent="submitComment">
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
import { addComment, getCommentsByArticleId } from "@/api/comment";
import { mapState } from "vuex";

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
        content: ''
      },
      // defaultAvatar: 'path/to/default/avatar.png',
      // articleTitle: '文章标题',
      // articleBody: '<p>文章内容</p>'
      // userId:1,
      articleId: 3
    };
  },
  // computed: {
  //   ...mapState({
  //     userId: state => state.uid // 从 Vuex 中获取 userId
  //   })
  // },
  created() {
    this.fetchComments();
  },
  methods: {

    async fetchComments() {
      try {
        const response = await getCommentsByArticleId(this.articleId);
        this.comments = response.data;
      } catch (error) {
        console.error("Error fetching comments:", error);
      }
    },

    async submitComment() {
      try {
        const response = await addComment(this.userId, this.newComment.content, this.articleId);
        this.comments.push(response.data); // 直接将新评论添加到当前评论列表
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

.article-content h1 {
  margin-bottom: 20px;
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
}

.comment-avatar {
  flex-shrink: 0;
  margin-right: 10px;
}

.comment-avatar img {
  width: 40px;
  height: 40px;
  border-radius: 50%;
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
</style>
