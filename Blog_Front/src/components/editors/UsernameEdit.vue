<template>
  <div class="edit-container">
    <input
        v-model="field.tempValue"
        :placeholder="isEmailChecked ? '邮箱将用作登录用户名' : '请输入新的用户名'"
        :disabled="isEmailChecked"
    />
    <label>
      <input type="checkbox" v-model="isEmailChecked" @change="handleEmailCheckboxChange" /> 使用邮箱作为登录用户名
    </label>
    <div class="buttons">
      <button @click="updateUsername">确定</button>
      <button @click="cancel">取消</button>
    </div>
  </div>
</template>


<script>
// import {edit, updateUsername} from "@/api/user.ts"; // 引入axios以发送HTTP请求

export default {
  props: ['field'],
  data() {
    return {
      field: {
        ...this.field,
        tempValue: this.field.value
      },
      isEmailChecked: false // 增加一个状态变量
    }
  },
  methods: {
    handleEmailCheckboxChange() {
      if (this.isEmailChecked) {
        this.field.tempValue = this.field.email; // 假设字段中有邮箱信息
        console.log(this.field.tempValue);
      } else {
        this.field.tempValue = this.field.value;
      }
    },
    updateUsername() {
    //   const payload = {
    //     value: this.isEmailChecked ? this.field.email : this.field.tempValue
    //   };
    //   edit(this.field.tempValue, this.field.id)
    //       .catch(error => {
    //         console.error('保存失败:', error);
    //       });
    //   this.$emit('update');
      this.$emit('update');
    },
    cancel() {
      this.$emit('cancel');
    }
  }
}
</script>


<style scoped>
.edit-container {
  display: flex;
  flex-direction: column;
  gap:5px
}

.edit-container input {
  border-radius: 8px;
  padding: 5px;
}

.buttons {
  display: flex;
  justify-content: flex-end;
  margin-top: 10px;
}
.buttons button {
  margin-left: 10px;
  border-radius: 8px;
  background-color: #1E90FF;
  color: #fff;
}
</style>
