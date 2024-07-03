<template>
  <div class="right-sidebar">
    <h3>热度排行榜</h3>
    <ul>
      <li v-for="item in hotList" :key="item.id">
        <a href="#">{{ item.title }}</a>
        <span>{{ item.heat }} <HeatIcon/></span>
      </li>
    </ul>
  </div>
</template>

<script>
import HeatIcon from "@/components/icons/IconHeat.vue";

export default {
  name: 'RightSidebar',
  components: { HeatIcon },
  data() {
    return {
      hotList: [
        { id: 1, title: 'C# 语言写的仅有 8KB 大小的贪吃蛇开源游戏', heat: 809 },
        { id: 2, title: '你要的 AI Agent 工具都在这里', heat: 2145 }
        // 更多热度排行项目
      ]
    }
  }
}
</script>

<style scoped>
.right-sidebar {
  position: fixed;
  top: 50px; /* 确保导航栏的高度 */
  right: 0;
  width: 200px;
  height: calc(100vh - 50px); /* 调整高度以排除导航栏 */
  background-color: #F8F8F8;
  padding: 20px;
  box-shadow: -2px 0 5px rgba(0,0,0,0.1);
  overflow-y: auto; /* 确保侧边栏可滚动 */
}
.right-sidebar h3 {
  margin-top: 0;
  color: #FFA500;
  text-align: center;
  font-weight: bold;
}
.right-sidebar ul {
  list-style: none;
  padding: 0;
}
.right-sidebar ul li {
  margin: 10px 0;
  display: flex;
  justify-content: space-between;
  border-bottom: 1px solid #ddd;
}
.right-sidebar ul li a {
  text-decoration: none;
  color: #333333;
  font-size: x-small;
  font-weight: bold;
  font-style: italic;
}
.right-sidebar ul li span {
  display: flex;
  color: #333333;
  font-size: xx-small;
  font-weight: lighter;
  font-style: italic;
}
.right-sidebar ul li span svg {
  margin-left: 5px;
}
</style>
