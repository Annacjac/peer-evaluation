<template>
    <el-button style='margin-bottom: 10px'>Add Student</el-button>
    <el-button style="margin-left: 10px; margin-bottom: 10px" >Assign Student</el-button>

    <el-dialog :visible.sync="showAssignModel" title="Assign Students to Team" width="600px">
      <div v-if="!selectedTeam">
        <h3>Select a Team:</h3>
        <el-list
          v-for="team in teams"
          :key="team.id"
          :bordered="false"
          @click.native="selectTeam(team)"
          style="cursor: pointer; margin-bottom: 10px;">
        <el-list-item>{{ team.name }}</el-list-item>
      </el-list>
    </div>
      <div v-if="selectedTeam">
        <h3>Selected Team: {{ selectedTeam.name }}</h3>
        <h3>Select Students:</h3>
        <el-checkbox-group v-model="selectedStudentIds">
          <el-checkbox
              v-for="student in students"
              :label="student.name"
              :key="student.id"
              :value="student.id">
            {{ student.name }}
          </el-checkbox>
        </el-checkbox-group>
      </div>

      <span slot="footer" class="dialog-footer">
        <el-button @click="resetAndClose">Cancel</el-button>
        <el-button type="primary" @click="assignStudents" :disabled="!selectedTeam || selectedStudentIds.length === 0">Assign</el-button>
      </span>
    </el-dialog>

  <el-table :data="tableData" style="width: 100%">
    <el-table-column fixed="left" prop="id" label="ID" width="50" />
    <el-table-column fixed="left" prop="name" label="Name" width="120" />
    <el-table-column prop="year" label="Year" width="120" />
    <el-table-column prop="teamName" label="Team Name" width="120" />
    <el-table-column prop="sectionName" label="Section Name" width="120" />
    <el-table-column prop="email" label="Email" width="1000" />
    <el-table-column fixed="right" prop="generatePeerEvaluation" label="Generate Peer Evaluation" width="200" >
      <template #default>
        <el-button link type="primary" size="small" @click="handleClick">Generate Peer Evaluation</el-button>
      </template>
    </el-table-column>
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
  import axios from 'axios';
  import {ref, reactive, onMounted} from "vue";

  const handleClick = () => {
    console.log('click')
  }

  const showAssignModel = ref(false);
  const teams = ref([]);
  const students = ref([]);
  const selectedTeam = ref(null);
  const selectedStudentIds = ref([]);

  const tableData = ref([
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
  ]);

  const fetchTeams = async () => {
    try{
      const response = await axios.get('/api/teams')
      teams.value = response.data;
    }
    catch(error){
      console.error('Error fetching teams - ', error)
    }
  };
  const fetchStudents = async () => {
    try{
      const response = await axios.get('api/students')
      students.value = response.data;
    }
    catch(error) {
      console.error('Error fetching students - ', error)
    }
  };
  const selectTeam = (team) => {
    selectedTeam.value = team;
  };


  const assignStudents = async () => {
    try{
      const response = await axios.post('api/student/assign-student', {
        studentIds: selectedStudentIds.value,
        teamId: selectedTeam.value.id
      });
      alert('Students successfully assigned.');
      resetAndClose();
    }
    catch(error){
      console.error('Error assigning students - ', error)
    }
  };
  const resetAndClose = () => {
    showAssignModel.value = false;
    selectedTeam.value = null;
    selectedStudentIds.value = [];
  };
  onMounted(() => {
    fetchTeams();
    fetchStudents();
  });


  </script>
    <style></style>
