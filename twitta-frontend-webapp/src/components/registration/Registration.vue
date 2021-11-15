<template>
  <div>
    <validation-observer
      ref="observer"
      v-slot="{ invalid }"
    >
      <v-form @submit.prevent="submit">
        <v-container>
          <v-row>
            <v-col
              cols="12"
            >
              <ValidationProvider
                v-slot="{ errors }"
                name="Username"
                rules="required|max:10"
              >
                <v-text-field
                  v-model="username"
                  :counter="10"
                  :error-messages="errors"
                  label="Username"
                  required
                ></v-text-field>
              </ValidationProvider>
            </v-col>

            <v-col
              cols="12"
            >
              <ValidationProvider
                v-slot="{ errors }"
                name="Email"
                rules="required|email"
              >
                <v-text-field
                  v-model="email"
                  :error-messages="errors"
                  label="E-mail"
                  required
                ></v-text-field>
              </ValidationProvider>
            </v-col>

            <v-col
              cols="12"
            >
              <ValidationProvider
                v-slot="{ errors }"
                name="Password"
                :rules="{
                  required: true,
                  min: 6,
                  regex: '^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}$'
                }"
              >
                <v-text-field
                  v-model="password"
                  :error-messages="errors"
                  label="Password"
                  required
                  :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                  :type="showPassword ? 'text' : 'password'"
                  counter
                  @click:append="showPassword = !showPassword"
                >
                </v-text-field>
              </ValidationProvider>
            </v-col>
            <v-col
              cols="12"
              class="d-flex justify-end"
            >
              <v-btn
                type="submit"
                color="primary"
                elevation="2"
                :disabled="invalid"
              >
                Register
              </v-btn>
            </v-col>
          </v-row>
        </v-container>
      </v-form>
    </validation-observer>
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import {
  required, email, max, min, regex,
} from 'vee-validate/dist/rules';
import {
  extend, ValidationObserver, ValidationProvider, setInteractionMode,
} from 'vee-validate';
import { register } from '@/api/user-service/userApi';
import { UserModel } from '@/api/models/request/UserModel';

setInteractionMode('eager');

extend('required', {
  ...required,
  message: '{_field_} can not be empty',
});

extend('max', {
  ...max,
  message: '{_field_} may not be greater than {length} characters',
});

extend('min', {
  ...min,
  message: '{_field_} may not be less than {length} characters',
});

extend('regex', {
  ...regex,
  message: '{_field_} {_value_} does not match {regex}',
});

extend('email', {
  ...email,
  message: 'Email must be valid',
});

export default Vue.extend({
  name: 'Registration',
  components: {
    ValidationProvider,
    ValidationObserver,
  },
  props: {
    //
  },
  data: () => ({
    username: '',
    email: '',
    password: '',
    showPassword: false,
  }),
  methods: {
    async submit() {
      const user: UserModel = {
        username: this.username,
        email: this.email,
        password: this.password,
      };
      const response = await register(user);
      console.log(response);
      if (response.error) {
        this.clearInputs();
      }
    },
    clearInputs() {
      this.username = '';
      this.email = '';
      this.password = '';
      this.$refs.observer.reset();
    },
  },
});
</script>

<style lang="scss" scoped>

</style>
