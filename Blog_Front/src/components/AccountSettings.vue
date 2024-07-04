<template>
  <div class="account-settings">
    <UserProfile />
    <UserSecurity />
  </div>
</template>

<script>
import UserProfile from './UserProfile.vue'
import UserSecurity from './UserSecurity.vue'

export default {
  name: 'AccountSettings',
  components: {
    UserProfile,
    UserSecurity
  }
}
</script>

<style scoped>
.account-settings {
  padding: 20px;
  flex: 1;
}
</style>
