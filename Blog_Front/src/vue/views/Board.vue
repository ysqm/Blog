<template>
  <div class="common-layout">
      <el-container>
          <el-header class="floating-header">
              <el-text class = "mr15-ml5">起始日期:</el-text>
              <el-date-picker class = "timeimputstyles" v-model="startDate" @change="datepickerChange1"/>
              <el-text class = "mr15-ml5">终止日期:</el-text>
              <el-date-picker style="top: 3px" class = "timeimputstyles" v-model="endDate" @change="datepickerChange2"/>
              <el-input-number v-model="Size" :min="1" :max="50"/>
              <el-button type="primary" class = "mr-15-ml5" @click="pageQuery">
                  <el-icon><Search /></el-icon>查询
              </el-button>
          </el-header>
          <el-main class="floating-main">
              <el-table :data="records" stripe style="width: 100%;background-color: #d9ecff;">
                  <el-table-column prop="userId" label="账号Id" width="180" />
                  <el-table-column prop="username" label="账户名" width="180" />
                  <el-table-column prop="email" label="邮箱" width="180"/>
                  <el-table-column prop="createTime" label="创建时间" width="180"/>
                  <el-table-column prop="updateTime" label="更新时间" width="180"/>
                  <el-table-column prop="wechatAccount" label="微信" width="180"/>
                  <el-table-column prop="qqAccount" label="QQ" width="180"/>
                  <el-table-column prop="isLoggedOut" label="注销状态" width="180"/>
                  <el-table-column prop="lastLoginTime" label="最后登录时间" width="180"/>
                  <el-table-column prop="permissionLevel" label="权限等级" width="180"/>
              </el-table>
              <el-pagination
              @Size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :page-size="Size"
              :pager-count="11"
              :current-page="pageNumber"
              layout="prev, pager, next ,jumper"
              :total="total"
              style = "margin-top:20px;"
              />

              
              <!-- <el-pagination background layout="prev, pager, next ,jumper" :total="total" style = "margin-top:20px;"/> -->
          </el-main>
      </el-container>
  </div>
</template>

<script lang = 'ts'>
import { getPageList } from "@/api/user";
import { ElMessage, ElMessageBox } from "element-plus";
import { ref } from "vue";
import { format } from "date-fns"; // 引入 date-fns 库的 format 函数

export default {
name: 'userManage',
data() {
  return {
    startDate: "2000-01-01",
    endDate: "2040-01-01",
    pageNumber: 1,
    Size: 10,
    total: 0,
    records: [],
  };
},
created() {
  this.pageQuery();
},
methods: {
  datepickerChange1(value) {
    this.startDate = format(new Date(value), "yyyy-MM-dd");
  },
  datepickerChange2(value) {
    this.endDate = format(new Date(value), "yyyy-MM-dd");
  },
  pageQuery(){
      getPageList({
          startTime:this.startDate + ' 00:00',
          endTime:this.endDate + ' 00:00',
          page:this.pageNumber,
          pageSize:this.Size
      }).then(res => {
          if(res.data.code === 1){
              this.total = res.data.data.total
              this.records = res.data.data.records
          }
      }).catch(err => {
          ElMessageBox.alert('未成功获取用户列表', 'Title', {
      // if you want to disable its autofocus
      // autofocus: false,
          confirmButtonText: 'OK',
          })
      })
  },
  handleSizeChange(Size){
      this.Size = Size
      this.pageQuery()
  },
  handleCurrentChange(pageNumber){
      this.pageNumber = pageNumber
      this.pageQuery()
  }
}
};
</script>

<style>
.common-layout {
  background-color:#a0cfff;
  text-align: left;
  display: flex;
  flex-direction: column;
}
.mr15-ml5 {
  margin-right: 15px;
  margin-left: 5px;
}
.floating-header {
  background-color: #d9ecff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 10px;
  margin-bottom: 10px;
  display: flex;
  justify-content: space-between; 
}
.left-aligned {
  display: flex;
  justify-content: flex-start; /* 左对齐 */
}

.right-aligned {
  display: flex;
  justify-content: flex-end; /* 右对齐 */
}
.floating-main {
  background-color: #d9ecff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 10px;
}
.timeimputstyles {
  top:3px;
  width:"15%";
}
</style>