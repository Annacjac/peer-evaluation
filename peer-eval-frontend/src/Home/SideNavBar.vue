<template >
    <el-row class="tac" >
      <el-col :span="24">
        <!-- <h5 class="mb-2">Peer Evaluation</h5> -->
        <el-menu
          default-active="2"
          active-text-color="#BC70EE"

          background-color="#f3f3f3"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
        >
          <!-- Router Links Added -->
          <el-menu-item index="5">Evaluation
            <!-- <router-link :to="{ name: 'evaluation' }">Evaluation</router-link> -->
          </el-menu-item>
          <el-menu-item index="6">
            <router-link :to="{ name: 'home' }">Home</router-link>
          </el-menu-item>
          <el-menu-item index="7">Student
            <!-- <router-link :to="{ name: 'student' }">Student Page</router-link> -->
          </el-menu-item>
          <el-menu-item index="8">Team
            <!-- <router-link :to="{ name: 'admin' }">My Team</router-link> -->
          </el-menu-item>
          <el-sub-menu index="4"><template #title>
            <span>Sign up / Sign in</span>
          </template>
                <el-menu-item index="1-1">
                    <router-link :to="{ name: 'studentsignup' }">as Student</router-link>
                </el-menu-item>
                <el-menu-item index="1-2">
                    <router-link :to="{ name: 'instructorsignup' }">as Instructor</router-link>
                </el-menu-item>
                <el-menu-item index="1-3">as Admin 
                    <!-- <router-link :to="{ name: 'adminsignup' }">My Team</router-link> -->
                </el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-col>
    </el-row>
  </template>
  
<script>
import {
    Document,
    Menu as IconMenu,
    Location,
    Setting,
} from '@element-plus/icons-vue'

const handleOpen = (key, keyPath) => {
    console.log(key, keyPath)
}

const handleClose = (key, keyPath) => {
    console.log(key, keyPath)
}

</script>

<style scoped>
/* Apply styles deep into the child components */
::v-deep .router-link,
::v-deep .router-link-active,
::v-deep .router-link-exact-active {
    text-decoration: none;
    font-weight: 550;

    color: inherit;}
    a {
    text-color: none; 
    text-decoration: none;
    color: inherit;
}
</style>

