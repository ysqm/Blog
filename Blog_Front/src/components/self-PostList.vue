<template>
  <div class="post-list">
    <BlogPost
        v-for="post in posts"
        :key="post.id"
        :post="post"
        @click.native="goToPost(post.id)"
    />
  </div>
</template>

<script>
import BlogPost from './self-BlogPost.vue';

export default {
  name: 'PostList',
  components: {
    BlogPost
  },
  data() {
    return {
      posts: [
        {id: 1, title: '测试文章1', date: '2023-11-23', tags: ['Java'], banner: '/src/assets/images/banner1.png'},
        {id: 2, title: '测试文章2', date: '2024-01-28', tags: ['Java'], banner: '/src/assets/images/banner2.png'}
      ]
    };
  },
  methods: {
    goToPost(id) {
      this.$router.push({name: 'Article', params: {id}});
    }
  }
};
</script>

<style>
.post-list {
  display: flex;

  flex-wrap: wrap;
  gap: 20px;

}

@media (min-width: 768px) {
  .post-list {
    justify-content: space-between;
  }
}

.post-item {
  aspect-ratio: 16 / 9; /* 设置长宽比为16:9，水平长度大于垂直长度 */
  width: 100%; /* 使其占满父元素的宽度 */
  max-width: 300px; /* 限制最大宽度 */
  background-color: lightgray; /* 示例背景色，可根据需要调整 */
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
}

</style>
