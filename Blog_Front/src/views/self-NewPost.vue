<template>
  <div class="new-post">
    <h1>发布新文章</h1>
    <form @submit.prevent="submitPost">
      <div>
        <label for="title">标题:</label>
        <input type="text" id="title" v-model="title" required />
      </div>
      <div>
        <label for="date">日期:</label>
        <input type="date" id="date" v-model="date" required />
      </div>
      <div>
        <label for="tags">标签 (用逗号分隔):</label>
        <input type="text" id="tags" v-model="tags" />
      </div>
      <div>
        <label for="banner">Banner 图片链接:</label>
        <input type="text" id="banner" v-model="banner" />
      </div>
      <button type="submit">提交</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'NewPost',
  data() {
    return {
      title: '',
      date: '',
      tags: '',
      banner: ''
    };
  },
  methods: {
    submitPost() {
      const newPost = {
        id: Date.now(),
        title: this.title,
        date: this.date,
        tags: this.tags.split(',').map(tag => tag.trim()),
        banner: this.banner
      };

      // Emit the new post data to be added to the post list
      this.$emit('new-post', newPost);

      // Redirect to home page after submission
      this.$router.push('/');
    }
  }
};
</script>

<style scoped>
.new-post {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 10px;
}

.new-post form {
  display: flex;
  flex-direction: column;
}

.new-post form div {
  margin-bottom: 15px;
}

.new-post form label {
  margin-bottom: 5px;
  font-weight: bold;
}

.new-post form input {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
</style>
