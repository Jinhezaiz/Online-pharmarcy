<template>
    <el-form v-model="form" style="height: 50%;width: 50%" label-position="top" class="el-form">
        <el-form-item label="药品名称">
            <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="生产企业" >
            <el-input v-model="form.company"></el-input>
        </el-form-item>
        <el-form-item label="生产日期">
            <el-input v-model="form.produceTime"></el-input>
        </el-form-item>
        <el-form-item label="保质期">
            <el-input v-model="form.life"></el-input>
        </el-form-item>
        <el-form-item label="是否处方药">
            <el-select v-model="form.isLimited">
                <el-option label="处方药" value=1></el-option>
                <el-option label="非处方药" value=0></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="药品类型">
            <el-select v-model="form.type">
                <el-option label="甲类药品" value=1></el-option>
                <el-option label="乙类药品" value=2></el-option>
                <el-option label="丙类药品" value=3></el-option>
                <el-option label="肿瘤特药" value=4></el-option>
                <el-option label="其他类药品" value=5></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="药品数量">
            <el-input v-model="form.count"></el-input>
        </el-form-item>
        <el-form-item label="药品单价">
            <el-input v-model="form.price"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit">添加</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
import store from "@/store";
import {ElMessage, ElMessageBox} from "element-plus";
import drugApi from "@/api/drug";
import drugStoreApi from "@/api/drugStore";
import bossApi from "@/api/boss";

export default {
    name: "personMessageManage",
    data() {
        let form = {
            name: "",
            company:"",
            produceTime:"yyyy-MM-dd HH:mm:ss",
            life:"",
            isLimited:"",
            type:"",
            count:"",
            price:""
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
            let bossId = await bossApi.getIdByDoctorId(payload.id).then(resp=>{
                return resp.data.data
            })
            let id = drugStoreApi.getMessage(bossId).then(resp=>{
                return resp.data.data.id
            })

            let dto = {
                drugStoreId: await id,
                name:this.form.name,
                company:this.form.company,
                produceTime: this.form.produceTime,
                life:this.form.life,
                isLimited:this.form.isLimited,
                type:this.form.type,
                count:this.form.count,
                price:this.form.price
            }
            if(dto.name==null||dto.company==null||dto.produceTime==null||dto.life==null||dto.isLimited==null
            ||dto.type==null||dto.count==null||dto.price==null||dto.name===''||dto.company===''||dto.produceTime===''
            ||dto.life===''||dto.isLimited===''||dto.type===''||dto.count===''||dto.price===''){
                ElMessage.error("必填信息项不能为空")
                return
            }
            if(!this.form.produceTime.match('^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29) (20|21|22|23|[0-1][0-9]):[0-5][0-9]:[0-5][0-9]$'))
            {
                ElMessage.error("生产日期应符合格式：yyyy-MM-dd HH:mm:ss")
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
                drugApi.insert(dto).then(resp=>{
                    if(resp.data.code==0){
                        ElMessage.error(resp.data.message)
                    }
                    else{
                        ElMessage.success("添加成功")
                            this.form.name="",
                            this.form.company="",
                            this.form.produceTime="yyyy-MM-dd HH:mm:ss",
                            this.form.life="",
                            this.form.isLimited="",
                            this.form.type="",
                            this.form.count="",
                            this.form.price=""
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