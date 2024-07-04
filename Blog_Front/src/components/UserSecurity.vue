<template>
  <div class="user-security">
    <h2>账号与安全</h2>
    <div class="info-item" v-for="(field, index) in fields" :key="index">
      <span class="title">{{ field.label }}</span>
      <div v-if="!field.editing">
        <div class="info-content">
          <span class="value">{{ field.value }}</span>
          <button @click="editField(index)">编辑</button>
        </div>
      </div>
      <div v-else>
        <component :is="field.component" :field="field" @save="saveField(index)" @cancel="cancelEdit(index)"/>
      </div>
    </div>
  </div>
</template>

<script>
import UsernameEdit from './editors/UsernameEdit.vue';
import PasswordEdit from './editors/PasswordEdit.vue';
import NicknameEdit from './editors/NicknameEdit.vue';
import EmailEdit from './editors/EmailEdit.vue';
import PhoneEdit from './editors/PhoneEdit.vue';

export default {
  name: 'UserSecurity',
  data() {
    return {
      fields: [
        {label: '登录用户名', value: '不会取名的人', component: UsernameEdit, editing: false},
        {label: '密码', value: '*********', component: PasswordEdit, editing: false},
        {label: '显示昵称', value: '不会取名的人', component: NicknameEdit, editing: false},
        {label: '邮箱', value: '3089748482@qq.com', component: EmailEdit, editing: false},
        {label: '手机号', value: '+86 19979219353', component: PhoneEdit, editing: false},
      ]
    }
  },
  methods: {
    editField(index) {
      this.fields[index].editing = true;
    },
    saveField(index) {
      this.fields[index].editing = false;
    },
    cancelEdit(index) {
      this.fields[index].editing = false;
    }
  }
}
</script>

<style scoped>
.user-security {
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  margin-top: 20px;
}

.info-item {
  margin-bottom: 10px;
  display: flex;
  border-bottom: 1px solid #ddd;
  padding-bottom: 10px;
  flex-direction: column;
  align-items: flex-start;
  padding-left: 30px;
}

.info-item .title {
  justify-content: flex-start;
  color: #666666;
  font-size: larger;
  font-weight: bold;
  font-style: italic;
}

.info-item .value {
  color: #333333;
  font-size: x-small;

}

.info-content {
  display: flex;
  align-items: center;
  gap: 10px;
}

button {
  border-radius: 8px;
  background-color: #1E90FF;
  color: #fff;
  justify-content: right;
}
</style>
