<template>
    <el-form v-model="form" style="height: 50%;width: 50%" label-position="top" class="el-form">
        <el-form-item label="姓名">
            <el-input v-model="form.name"></el-input>
        </el-form-item>

        <el-form-item label="性别">
            <el-select v-model="form.sex">
                <el-option label="男" value=1></el-option>
                <el-option label="女" value=0></el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">添加</el-button>
        </el-form-item>
    </el-form>
</template>

<script>

import {ElMessage, ElMessageBox} from "element-plus";
import transferApi from "@/api/transfer"
import store from "@/store";
import drugStoreApi from "@/api/drugStore";
import bossApi from "@/api/boss";

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
            let token = store.state.token
            let strings = token.split(".")
            let payload = JSON.parse(decodeURIComponent(escape(window.atob(strings[1]))))
            let bossId = await bossApi.getIdByDoctorId(payload.id).then(resp=>{
                return resp.data.data
            })
            let id = drugStoreApi.getMessage(bossId).then(resp=>{
                return resp.data.data.id
            })
            let dto = {
                name: this.form.name,
                sex: this.form.sex == 1 || this.form.sex == '男' ? 1 : 0,
                drugStoreId:await id
            }
            if (dto.name == null || dto.sex == null || dto.name == '' || dto.sex === ''
            ) {
                ElMessage.error("必填信息项不能为空")
                return
            }
            ElMessageBox.confirm(
                '请您确认是否添加骑手',
                '提示',
                {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                }
            ).then(() => {
                transferApi.insert(dto).then(resp => {
                    ElMessage.success("添加成功")
                    this.form.sex = ''
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