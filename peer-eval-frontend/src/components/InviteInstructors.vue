<template>
  <div class="bg-gray-300 flex justify-center items-center min-h-screen">
    <form @submit.prevent="sendInvitations" class="bg-white p-6 rounded-lg shadow-md my-8 w-full max-w-4xl">
      <div class="mb-4">
        <label for="instructorEmails" class="block text-sm font-medium text-gray-700">Instructor Emails:</label>
        <input type="text" v-model="instructorEmails" required class="mt-1 p-2 block w-full border-gray-300 rounded-md shadow-sm" placeholder="Enter emails separated by semicolons">
      </div>
      <div class="mb-4">
        <label for="emailMessage" class="block text-sm font-medium text-gray-700">Email Message:</label>
        <textarea v-model="emailMessage" rows="6" class="mt-1 p-2 block w-full border-gray-300 rounded-md shadow-sm" placeholder="Email message to send"></textarea>
      </div>
      <button type="submit" class="mt-4 w-full bg-blue-500 text-white p-2 rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-opacity-50">Send Invitations</button>
      <div id="responseMessage" class="text-center mt-4">{{ responseMessage }}</div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      instructorEmails: '',
      emailMessage: `Hello,\n\n[Name of the Admin] has invited you to join The Peer Evaluation Tool. To complete your registration please use the link below:\n\n[Registration link]\n\nIf you have any questions or need assistance feel free to contact [Admin’s email] or our team directly.\n\nPlease note: This email is not monitored so do not reply directly to this message.\n\nBest regards,\nPeer Evaluation Tool Team`,
      responseMessage: ''
    };
  },
  methods: {
    sendInvitations() {
      const emails = this.instructorEmails.split(';').map(email => email.trim());
      fetch('/email/sendInvitations', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ instructorEmails: emails, message: this.emailMessage })
      })
      .then(response => response.text())
      .then(data => {
        this.responseMessage = 'Invitations sent successfully!';
      })
      .catch(error => {
        this.responseMessage = 'Error sending invitations: ' + error.message;
      });
    }
  }
};
</script>

<style scoped>
</style>
