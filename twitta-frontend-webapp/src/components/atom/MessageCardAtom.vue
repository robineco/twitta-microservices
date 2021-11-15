<template>
  <div class="message-card">
    <p>
      {{ message }}
    </p>
  </div>
</template>

<script lang="ts">
import Vue from 'vue';

export default Vue.extend({
  name: 'MessageCardAtom',
  props: {
    message: {
      required: true,
    },
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
.message-card {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  margin: 1rem 1rem 0 12px;
  border: 1px solid black;
  border-radius: 8px;
  height: 4rem;
}

.message-card > * {
  margin-top: auto;
  margin-bottom: auto;
  color: red;
}
</style>
