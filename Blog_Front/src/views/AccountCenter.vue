<template>
  <div class="account-center">
    <UserSidebar />
    <AccountSettings class="accountSetting"/>
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
.accountSetting {
  top: 0;
  right: 0;
  position: absolute;
  width: 75%;
}

</style>
