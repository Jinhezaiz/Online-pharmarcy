<template>
    <el-form v-model="form" style="height: 50%;width: 50%" label-position="top" class="el-form">
        <el-form-item label="角色名称">
            <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">添加</el-button>
        </el-form-item>
    </el-form>
</template>

<script>

import {ElMessage, ElMessageBox} from "element-plus";
import roleApi from "@/api/role"
import store from "@/store";


export default {
    name: "personMessageManage",
    data() {
        let form = {
            name: "",
            sex: "",
        }

        return {
            form,
        }
    },
    methods: {
        async onSubmit() {
            let dto = {
                name: this.form.name
            }
            if (dto.name == null || dto.name == '') {
                ElMessage.error("必填信息项不能为空")
                return
            }
            ElMessageBox.confirm(
                '请您确认是否添加角色',
                '提示',
                {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                }
            ).then(() => {
                roleApi.insert(dto).then(resp => {
                    if(resp.data.code==0){
                        ElMessage.error("该角色已存在")
                        return
                    }
                    ElMessage.success("添加成功")
                    this.form.name = ''
                })

            }).catch(() => {
            })

        }
    }


}
</script>

<style scoped>
</style>