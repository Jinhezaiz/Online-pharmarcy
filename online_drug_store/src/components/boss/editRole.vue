<template>
    <el-form v-model="form" style="height: 50%;width: 50%" label-position="top" class="el-form">
        角色编号:{{id}}
        <el-form-item label="角色名称">
            <el-input v-model="form.name"></el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="onSubmit">更新</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
import roleApi from "@/api/role"

import {ElMessage, ElMessageBox} from "element-plus";


export default {
    name: "personMessageManage",
    props:{
        id:String
    },
    data() {
        let form = {
            name: "",
        }

        return {
            form,
        }
    },
    methods:{
        onSubmit(){
            let dto = {
                id:this.id,
                name:this.form.name,
            }
            if(dto.name==null||
                dto.name==''){
                ElMessage.error("必填信息项不能为空")
                return
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
                roleApi.update(dto).then(resp=>{
                    if(resp.data.code==0){
                        ElMessage.error("该角色已存在")
                    }
                    else{
                        ElMessage.success("数据更新成功")
                    }
                })
            }).catch(()=>{
            })
        },

    },

}
</script>

<style scoped>
</style>