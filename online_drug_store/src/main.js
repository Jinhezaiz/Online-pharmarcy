import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import Vue3ProTable from "vue3-pro-table";


createApp(App).use(store).use(router).use(ElementPlus).use(Vue3ProTable).mount('#app')
