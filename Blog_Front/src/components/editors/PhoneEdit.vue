<template>
  <div class="edit-container">
    <input v-model="field.tempValue" placeholder="请输入新的手机号" />
    <input placeholder="验证码" />
    <button>获取验证码</button>
    <div class="buttons">
      <button @click="save">确定</button>
      <button @click="cancel">取消</button>
    </div>
  </div>
</template>

<script>
export default {
  props: ['field'],
  data() {
    return {
      field: {
        ...this.field,
        tempValue: this.field.value
      }
    }
  },
  methods: {
    save() {
      this.$emit('save');
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
}

.buttons {
  display: flex;
  justify-content: flex-end;
  margin-top: 10px;
}
</style>
