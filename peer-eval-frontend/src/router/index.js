import { createRouter, createWebHistory } from 'vue-router';
import Home from '@/Home/Home.vue';
import InstructorSignUp from '@/Instructor/InstructorSignUp.vue'; 
import StudentSignUp from '@/student/StudentSignUp.vue'; 

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/instructor-sign-up', // Adding a new route for instructor sign up
      name: 'instructorsignup',
      component: InstructorSignUp
    },
    {
        path: '/student-sign-up', // Adding a new route for student sign up
        name: 'studentsignup',
        component: StudentSignUp
    }
  ]
});

export default router;
