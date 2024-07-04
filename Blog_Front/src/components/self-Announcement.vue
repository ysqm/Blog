<template>
  <div v-if="visible" class="announcement">
    <p>公告：当前为第二层个人博客界面，第一层为社区首页</p>
    <button class="close-button" @click="closeAnnouncement">×</button>
  </div>
</template>

<script>
export default {
  name: 'Announcement',
  data() {
    return {
      visible: true
    };
  },
  methods: {
    closeAnnouncement() {
      this.visible = false;
    }
  }
};
</script>

<style scoped>
.announcement {
  background-color: #e0f7fa;
  color: #00796b;
  padding: 15px;
  margin: 10px;
  border-radius: 10px;
  width: calc(100% - 40px);
  text-align: center;
  border: 1px solid #00796b;
  position: relative;
}

.announcement p {
  margin: 0;
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 20px;
  cursor: pointer;
  color: #00796b;
}
</style>
