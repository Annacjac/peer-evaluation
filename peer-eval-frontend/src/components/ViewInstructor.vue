<template>
    <div class="max-w-md mx-auto mt-10 bg-white p-8 border border-gray-300 rounded-lg">
      <h2 class="text-xl font-bold mb-4">View Instructor Details</h2>
      <form @submit.prevent="fetchInstructor">
        <div class="mb-4">
          <label for="instructorId" class="block text-sm font-medium text-gray-700">Instructor ID:</label>
          <input type="number" id="instructorId" v-model.number="instructorId" class="mt-1 block w-full p-2 border border-gray-300 rounded-md" placeholder="Enter instructor ID" required>
        </div>
        <button type="submit" class="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600">Search</button>
      </form>
      <div v-if="instructor" class="mt-6">
        <h3 class="text-lg font-semibold">Instructor Details:</h3>
        <p><strong>Name:</strong> {{ instructor.name }}</p>
        <p><strong>Email:</strong> {{ instructor.email }}</p>
        <p><strong>Department:</strong> {{ instructor.department }}</p>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'ViewInstructor',
    data() {
      return {
        instructorId: '',
        instructor: null
      };
    },
    methods: {
      fetchInstructor() {
        axios.get(`/api/instructors/${this.instructorId}`)
          .then(response => {
            this.instructor = response.data;
          })
          .catch(error => {
            console.error('Error fetching instructor details:', error);
            alert('Failed to fetch instructor details. Please check the ID and try again.');
            this.instructor = null;
          });
      }
    }
  };
  </script>
  
  <style scoped>
  </style>
  