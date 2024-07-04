<template>
  <div class="edit-container">
    <input type="password" placeholder="当前密码" />
    <input type="password" placeholder="新密码" />
    <input type="password" placeholder="确认新密码" />
    <div class="buttons">
      <button @click="save">确定</button>
      <button @click="cancel">取消</button>
      <button @click="forgotPassword">忘记密码</button>
    </div>
  </div>
</template>

<script>
export default {
  props: ['field'],
  methods: {
    save() {
      this.$emit('save');
    },
    cancel() {
      this.$emit('cancel');
    },
    forgotPassword() {
      // Implement forgot password functionality
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

button {
  margin-left: 10px;
}
</style>
