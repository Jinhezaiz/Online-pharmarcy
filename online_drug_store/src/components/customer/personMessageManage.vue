<template>
    <el-form v-model="form" style="height: 50%;width: 50%" label-position="top" class="el-form">
        <el-form-item label="姓名">
            <el-input v-model="form.name"></el-input>
        </el-form-item>

        <el-form-item label="密码">
            <el-input v-model="form.password"></el-input>
        </el-form-item>

        <el-form-item label="手机号">
            <el-input v-model="form.phone"></el-input>
        </el-form-item>
        <el-form-item label="地址">
            <el-input v-model="form.address"></el-input>
        </el-form-item>
        <el-form-item label="性别">
            <el-select v-model="form.sex">
                <el-option label="男" value=1></el-option>
                <el-option label="女" value=0></el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">更新</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
import customerApi from "@/api/customer"
import store from "@/store";
import {ElMessage, ElMessageBox} from "element-plus";

export default {
    name: "personMessageManage",
    data() {
        let form = {
            name: "",
            password: "",
            phone: "",
            address: "",
            sex: ""
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
                sex:this.form.sex==1||this.form.sex=='男'?1:0,
                password:this.form.password,
                phone:this.form.phone,
                address: this.form.address,
            }
            if(dto.name==null||dto.name==''||dto.sex==null||dto.sex===''
            ||dto.password==null||dto.password==''||
            dto.phone==null||dto.phone==''||
            dto.address==null||dto.address==''){
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
                customerApi.update(dto)
                ElMessage.success("数据更新成功")
            }).catch(()=>{

            })
        }
    },
    created() {
        let token = store.state.token
        let strings = token.split(".")
        let payload = JSON.parse(decodeURIComponent(escape(window.atob(strings[1]))))
        customerApi.getMessage(payload.id).then(resp=>{
            this.form.name=resp.data.data.name
            this.form.sex=resp.data.data.sex==1?'男':'女'
            this.form.password=resp.data.data.password
            this.form.phone=resp.data.data.phone
            this.form.address=resp.data.data.address
        })
    }

}
</script>

<style scoped>
</style>