<template>
    <el-button style='margin: 20px'>Add Team</el-button>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column fixed="left" prop="id" label="ID" width="50" />
      <el-table-column fixed="left" prop="name" label="Team Name" width="3000" />
      <el-table-column fixed="right" prop="generateWarReport" label="Generate WAR Report" width="200">
        <template #default>
          <el-button link type="primary" size="small" @click="handleClick">Generate WAR Report</el-button>
        </template>
      </el-table-column>
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
  const handleClick = () => {
    console.log('click')
  }
  
  const tableData = [
    {
      id: '1',
      name: 'Tom',
      state: 'California',
      city: 'Los Angeles',
      address: 'No. 189, Grove St, Los Angeles',
      zip: 'CA 90036',
      tag: 'Home',
    },
    {
      id: '2',
      name: 'Tom',
      state: 'California',
      city: 'Los Angeles',
      address: 'No. 189, Grove St, Los Angeles',
      zip: 'CA 90036',
      tag: 'Office',
    },
    {
      id: '3',
      name: 'Tom',
      state: 'California',
      city: 'Los Angeles',
      address: 'No. 189, Grove St, Los Angeles',
      zip: 'CA 90036',
      tag: 'Home',
    },
    {
      id: '4',
      name: 'Tom',
      state: 'California',
      city: 'Los Angeles',
      address: 'No. 189, Grove St, Los Angeles',
      zip: 'CA 90036',
      tag: 'Office',
    },
  ]
  </script>
    <style></style>
