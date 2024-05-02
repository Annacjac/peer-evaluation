<template>
    <div class="signuppage">
      <h2>Sign Up as a Student</h2>
      <form @submit.prevent="registerStudent">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" v-model="firstName" required>

        <label for="middleInitial">Middle Initial:</label>
        <input type="text" id="middleInitial" v-model="middleInitial">

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" v-model="lastName" required>

        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required>

        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" v-model="confirmPassword" required>

        <button type="submit">Sign Up</button>
      </form>
    </div>
</template>

<script>
export default {
  name: 'StudentSignUp',
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
    registerStudent() {
      // Call backend API to register student
      if (this.password !== this.confirmPassword) {
        alert('Passwords do not match');
        return;
      }
      // Simulated API call placeholder
      console.log("Registering student:", this.firstName, this.middleInitial, this.lastName, this.password);
      // Redirect to login page after successful registration
      // this.$router.push('/login');
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
