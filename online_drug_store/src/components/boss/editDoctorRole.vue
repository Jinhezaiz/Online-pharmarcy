<template>
    <el-form v-model="form" style="height: 50%;width: 50%" label-position="top" class="el-form">
        药师编号:{{id}}
        <el-form-item label="角色名称">
            <el-select v-model="form.roleId">
                <el-option :label="role.name" :value="role.id" v-for="role in form.roleList">
                </el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">更新</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
import {ElMessage, ElMessageBox} from "element-plus";
import userApi from "@/api/user";
import roleApi from "@/api/role";

export default {
    name: "personMessageManage",
    props:{
        id:String
    },
    data() {
        let form = {
            roleId:"",
            roleList:""
        }

        return {
            form,
        }
    },
    methods:{
        onSubmit(){
            let dto = {
                id:this.id,
                roleId:this.form.roleId
            }
            if(dto.id==null||dto.id===''){
                ElMessage.error("必填信息项不能为空")
                return
            }
            if(dto.roleId==1||dto.roleId==3){
                ElMessage.error("不能赋予药师店长或顾客角色")
                return;
            }
            ElMessageBox.confirm(
                '请您确认是否更新信息',
                '提示',
                {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                }
            ).then(() => {
                userApi.update(dto)
                ElMessage.success("数据更新成功")
            }).catch(()=>{
            })
        },

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