import {createRouter, createWebHashHistory} from 'vue-router'
import LoginView from '@/views/Login.vue'
import RegisterView from '@/views/Register.vue'
import BossView from '@/views/Boss.vue'
import CustomerView from '@/views/Customer.vue'
import DrugDoctorView from "@/views/DrugDoctor.vue";
import {ElMessage} from "element-plus";
import store from "@/store";


const routes = [
    {
        path: "/",
        redirect: "/login"
    },
    {
        path: "/login",
        component: LoginView
    },
    {
        path: "/register",
        component: RegisterView
    },
    {
        path: "/boss",
        component: BossView,
        children:
            [
                {
                    path: "index",
                    component: () =>
                        import("@/components/index.vue")
                },
                {
                    path: "storemanage",
                    component: () =>
                        import("@/components/boss/storeMessageManage.vue")
                },
                {
                    path: "doctormanage",
                    component: () => import("@/components/boss/drugDoctorManage.vue")
                },
                {
                    path: "transfermanage",
                    component: () => import("@/components/boss/transferManage.vue")
                },
                {
                    path: "rolemanage",
                    component: () => import("@/components/boss/roleManage.vue")
                },
                {
                    path: "rolemenumanage",
                    component: () => import("@/components/boss/roleMenuManage.vue")
                },
                {
                    path: "doctorrolemanage",
                    component: () => import("@/components/boss/doctorRoleManage.vue")
                },
                {
                    path:"drugmanage",
                    component:()=>import("@/components/boss/drugManage.vue")
                }
            ]
    },
    {
        path: '/customer',
        component: CustomerView,
        children:
            [
                {
                    path: "index",
                    component: () =>
                        import("@/components/index.vue")
                },
                {
                    path: "storemessage",
                    component: () => import("@/components/customer/StoreMessage.vue")
                },
                {
                    path: "drugmessage",
                    component: () => import("@/components/customer/DrugMessage.vue")
                },
                {
                    path: "person",
                    component: () => import("@/components/customer/personMessageManage.vue")
                }
            ]
    },
    {
        path: '/drugdoctor',
        component: DrugDoctorView,
        children:
            [
                {
                    path: "index",
                    component: () =>
                        import("@/components/index.vue")
                },
                {
                    path:"doctormanage",
                    component:()=>import("@/components/drugdoctor/drugDoctorManage.vue")
                },
                {
                    path:"storemanage",
                    component:()=>import("@/components/drugdoctor/storeMessageManage.vue")
                },
                {
                    path:"transfermanage",
                    component:()=>import("@/components/drugdoctor/transferManage.vue")
                },
                {
                    path:"drugmanage",
                    component:()=>import("@/components/drugdoctor/drugManage.vue")
                }
            ]
    }

]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})
router.beforeEach((to, from, next) => {
    if (to.path == '/login' || to.path == '/register') {
        // 登录或者注册才可以往下进行
        next();
    } else {
        // 获取 token
        const token = store.state.token;
        // token 不存在
        if (token === null || token === '') {
            ElMessage.error('您还没有登录，请先登录');
            next('/login');
        } else {
            next();
        }
    }
})


export default router
