<!-- LikeButton.vue -->
<template>
  <button @click="likeComment">
    👍 {{ likes }}
  </button>
</template>

<script>
import {getCommentLikeNum, addCommentLike, addCommentDislike} from '@/api/likeDislike'; // 确保导入路径正确

export default {
  name: 'LikeButton',
  props: {
    commentId: {
      type: Number,
      required: true
    },
    initialLikes: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      likes: this.initialLikes
    };
  },
  async created() {
    try {
      const response = await getCommentLikeNum(this.commentId);
      if (response && response.data) {
        this.likes = response.data.data; // 假设返回的点赞数在 response.data.data
      } else {
        console.error('Failed to fetch initial likes');
      }
    } catch (error) {
      console.error('Error fetching initial likes:', error);
    }
  },
  methods: {
    async likeComment() {
      try {
        const userId = localStorage.getItem('uid');
        if (!userId) {
          console.error('User is not logged in.');
          return;
        }
        const response = await addCommentDislike(Number(userId), this.commentId);
        if (response && response.data) {
          const response2 = await getCommentLikeNum(this.commentId);
          this.likes = response2.data.data; // 更新点赞数
          console.log('Comment liked:', response.data);
        } else {
          console.error('Failed to like comment');
        }
      } catch (error) {
        console.error('Error liking comment:', error);
      }
    }
  }
};
</script>

<style scoped>
button {
  display: inline-block;
  padding: 5px 10px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
