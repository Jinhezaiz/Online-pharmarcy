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
        <el-form-item label="账号">
            <el-input v-model="form.userName"></el-input>
        </el-form-item>
        <el-form-item label="密码">
            <el-input v-model="form.passWord"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
            <el-input v-model="form.email"></el-input>
        </el-form-item>
        <el-form-item label="电话">
            <el-input v-model="form.phone"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">添加</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
import store from "@/store";
import {ElMessage, ElMessageBox} from "element-plus";
import userApi from "@/api/user"
import drugStoreApi from "@/api/drugStore";

export default {
    name: "personMessageManage",
    data() {
        let form = {
            name: "",
            sex:"",
            userName:"",
            passWord:"",
            email:"",
            phone:""
        }

        return {
            form,
        }
    },
    methods:{
        async onSubmit(){
            let token = store.state.token
            let strings = token.split(".")
            let payload = JSON.parse(decodeURIComponent(escape(window.atob(strings[1]))))
            let id = drugStoreApi.getMessage(payload.id).then(resp=>{
                return resp.data.data.id
            })
            let dto = {
                drugStoreId: await id,
                name:this.form.name,
                sex:this.form.sex==1||this.form.sex=='男'?1:0,
                userName:this.form.userName,
                password:this.form.passWord,
                email:this.form.email,
                phone:this.form.phone,
                auth:2,
                roleId:2
            }
            if(dto.name==null||dto.sex==null||
                dto.userName==null||dto.password==null||dto.name==''||dto.sex===''
                ||dto.userName==''||dto.password==''||dto.email==''||dto.email==null
                ||dto.phone==null||dto.phone==''){
                ElMessage.error("必填信息项不能为空")
                return
            }
            ElMessageBox.confirm(
                '请您确认是否添加药师',
                '提示',
                {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                }
            ).then(() => {
                userApi.register(dto).then(resp=>{
                    if(resp.data.code==0){
                        ElMessage.error(resp.data.message)
                    }
                    else{
                        ElMessage.success("添加成功")
                        this.form.sex=''
                        this.form.phone=''
                        this.form.userName=''
                        this.form.name=''
                        this.form.email=''
                        this.form.passWord=''
                    }
                })

            }).catch(()=>{
            })

        }
    }


}
</script>

<style scoped>
</style>