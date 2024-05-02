<script>
import Layout from './Home/Layout.vue';
export default {
  components: { Layout },
};
</script>

<template >
    <Layout class="layout"></Layout>
</template>

<style scoped>

.layout {
  height: 100vh; /* Set height to 100% of viewport height */
  width: 100vw; /* Set width to 100% of viewport width */
  background-color: #f3f3f3;
}

</style>
