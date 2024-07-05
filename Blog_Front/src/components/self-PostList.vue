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
        {id: 1, title: '测试文章1', date: '2024-07-02', tags: ['Test'], banner: '/src/assets/images/banner1.png'},
        {id: 2, title: '测试文章2', date: '2024-07-03', tags: ['Test'], banner: '/src/assets/images/banner2.png'},
        {id: 3, title: '测试文章3', date: '2024-07-04', tags: ['Test'], banner: '/src/assets/images/banner1.png'},
        {id: 4, title: '测试文章4', date: '2024-07-04', tags: ['Test'], banner: '/src/assets/images/banner1.png'}
      ]
    };
  },
  methods: {
    goToPost(id) {
      this.$router.push({name: 'ArticleDetail', params: {id}});
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

.post-list > * {
  flex: 1 1 calc(50% - 20px); /* 每行两个项目，每个项目占50%的宽度，减去gap */
  margin-bottom: 20px;
}

@media (min-width: 768px) {
  .post-list {
    justify-content: space-between;
  }
}

@media (max-width: 767px) {
  .post-list > * {
    flex: 1 1 100%; /* 在小屏幕上每行显示一个项目 */
  }
}
</style>
