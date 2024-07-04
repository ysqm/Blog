<template>
  <div class="account-center">
    <UserSidebar />
    <AccountSettings />
  </div>
</template>

<script>
import UserSidebar from "../components/UserSideBar.vue"
import AccountSettings from "../components/AccountSettings.vue";

export default {
  name: 'AccountCenter',
  components: {
    UserSidebar,
    AccountSettings
  }
}
</script>

<style scoped>
.account-center {
  display: flex;
  padding: 20px;
}
</style>
