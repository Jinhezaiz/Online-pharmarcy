<template>
    <el-dialog v-model="outerVisible" title="添加药师" width="40%" center>
        <AddDoctor></AddDoctor>
    </el-dialog>
    <el-dialog v-model="outVisible" title="编辑" width="40%" center draggable>
        <EditDoctor :id="id" ></EditDoctor>
    </el-dialog>
    <vue3-pro-table :request="getList"
                    :columns="columns"
                    border="true"
                    :pagination="pageConfig"
                    ref="proTable"
    >

        <!-- 工具栏 -->
        <template #toolbar>
            <el-button
                    type="primary"
                    icon="el-icon-plus"
                    @click="outerVisible=true">添加药师
            </el-button>
            <el-button type="danger" icon="el-icon-refresh" @click="refresh">刷新
            </el-button>
        </template>
        <!-- 表格操作栏 -->
        <template #page-operate="{ row }">
            <el-button
                    type="text"
                    @click="
            initRow(row)
        ">编辑
            </el-button>

            <el-button
                    type="text"
                    @click="deleteDoctor(row.id)">删除
            </el-button>
        </template>
    </vue3-pro-table>

</template>

<script>
import {defineComponent, reactive, ref, toRefs} from "vue";
import drugDoctorApi from "@/api/drugDoctor"
import store from "@/store";
import {ElMessage, ElMessageBox} from "element-plus";
import AddDoctor from "@/components/drugdoctor/addDoctor.vue";
import EditDoctor from "@/components/drugdoctor/editDoctor.vue";
import bossApi from "@/api/boss";


export default defineComponent({
    components: {
        EditDoctor,
        AddDoctor
    },
    setup() {
        const outerVisible = ref(false);
        const outVisible = ref(false);
        const id = ref("")

        function initRow(row){
            id.value = row.id
            outVisible.value=true
        }
        const state = reactive({
            // 表格列配置，大部分属性跟el-table-column配置一样
            columns: [
                {label: "序号", type: "index"},
                {label: "药师编号", prop: "id"},
                {label: "药师姓名", prop: "name"},
                {label: "药师性别", prop: "sex"},
                {
                    label: "操作",
                    labelSlot: "th-operate",
                    fixed: "right",
                    width: 180,
                    align: "center",
                    tdSlot: "page-operate",
                }
            ],
            pageConfig: {
                layout: "prev, pager, next,jumper",
                pageSize: 10
            },
        });

        function deleteDoctor(id) {
            ElMessageBox.confirm(
                '请您确认是否删除该数据',
                '提示',
                {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                }
            ).then(() => {
                drugDoctorApi.delete(id)
                ElMessage.success("删除成功")
            }).catch(() => {

            })
        }

        //请求函数
        const getList = async (params) => {
            let token = store.state.token
            let strings = token.split(".")
            let payload = JSON.parse(decodeURIComponent(escape(window.atob(strings[1]))))
            let bossId = await bossApi.getIdByDoctorId(payload.id).then(resp=>{
                return resp.data.data
            })
            let dto = {
                id: bossId,
                size: params.pageSize,
                current: params.pageNum
            }
            //必须要返回一个对象，包含data数组和total总数
            const data = drugDoctorApi.getPage(dto).then(resp => {
                let current = resp.data.data.records
                for (let i = 0; i < current.length; i++) {
                    current[i].sex = current[i].sex == 1 ? '男' : '女'
                }
                return {
                    list: current,
                    total: +resp.data.data.total
                }
            })
            return {
                data: (await data).list,
                total: (await data).total,
            };
        };
        const proTable = ref(null);

        // 刷新
        const refresh = () => {
            proTable.value.refresh();
        };
        return {
            ...toRefs(state),
            getList,
            proTable,
            refresh,
            deleteDoctor,
            outerVisible,
            outVisible,
            id,
            initRow,
        };
    },
});
</script>
<style scoped>
</style>