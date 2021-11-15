<template>
  <div>
    <MessageCardAtom
      v-if="errorMessage !== ''"
      :message="errorMessage"
    />
    <v-form @submit.prevent="login">
      <v-container>
        <v-row>
          <v-col
            cols="12"
          >
            <v-text-field
              v-model="email"
              label="E-mail"
              required
            />
          </v-col>

          <v-col
            cols="12"
          >
            <v-text-field
              v-model="password"
              label="Password"
              required
              :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
              :type="showPassword ? 'text' : 'password'"
              @click:append="showPassword = !showPassword"
            />
          </v-col>
          <v-col
            cols="12"
            class="d-flex justify-end"
          >
            <v-btn
              type="submit"
              color="primary"
              elevation="2"
            >
              Login
            </v-btn>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import { login } from '@/api/user-service/userApi';
import { LoginUserModel } from '@/api/models/request/LoginUserModel';
import MessageCardAtom from '@/components/atom/MessageCardAtom.vue';

export default Vue.extend({
  name: 'Login',
  components: { MessageCardAtom },
  props: {
    //
  },
  data: () => ({
    email: '',
    password: '',
    showPassword: false,
    errorMessage: '',
  }),
  methods: {
    async login() {
      this.errorMessage = '';
      const user: LoginUserModel = {
        email: this.email,
        password: this.password,
      };
      const response = await login(user);
      console.log(response);
      if (response.token) {
        await this.$router.push('home');
      } else if (response.error && response.message) {
        const { message } = response;
        this.errorMessage = message;
        this.password = '';
      }
    },
  },
});
</script>

<style lang="scss" scoped>

</style>
