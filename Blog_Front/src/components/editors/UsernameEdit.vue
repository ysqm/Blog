<template>
  <div class="edit-container">
    <input v-model="field.tempValue" placeholder="请输入新的用户名" />
    <label><input type="checkbox" /> 使用邮箱作为登录用户名</label>
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
