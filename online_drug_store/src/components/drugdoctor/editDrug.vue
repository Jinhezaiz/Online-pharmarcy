<template>
    <el-form v-model="form" style="height: 50%;width: 50%" label-position="top" class="el-form">
        <el-form-item label="药品名称">
            <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="生产企业">
            <el-input v-model="form.company"></el-input>
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
            <el-button type="primary" @click="onSubmit">更新</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
import drugApi from "@/api/drug"
import {ElMessage, ElMessageBox} from "element-plus";

export default {
    name: "personMessageManage",
    props: {
        id: String
    },
    data() {
        let form = {
            name: "",
            company: "",
            life: "",
            isLimited: "",
            type: "",
            count: "",
            price: ""
        }

        return {
            form,
        }
    },
    methods: {
        onSubmit() {
            let dto = {
                id: this.id,
                name: this.form.name,
                company: this.form.company,
                life: this.form.life,
                isLimited: this.form.isLimited,
                type: this.form.type,
                count: this.form.count,
                price: this.form.price
            }
            if (dto.name == null || dto.company == null || dto.life == null || dto.isLimited == null
                || dto.type == null || dto.count == null || dto.price == null || dto.name === '' || dto.company === ''
                || dto.life === '' || dto.isLimited === '' || dto.type === '' || dto.count === '' || dto.price === '') {
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
                drugApi.update(dto).then(resp=>{
                    if(resp.data.code==0){
                        ElMessage.error(resp.data.message)
                    }
                    else{
                        ElMessage.success("数据更新成功")
                    }
                })
            }).catch(() => {
            })
        },

    },

}
</script>

<style scoped>
</style>