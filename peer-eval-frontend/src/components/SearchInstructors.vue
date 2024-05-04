<template>
    <div class="max-w-xl mx-auto mt-10 bg-white p-8 border border-gray-300 rounded-lg">
      <h2 class="text-xl font-bold mb-4">Search Instructors</h2>
      <form @submit.prevent="searchInstructors">
        <div class="mb-4">
          <label for="searchQuery" class="block text-sm font-medium text-gray-700">Search Query:</label>
          <input type="text" id="searchQuery" v-model="searchQuery" class="mt-1 block w-full p-2 border border-gray-300 rounded-md" placeholder="Enter name, email, or ID">
        </div>
        <button type="submit" class="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600">Search</button>
      </form>
      <div v-if="instructors.length > 0" class="mt-6">
        <h3 class="text-lg font-semibold">Results:</h3>
        <ul>
          <li v-for="instructor in instructors" :key="instructor.id" class="mt-2">
            {{ instructor.name }} - {{ instructor.email }}
          </li>
        </ul>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'SearchInstructors',
    data() {
      return {
        searchQuery: '',
        instructors: []
      };
    },
    methods: {
      searchInstructors() {
        axios.get(`/api/search-instructors?query=${encodeURIComponent(this.searchQuery)}`)
          .then(response => {
            this.instructors = response.data;
          })
          .catch(error => {
            console.error('Error searching instructors:', error);
            alert('Failed to search instructors. Please try again.');
          });
      }
    }
  };
  </script>
  
  <style scoped>
  </style>
  