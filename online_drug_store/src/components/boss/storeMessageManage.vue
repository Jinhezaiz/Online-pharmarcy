<template>
    <el-form v-model="form" style="height: 50%;width: 50%" label-position="top" class="el-form">
        <el-form-item label="门店名称">
            <el-input v-model="form.name"></el-input>
        </el-form-item>

        <el-form-item label="门店地址">
            <el-input v-model="form.address"></el-input>
        </el-form-item>

        <el-form-item label="门店公告">
            <el-input v-model="form.announcement"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">更新</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
import drugStoreApi from "@/api/drugStore"
import store from "@/store";
import {ElMessage, ElMessageBox} from "element-plus";
import router from "@/router";

export default {
    name: "personMessageManage",
    data() {
        let form = {
            name: "",
            address: "",
            announcement:""
        }

        return {
            form,
        }
    },
    methods:{
        onSubmit(){
            let token = store.state.token
            let strings = token.split(".")
            let payload = JSON.parse(decodeURIComponent(escape(window.atob(strings[1]))))
            let dto = {
                id:payload.id,
                name:this.form.name,
                announcement: this.form.announcement,
                address: this.form.address,
            }
            if(dto.name==null||dto.announcement==null||
                dto.address==null||dto.name==''||dto.announcement==''
            ||dto.address==''){
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
                drugStoreApi.update(dto)
                ElMessage.success("数据更新成功")
            }).catch(()=>{

            })

        }
    },
    created() {
        let token = store.state.token
        let strings = token.split(".")
        let payload = JSON.parse(decodeURIComponent(escape(window.atob(strings[1]))))
        drugStoreApi.getMessage(payload.id).then(resp=>{
            this.form.name=resp.data.data.name
            this.form.announcement=resp.data.data.announcement
            this.form.address=resp.data.data.address
        })
    }

}
</script>

<style scoped>
</style>