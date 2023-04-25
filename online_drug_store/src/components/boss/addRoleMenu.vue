<template>
    <el-form v-model="form" style="height: 50%;width: 50%" label-position="top" class="el-form">
        <el-form-item label="角色名称">
            <el-select v-model="form.roleId">
                <el-option :label="role.name" :value="role.id" v-for="role in form.roleList">
                </el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="菜单名称">
            <el-select v-model="form.menuId" >
                <el-option v-for="menu in form.menuList" :label="menu.menuName" :value="menu.menuId">
                </el-option>
            </el-select>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="onSubmit">添加</el-button>
        </el-form-item>
    </el-form>
</template>

<script>

import {ElMessage, ElMessageBox} from "element-plus";
import roleApi from "@/api/role"
import roleMenuApi from "@/api/roleMenu"


export default {
    name: "personMessageManage",
    data() {
        let form = {
            roleId: "",
            menuId: "",
            roleList: "",
            menuList:
                [
                    {
                        menuId:1,
                        menuName:"门店信息"
                    },
                    {
                        menuId:2,
                        menuName:"店员信息管理"
                    },
                    {
                        menuId:4,
                        menuName:"药品信息"
                    },
                    {
                        menuId:5,
                        menuName:"订单管理"
                    },
                    {
                        menuId:6,
                        menuName:"工作台"
                    }
                ],
        }

        return {
            form,
        }
    },
    methods: {
        async onSubmit() {
            let dto = {
                roleId:this.form.roleId,
                menuId:this.form.menuId
            }
            if (dto.roleId==null || dto.roleId === ''||dto.menuId==null||dto.menuId==='') {
                ElMessage.error("必填信息项不能为空")
                return
            }
            if(dto.roleId==1||dto.roleId==2||dto.roleId==3){
                ElMessage.error("默认角色不能添加权限")
                return
            }
            ElMessageBox.confirm(
                '请您确认是否为该角色添加此权限',
                '提示',
                {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                }
            ).then(() => {
                roleMenuApi.insert(dto).then(resp => {
                    if (resp.data.code == 0) {
                        ElMessage.error("该角色已拥有该权限，添加失败")
                        return
                    }
                    ElMessage.success("添加成功")
                    this.form.menuId=''
                    this.form.roleId=''
                })

            }).catch(() => {
            })

        }
    },
     async created() {
         this.form.roleList = await roleApi.list().then(resp => {
             return resp.data.data;
         })
     }


}
</script>

<style scoped>
</style>