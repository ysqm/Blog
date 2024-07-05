<template>
  <div class="user-security">
    <!-- Section title -->
    <h2>账号与安全</h2>

    <!-- Loop through each field and render its content or editor -->
    <div class="info-item" v-for="(field, index) in fields" :key="index">
      <span class="title">{{ field.label }}</span>
      <div v-if="!field.editing">
        <div class="info-content">
          <!-- Display field value -->
          <span class="value">{{ field.value }}</span>
          <!-- Edit button -->
          <button @click="editField(index)">编辑</button>
        </div>
      </div>
      <div v-else>
        <!-- Render the appropriate component for editing the field -->
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
        { label: '登录用户名', value: '不会取名的人', component: UsernameEdit, editing: false },
        { label: '密码', value: '*********', component: PasswordEdit, editing: false },
        { label: '显示昵称', value: '不会取名的人', component: NicknameEdit, editing: false },
        { label: '邮箱', value: '3089748482@qq.com', component: EmailEdit, editing: false },
        { label: '手机号', value: '+86 19979219353', component: PhoneEdit, editing: false },
      ]
    }
  },
  methods: {
    editField(index) {
      this.fields[index].editing = true; // Enable editing mode for the field
    },
    saveField(index) {
      this.fields[index].editing = false; // Disable editing mode after saving
    },
    cancelEdit(index) {
      this.fields[index].editing = false; // Cancel editing mode
    }
  }
}
</script>

<style scoped>
/* User security section styling */
.user-security {
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  margin-top: 20px;
}

/* Individual field item styling */
.info-item {
  margin-bottom: 10px;
  display: flex;
  border-bottom: 1px solid #ddd;
  padding-bottom: 10px;
  flex-direction: column;
  align-items: flex-start;
  padding-left: 30px;
}

/* Field title styling */
.info-item .title {
  justify-content: flex-start;
  color: #666666;
  font-size: larger;
  font-weight: bold;
  font-style: italic;
}

/* Field value styling */
.info-item .value {
  color: #333333;
  font-size: x-small;
}

/* Info content styling */
.info-content {
  display: flex;
  align-items: center;
  gap: 10px;
}

/* Button styling */
button {
  border-radius: 8px;
  background-color: #1E90FF;
  color: #fff;
  border: none;
  padding: 5px 10px;
  cursor: pointer;
}

button:hover {
  background-color: #1C86EE;
}
</style>
