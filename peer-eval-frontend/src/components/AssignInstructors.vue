<template>
  <div class="bg-gray-300 flex justify-center items-center min-h-screen">
    <div class="bg-white p-6 rounded-lg shadow-md max-w-4xl w-full">
      <h1 class="text-xl text-blue-900 mb-4">Assign Instructors to Teams</h1>
      <h2 class="text-lg font-semibold mb-4">Select a Team and Assign Instructors</h2>
      <form @submit.prevent="assignInstructor" id="assignForm">
        <div class="mb-4">
          <label for="teamSelect" class="block text-sm font-medium text-gray-700">Select Team:</label>
          <select id="teamSelect" v-model="selectedTeam" class="mt-1 block w-full p-2 border-gray-300 rounded-md shadow-sm">
            <option v-for="team in teams" :key="team.id" :value="team.id">{{ team.name }}</option>
          </select>
        </div>
        <div class="mb-4">
          <label for="instructorSelect" class="block text-sm font-medium text-gray-700">Select Instructor:</label>
          <select id="instructorSelect" v-model="selectedInstructor" class="mt-1 block w-full p-2 border-gray-300 rounded-md shadow-sm">
            <option v-for="instructor in instructors" :key="instructor.id" :value="instructor.id">{{ instructor.name }}</option>
          </select>
        </div>
        <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Assign Instructor</button>
      </form>
      <div id="responseMessage" class="mt-4 text-center">{{ responseMessage }}</div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      teams: [],
      instructors: [],
      selectedTeam: '',
      selectedInstructor: '',
      responseMessage: ''
    };
  },
  mounted() {
    this.loadTeams();
    this.loadInstructors();
  },
  methods: {
    loadTeams() {
      axios.get('/api/teams').then(response => {
        this.teams = response.data;
      }).catch(error => {
        console.error('Error loading teams:', error);
      });
    },
    loadInstructors() {
      axios.get('/api/instructors').then(response => {
        this.instructors = response.data;
      }).catch(error => {
        console.error('Error loading instructors:', error);
      });
    },
    assignInstructor() {
      axios.post('/api/assign', {
        teamId: this.selectedTeam,
        instructorId: this.selectedInstructor
      })
      .then(response => {
        this.responseMessage = 'Instructor assigned successfully!';
      })
      .catch(error => {
        this.responseMessage = 'Error assigning instructor: ' + error.response.data.message;
      });
    }
  }
};
</script>

<style scoped>
</style>
