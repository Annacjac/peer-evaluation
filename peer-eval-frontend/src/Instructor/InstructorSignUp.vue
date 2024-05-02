<template>
    <div class = "signuppage">
      <h2>Sign Up as an Instructor</h2>
      <form @submit.prevent="registerInstructor">
        <label for="firstName">First Name:</label>
        <input type="text" v-model="firstName" required>
  
        <label for="middleInitial">Middle Initial:</label>
        <input type="text" v-model="middleInitial">
  
        <label for="lastName">Last Name:</label>
        <input type="text" v-model="lastName" required>
  
        <label for="password">Password:</label>
        <input type="password" v-model="password" required>
  
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" v-model="confirmPassword" required>
  
        <button type="submit">Sign Up</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    name: 'InstructorSignUp',
    data() {
      return {
        firstName: '',
        middleInitial: '',
        lastName: '',
        password: '',
        confirmPassword: ''
      };
    },
    methods: {
      registerInstructor() {
        // Call backend API to register instructor
        if (this.password !== this.confirmPassword) {
          alert('Passwords do not match');
          return;
        }
        // Call backend API to register instructor with this.firstName, this.middleInitial, this.lastName, this.password
        // Redirect to login page after successful registration
      }
    }
  }
  </script>
  


<style scoped>
    .signuppage {
    display: flex;
    flex-direction: column;
    align-items: center;
}
    h2 {
        font-size: 24px;
        margin-bottom: 20px;
    }

    form {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
    }

    label {
        font-weight: bold;
        margin-bottom: 10px;
    }

    input {
        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        
    }

    button {
        padding: 10px 20px;
        background-color: #BC70EE;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    button:hover {
        background-color: #6A06AC;
    }
</style>
  