<template>
    <el-button style='margin-bottom: 20px'>Add Student</el-button>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column fixed prop="id" label="ID" width="50" />
      <el-table-column prop="name" label="Name" width="120" />
      <el-table-column prop="year" label="Year" width="120" />
      <el-table-column prop="teamName" label="Team Name" width="120" />
      <el-table-column prop="sectionName" label="Section Name" width="120" />
      <el-table-column prop="email" label="Email" width="200" />
      <el-table-column prop="generatePeerEvaluation" label="Generate Peer Evaluation" width="200" >
        <template #default>
          <el-button link type="primary" size="small" @click="handleClick">Generate Peer Evaluation</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="generateWarReport" label="Generate WAR Report" width="200">
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
      name: 'Tom L. Doe',
      year: 'Senior',
      teamName: 'Team-1',
      sectionName: 'Section-1',
      email: 'tomldoe@gmail.com',
      generatePeerEvaluation: 'Generate Peer Evaluation',
      generateWarReport:'Generate WAR Report',
      tag: 'Home',
    },
    {
      id: '2',
      name: 'Tom S. Doe',
      year: 'Senior',
      teamName: 'Team-1',
      sectionName: 'Section-1',
      email: 'tomsdoe@gmail.com',
      generatePeerEvaluation: 'Generate Peer Evaluation',
      generateWarReport:'Generate WAR Report',
      tag: 'Office',
    },
    {
      id: '3',
      name: 'Tom M. Doe',
      year: 'Senior',
      teamName: 'Team-1',
      sectionName: 'Section-1',
      email: 'tommdoe@gmail.com',
      generatePeerEvaluation: 'Generate Peer Evaluation',
      generateWarReport:'Generate WAR Report',
      tag: 'Home',
    },
    {
      id: '4',
      name: 'Tom N. Doe',
      year: 'Senior',
      teamName: 'Team-1',
      sectionName: 'Section-1',
      email: 'tomndoe@gmail.com',
      generatePeerEvaluation: 'Generate Peer Evaluation',
      generateWarReport:'Generate WAR Report',
      tag: 'Home',
    },
  ]
  </script>
    <style></style>
