<template>
  <el-button style='margin: 20px' @click="addTeam">Add Team</el-button>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column fixed="left" prop="index" label="Index" width="65">
      <template #default="scope">
        {{ scope.$index + 1 }}
      </template>
    </el-table-column>
    <el-table-column fixed="left" prop="name" label="Team Name" width="300">
      <template #default="scope">
        <el-input
          v-if="scope.row.editable"
          v-model="scope.row.name"
          ref="inputRefs"
          @keyup.enter="saveTeam(scope.row, scope.$index)"
          clearable>
        </el-input>
        <span v-else>{{ scope.row.name }}</span>
      </template>
    </el-table-column>
    <el-table-column fixed="right" prop="generateWarReport" label="Generate WAR Report" width="200">
      <template #default>
        <el-button type="primary" size="small" @click="handleClick">Generate WAR Report</el-button>
      </template>
    </el-table-column>
    <el-table-column fixed="right" label="Operations" width="180">
      <template #default="scope">
        <el-button type="primary" size="small" @click="editTeam(scope.row)">Edit</el-button>
        <el-button type="danger" size="small" @click="deleteTeam(scope.$index)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script lang="ts" setup>
import { ref, nextTick } from 'vue';
import { ElMessageBox, ElMessage } from 'element-plus';

const tableData = ref([]);

const addTeam = () => {
  if (tableData.value.some(item => item.editable && !item.name.trim())) {
    ElMessage({
      message: 'Please complete the existing entry before adding a new team.',
      type: 'error',
      duration: 2000
    });
    return;
  }

  const newRow = {
    name: '',
    editable: true
  };

  tableData.value.push(newRow);
  focusOnNewRow();
}

const focusOnNewRow = async () => {
  await nextTick();
  const inputElements = document.querySelectorAll('.el-input__inner');
  inputElements[inputElements.length - 1].focus();
}

const editTeam = (row) => {
  row.editable = true;
}

const saveTeam = (row, index) => {
  if (!row.name.trim()) {
    ElMessage({
      message: 'The team name cannot be empty!',
      type: 'error',
      duration: 2000
    });
    return;
  }

  if (tableData.value.some((item, idx) => item.name === row.name && idx !== index)) {
    ElMessage({
      message: 'This team name already exists!',
      type: 'error',
      duration: 2000
    });
    return;
  }

  row.editable = false;
}

const deleteTeam = (index) => {
  tableData.value.splice(index, 1);
}

const handleClick = () => {
  console.log('Generate report click');
}
</script>
<style scoped>
</style>
