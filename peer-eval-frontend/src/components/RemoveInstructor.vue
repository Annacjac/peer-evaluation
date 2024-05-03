<template>
  <div class="container mx-auto px-4 bg-gray-100 min-h-screen">
    <h1 class="text-xl font-bold text-center my-4">Remove Instructor from Team</h1>
    <form @submit.prevent="removeInstructor" class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
      <div class="mb-4">
        <label class="block text-gray-700 text-sm font-bold mb-2" for="teamId">
          Select Team
        </label>
        <select id="teamId" v-model="selectedTeam" @change="loadInstructorsForTeam" class="block appearance-none w-full bg-white border border-gray-200 text-gray-700 py-2 px-3 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
          <option v-for="team in teams" :key="team.id" :value="team.id">{{ team.name }}</option>
        </select>
      </div>
      <div class="mb-4">
        <label class="block text-gray-700 text-sm font-bold mb-2" for="instructorId">
          Select Instructor to Remove
        </label>
        <select id="instructorId" v-model="selectedInstructor" class="block appearance-none w-full bg-white border border-gray-200 text-gray-700 py-2 px-3 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500">
          <option v-for="instructor in instructors" :key="instructor.id" :value="instructor.id">{{ instructor.firstName }} {{ instructor.lastName }}</option>
        </select>
      </div>
      <div class="flex items-center justify-between">
        <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="submit">
          Remove Instructor
        </button>
      </div>
    </form>
    <p id="response" class="text-center">{{ responseMessage }}</p>
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
  },
  methods: {
    async loadTeams() {
      try {
        const response = await axios.get('/api/teams');
        this.teams = response.data;
        this.selectedTeam = this.teams[0]?.id || '';
        this.loadInstructorsForTeam();
      } catch (error) {
        this.responseMessage = 'Error loading teams: ' + error.message;
      }
    },
    async loadInstructorsForTeam() {
      if (!this.selectedTeam) return;
      try {
        const response = await axios.get(`/api/teams/${this.selectedTeam}/instructors`);
        this.instructors = response.data;
        this.selectedInstructor = this.instructors[0]?.id || '';
      } catch (error) {
        this.responseMessage = 'Error loading instructors: ' + error.message;
      }
    },
    async removeInstructor() {
      if (!this.selectedTeam || !this.selectedInstructor) return;
      try {
        const response = await axios.delete(`/api/teams/${this.selectedTeam}/remove-instructor/${this.selectedInstructor}`);
        this.responseMessage = response.data.message || 'Instructor removed successfully!';
      } catch (error) {
        this.responseMessage = 'Error removing instructor: ' + (error.response?.data?.message || error.message);
      }
    }
  }
};
</script>

<style scoped>
</style>
