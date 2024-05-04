<template>
  <el-button style='margin: 20px'>Add Section</el-button>
  <el-button style='margin: 20px' @click="showDialog = true">Generate Rubric</el-button>

  <el-dialog
      title="Create Rubric"
      :visible.sync="showDialog"
      width="30%"
      @close="handleClose">
    <div>
      <span>Name of Rubric:</span>
      <el-input v-model="rubricName" placeholder="Enter rubric name"></el-input>
    </div>
    <span slot="footer" class="dialog-footer">
            <el-button @click="showDialog = false">Cancel</el-button>
            <el-button type="primary" @click="createRubric">Create</el-button>
        </span>
  </el-dialog>

  <el-table :data="tableData" style="width: 100%">
    <el-table-column fixed prop="id" label="Id" width="150" />
    <el-table-column prop="name" label="Section Name" width="120" />
    <el-table-column prop="academicYear" label="Academic Year" width="120" />
    <el-table-column prop="instructor" label="Instructor" width="120" />
    <el-table-column fixed="right" label="Operations" width="120">
      <template #default>
        <el-button link type="primary" size="small" @click="handleClick">
          Detail
        </el-button>
        <el-button link type="primary" size="small">Edit</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script lang="ts" setup>
import { ref } from 'vue';

const showDialog = ref(false);
const rubricName = ref('');

const handleClick = () => {
  console.log('click');
}

const handleClose = () => {
  console.log('Dialog closed');
}

const createRubric = () => {
  console.log(`Creating rubric: ${rubricName.value}`);
  showDialog.value = false;
}

const tableData = [
  {
    id: "1",
    name: 'Section-1',
    academicYear: '2023-2024',
    instructor: 'Dr. Helms',
  },
  {
    id: "2",
    name: 'Section-2',
    academicYear: '2023-2024',
    instructor: 'Dr. Wei',
  },
  {
    id: "3",
    name: 'Section-3',
    academicYear: '2023-2024',
    instructor: 'Dr. Ma',
  },
]
</script>

<style>
/* Add styles if needed */
</style>
