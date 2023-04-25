<template>
    <el-container class="layout-container-demo" >
        <el-aside width="200px" class="el-aside">
            <el-scrollbar>
                <el-menu  class="el-menu"   text-color="#fff" background-color=" #313A4C">
                    <el-menu-item style="font-size:16px;font-weight: bolder">
                        <el-icon size="25"><chrome-filled/></el-icon>Online Pharmacy
                    </el-menu-item>
                    <el-sub-menu index="1">
                        <template #title>
                            <el-icon><message /></el-icon>门店信息
                        </template>
                        <el-menu-item index="1-1" @click="one_one">
                            门店信息管理
                        </el-menu-item>
                    </el-sub-menu>
                    <el-sub-menu index="2">
                        <template #title>
                            <el-icon><icon-menu /></el-icon>店员信息管理
                        </template>
                        <el-menu-item index="2-1" @click="two_one">药师信息管理</el-menu-item>
                        <el-menu-item index="2-2" @click="two_two">骑手信息管理</el-menu-item>
                    </el-sub-menu>
                    <el-sub-menu index="3">
                        <template #title>
                            <el-icon><setting /></el-icon>权限管理
                        </template>
                        <el-menu-item index="3-1" @click="three_one">角色管理</el-menu-item>
                        <el-menu-item index="3-2" @click="three_two">角色权限管理</el-menu-item>
                        <el-menu-item index="3-3" @click="three_three">药师角色管理</el-menu-item>
                    </el-sub-menu>
                    <el-sub-menu index="4">
                        <template #title>
                            <el-icon><histogram /></el-icon>药品信息
                        </template>
                        <el-menu-item index="4-1" @click="four_one">
                            药品信息管理
                        </el-menu-item>
                    </el-sub-menu>
                    <el-sub-menu index="5">
                        <template #title>
                            <el-icon><mostly-cloudy /></el-icon>订单管理
                        </template>
                        <el-menu-item index="5-1" @click="five_one">订单状态</el-menu-item>
                        <el-menu-item index="5-2" @click="five_two">订单退款</el-menu-item>
                    </el-sub-menu>
                    <el-sub-menu index="6">
                        <template #title>
                            <el-icon><list /></el-icon>工作台
                        </template>
                        <el-menu-item index="6-1" @click="six_one">零售工作台</el-menu-item>
                        <el-menu-item index="6-2" @click="six_two">线上工作台</el-menu-item>
                    </el-sub-menu>
                </el-menu>
            </el-scrollbar>
        </el-aside>
        <el-container>
            <el-header style=" font-size:15px" class="el-header">
                <div class="warn">
                    <el-icon style="margin-right: 8px; margin-top: 1px">
                        <help-filled/>
                    </el-icon>
                    {{warn}}
                </div>
                <div class="toolbar">
                    <el-dropdown>
                        <el-icon style="margin-right: 8px; margin-top: 1px">
                            <Avatar/>
                        </el-icon>
                        <template #dropdown>
                            <el-dropdown-menu>
                                <el-dropdown-item @click="returnLogin">退出登录</el-dropdown-item>
                            </el-dropdown-menu>
                        </template>
                    </el-dropdown>
                    <span>{{name}}</span>
                </div>
            </el-header>

            <el-main class="el-main">
                <el-scrollbar>
                    <router-view></router-view>
                </el-scrollbar>
            </el-main>
        </el-container>
    </el-container>
</template>

<script scope setup>
import {onBeforeMount, onMounted, ref} from 'vue'
import router from "@/router";
import store from "@/store";
import { Menu as IconMenu, Message, Setting,Avatar,Histogram,MostlyCloudy,List,ChromeFilled,HelpFilled} from '@element-plus/icons-vue'
import {ElMessageBox} from "element-plus";

const name = ref(0)
const warn = ref('首页')

onBeforeMount(()=>{
    let token = store.state.token
    let strings = token.split(".")
    let payload = JSON.parse(decodeURIComponent(escape(window.atob(strings[1]))))
    name.value = payload.name
})
function returnLogin(){
    ElMessageBox.confirm(
        '请您确认是否需要退出登录',
        '提示',
        {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
        }
    ).then(() => {
        localStorage.clear()
        store.commit('login','')
        router.push("/login")
    }).catch(()=>{
    })
}
function one_one(){
    warn.value="门店信息>门店信息管理"
    router.push("/boss/storemanage")
}
function two_one(){
    warn.value="店员信息管理>药师信息管理"
    router.push("/boss/doctormanage")
}
function two_two(){
    warn.value="店员信息管理>骑手信息管理"
    router.push("/boss/transfermanage")
}
function three_one(){
    warn.value="权限管理>角色管理"
    router.push("/boss/rolemanage")
}
function three_two(){
    warn.value="权限管理>角色权限管理"
    router.push("/boss/rolemenumanage")
}
function three_three(){
    warn.value="权限管理>药师角色管理"
    router.push("/boss/doctorrolemanage")
}
function four_one(){
    warn.value="药品信息>药品信息管理"
    router.push("/boss/drugmanage")
}
function five_one(){
    warn.value="订单管理>订单状态"
}
function five_two(){
    warn.value="订单管理>订单退款"
}
function six_one(){
    warn.value="工作台>零售工作台"
}
function six_two(){
    warn.value="工作台>线上工作台"
}
</script>

<style scoped>
.layout-container-demo{
    height: 100%;
}
.layout-container-demo .el-header {
    position: relative;
    display: flex;
    align-items: center;
    justify-content: space-between;
    color: var(--el-text-color-primary);
    height: 30px;
    padding: 10px;
    box-shadow: 0 2px 4px 0 #d3d3d3;
}
.layout-container-demo .el-aside {
    background: #313A4C
}

.layout-container-demo .el-main {
    padding: 10px;
}
.layout-container-demo .el-menu{
    border-right: none;
}
.layout-container-demo .toolbar {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    height: 100%;
    font-weight: bolder;
}
.warn{
    font-weight: bolder;
    display: inline-flex;
    align-items: center;
    justify-content: center;
    height: 100%;
}
</style>