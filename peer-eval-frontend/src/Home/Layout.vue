<script>
import SideNavBar from './SideNavBar.vue';
export default {
  components: { SideNavBar },
};
</script>

<template>
    <div class="common-layout">
      <el-container>
        <el-header style="text-align: center; height: 100px"><h2>Peer Evaluation</h2></el-header>
        <el-container style="height: 100vh;">
          <el-aside width="200px"><SideNavBar /></el-aside>
          <el-container>
            <el-main><router-view></router-view></el-main>
          </el-container>
        </el-container>
      </el-container>
    </div>
  </template>
<script></script>
<style>
.router-view {
  background-color: #f3f3f3;
  padding: 20px;
}
</style>