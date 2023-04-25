<template>
    <el-form v-model="form" style="height: 50%;width: 50%" label-position="top" class="el-form">
        骑手编号:{{id}}
        <el-form-item label="骑手姓名">
            <el-input v-model="form.name"></el-input>
        </el-form-item>

        <el-form-item label="骑手性别">
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
import transferApi from "@/api/transfer"

import {ElMessage, ElMessageBox} from "element-plus";


export default {
    name: "personMessageManage",
    props:{
        id:String
    },
    data() {
        let form = {
            name: "",
            sex:""
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
                sex: this.form.sex
            }
            if(dto.name==null||
                dto.name==''||dto.sex===''
                ||dto.sex==null){
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
                transferApi.update(dto)
                ElMessage.success("数据更新成功")
            }).catch(()=>{
            })
        },

    },

}
</script>

<style scoped>
</style>