<template>
  <div class="article">
    <Navbar />

    <div class="page-container">

      <div class="content-container">

        <div class="article-header">
          <h1>{{ article.title }}</h1>
          <div class="article-meta">
            <span>发布于 {{ article.publishDate }}</span>
            <span>分类: {{ article.category }}</span>
            <span>浏览量: {{ article.views }}</span>
          </div>
        </div>

        <div class="article-title">
          <span>
            两种宇宙观
          </span>
        </div>

        <div class="article-content">

          <span>
            在人类的认识史中，从来就有关于宇宙发展法则的两种见解，一种是形而上学的见解，一种是辩证法的见解，形成了互相对立的两种宇宙观。列宁说:“对于发展(进化)所持的两种基本的(或两种可能的?或两种在历史上常见的?)观点是:(一)认为发展是减少和增加，是重复;(二)认为发展是对立的统一(统一物分成为两个互相排斥的对立，而两个对立又互相关联” 列宁说的就是这两种不同的宇宙观。着)。
            形而上学，亦称玄学。这种思想，无论在中国，在欧洲，在一个很长的历史时间内，是属于唯心论的宇宙观，并在人们的思想中占了统治的地位。在欧洲，资产阶级初期的唯物论，也是形而上学的。由于欧洲许多国家的社会经济情况进到了资本主义高度发展的阶段，生产力、阶级斗争和科学均发展到了历史上未有过的水平，工业无产阶级成为历史发展的最伟大的动力，因而产生了马克思主义的唯物辩证法的宇审观。于是，在资产阶级那里，除了公开的极端露骨的反动的唯心论之外，还出现了庸俗的进化论，出来对抗唯物辩证法。
            所谓形而上学的或庸俗进化论的宇宙观，就是用孤立的、静止的和片面的观点去看世界。这种宇宙观把世界一切事物，一切事物的形态和种类，都看成是永远彼此孤立和永远不变化的。如果说有变化，也只是数量的增减和场所的变更。而这种增减和变更的原因，不在事物的内部而在事物的外部，即是由于外力的推动。形而上学家认为，世界上各种不同事物和事物的特性，从它们一开始存在的时候就是如此。后来的变化，不过是数量上的扩大或缩小。他们认为一种事物永远只能反复地产生为同样的事物，而不能变化为另一种不同的事物。
            在形而上学家看来，资本主义的剥削，资本主义的竞争，资本主义社会的个人主义思想等，就是在古代的奴隶社会里，甚至在原始社会里，都可以找得出来，而且会要永远不变地存在下去。说到社会发展的原因，他们就用社会外部的地理、气候等条件去说明。他们简单地从事物外部去找发展的原因，否认唯物辩证法所主张的事物因内部矛盾引起发展的学说。因此，他们不能解释事物的质的多样性，不能解释一种质变为他种质的现象。这种思想，在欧洲，在十七世纪和十八世纪是机械唯物论，在十九世纪未和二十世纪初则有庸俗进化论。
            在中国，则有所谓“天不变，道亦不变”的形而上学的思想，曾经长期地为腐朽了的封建统治阶级所拥护。近百年来输入了欧洲的机械唯物论和庸俗进化论，则为资产阶级所拥护。
          </span>
        </div>

        <div class="article-content" v-html="article.content"></div>
        <CommentSection :articleId="article.id" />

      </div>

      <Sidebar />

    </div>

  </div>
</template>

<script>
import axios from 'axios';
import CommentSection from '@/components/CommentSection.vue';
import Navbar from "@/components/Navbar.vue";
import Sidebar from "@/components/self-Sidebar.vue";
import PostList from "@/components/self-PostList.vue";

export default {
  name: 'ArticleView',
  components: {
    PostList,
    Sidebar,
    Navbar,
    CommentSection
  },
  data() {
    return {
      article: {}
    };
  },
  created() {
    this.fetchArticle();
  },
  methods: {
    async fetchArticle() {
      try {
        // const response = await axios.get(`/api/articles/${this.$route.params.id}`);
        // this.article = response.data;
      } catch (error) {
        console.error("Error fetching article:", error);
      }
    }
  }
};
</script>

<style scoped>
.article-view {
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.page-container {
  display: flex;
  max-width: 1200px;
  margin: 20px auto;
  padding: 20px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.sidebar {
  width: 250px;
}

.content-container {
  flex: 1;
  padding-left: 20px;
}

.article-header {
  margin-bottom: 20px;
}

.article-meta span {
  margin-right: 15px;
  color: #666;
}

.article-content {
  margin-bottom: 40px;
}
</style>
