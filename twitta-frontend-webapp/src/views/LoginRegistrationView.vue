<template>
  <div class="center">
    <v-row>
      <v-col
        class="text-center left-part"
        cols="6"
      >
        <h1>Welcome to <br> Twitta</h1>
      </v-col>
      <v-col
        cols="6"
        class="right-part"
      >
        <div class="navigation">
          <router-link to="register">Register</router-link>
          <router-link to="login">Login</router-link>
        </div>
        <router-view></router-view>
      </v-col>
    </v-row>
  </div>
</template>

<script lang="ts">
import Vue from 'vue';

export default Vue.extend({
  name: 'LoginRegistrationView',
  props: {
    //
  },
  data: () => ({
    //
  }),
  methods: {
    //
  },
});
</script>

<style lang="scss" scoped>
.center {
  background-color: #E5E5E5;
  padding: 4rem;
  border-radius: 16px;
  position: absolute;
  width: 80%;
  height: 60%;
  left: 50%;
  top: 50%;
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}

.left-part {
  border-right: 2px solid #BEBEBE;
}

.right-part {
  max-width: 400px;
}

.navigation > * {
  margin: 1rem 1rem 0 12px;
}

</style>
