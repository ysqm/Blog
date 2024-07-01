<template>
  <div class="right-sidebar">
    <h3>热度排行榜</h3>
    <ul>
      <li v-for="item in hotList" :key="item.id">
        <a href="#">{{ item.title }}</a>
        <span>{{ item.heat }} <heat-icon/></span>
      </li>
    </ul>
  </div>
</template>

<script>
import HeatIcon from "@/components/icons/IconHeat.vue";

export default {
  name: 'RightSidebar',
  components: {HeatIcon},
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
  top: 60px; /* Adjust based on the height of the navbar */
  right: 0;
  width: 200px;
  background-color: #f9f9f9;
  padding: 20px;
  height: 100%;
  box-shadow: -2px 0 5px rgba(0,0,0,0.1);
}
.right-sidebar h3 {
  margin-top: 0;
  color: red;
  text-align: center;
  font-weight: bold;
}
.right-sidebar ul {
  list-style: none;
  padding: 0;
}
.right-sidebar ul li {
  margin: 20px 0;
  border-bottom: 1px solid #ddd;
}
.right-sidebar ul li a {
  text-decoration: none;
  color: #333;
  font-size: x-small;
  font-weight: bold;
  font-style: italic;
}
.right-sidebar ul li span {
  display: block;
  color: red;
  font-size: xx-small;
  font-weight: lighter;
  font-style: italic;
}
</style>
