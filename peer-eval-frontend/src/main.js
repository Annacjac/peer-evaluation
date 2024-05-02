import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from './router' // Import the router from the router configuration file
import App from './App.vue'
import './style.css';  // Adjust the path as necessary

const app = createApp(App);

app.use(ElementPlus);
app.use(router);
app.mount('#app');
