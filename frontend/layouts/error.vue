<template>
  <div class="container">
    <div v-if="error.statusCode === 404">
      <b-img
          src="/img/art/404.png"
          alt="404 Illustration"
          fluid
      />
      <h1 class="text-warning">
        Opps!, something went wrong
      </h1>
      <p>We could not find that page you were looking for</p>
    </div>
    <h1 v-else>
      An error occurred
    </h1>
    <nuxt-link to="/">
      Go Home => 🏠
    </nuxt-link>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'

export default Vue.extend({
  layout: 'error',
  props: {
    error: {
      type: Object,
      required: true
    }
  }
})
</script>
<style scoped>
.container {
  margin-top: 20px;
}

img {
  margin: auto;
  width: 50%;
  height: 50%;
}
</style>
