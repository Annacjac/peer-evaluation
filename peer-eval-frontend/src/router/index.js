import { createRouter, createWebHistory } from 'vue-router';
import Home from '@/Home/Home.vue';
import InstructorSignUp from '@/Instructor/InstructorSignUp.vue'; 
import StudentSignUp from '@/student/StudentSignUp.vue'; 
import StudentList from '@/student/StudentList.vue';
import TeamList from '@/Team/TeamList.vue';
import SectionList from '@/Sections/SectionList.vue';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/home',
      name: 'home',
      component: Home
    },
    {
      path: '/instructor-sign-up', 
      name: 'instructorsignup',
      component: InstructorSignUp
    },
    {
        path: '/student-sign-up', 
        name: 'studentsignup',
        component: StudentSignUp
    },
    {
        path: '/student-list', 
        name: 'studentlist',
        component: StudentList
    },
    {
        path: '/team-list', 
        name: 'teamlist',
        component: TeamList
    },
    {
        path: '/section-list', 
        name: 'sectionlist',
        component: SectionList
    }
  ]
});

export default router;
