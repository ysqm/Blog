<template>
  <div class="blog-post" @click="goToPost(post.id)">
    <img :src="post.banner" alt="Post banner" class="post-banner" />
    <h2>{{ post.title }}</h2>
    <p>{{ post.date }}</p>
    <div class="tags">
      <span v-for="tag in post.tags" :key="tag">{{ tag }}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'BlogPost',
  props: {
    post: {
      type: Object,
      required: true
    }
  },
  methods: {
    goToPost(id) {
      this.$router.push({ name: 'Article', params: { id } });
    }
  }
};
</script>

<style scoped>
.blog-post {
  border: 1px solid #ddd;
  border-radius: 10px;
  padding: 15px;
  transition: background-color 0.3s ease;
  cursor: pointer;
  width: calc(50% - 20px);
  box-sizing: border-box;
}

.blog-post:hover {
  background-color: #f9f9f9;
}

.post-banner {
  width: 100%;
  border-radius: 10px;
  margin-bottom: 10px;
}

.tags span {
  background-color: #42b983;
  color: white;
  padding: 5px 10px;
  border-radius: 5px;
  margin-right: 5px;
}
</style>
