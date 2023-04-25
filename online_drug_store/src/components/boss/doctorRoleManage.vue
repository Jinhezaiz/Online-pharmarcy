<template>
    <el-dialog v-model="outVisible" title="编辑" width="40%" center draggable>
        <EditDoctorRole :id="id"></EditDoctorRole>
    </el-dialog>
    <vue3-pro-table :request="getList"
                    :columns="columns"
                    border="true"
                    :pagination="pageConfig"
                    ref="proTable"
    >

        <!-- 工具栏 -->
        <template #toolbar>
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
        </template>
    </vue3-pro-table>

</template>

<script>
import {defineComponent, reactive, ref, toRefs} from "vue";
import doctorRoleApi from "@/api/doctorRole"
import store from "@/store";
import {ElMessage, ElMessageBox} from "element-plus";

import EditDoctorRole from "@/components/boss/editDoctorRole.vue";


export default defineComponent({
    components: {
        EditDoctorRole,
    },
    setup() {
        const outerVisible = ref(false);
        const outVisible = ref(false);
        const id = ref("")

        function initRow(row) {
            id.value = row.doctorId
            outVisible.value = true
        }

        const state = reactive({
            // 表格列配置，大部分属性跟el-table-column配置一样
            columns: [
                {label: "序号", type: "index"},
                {label: "药师编号", prop: "doctorId"},
                {label: "药师姓名", prop: "name"},
                {label: "药师性别", prop: "sex"},
                {label: "角色编号",prop: "roleId"},
                {label: "角色名称",prop: "roleName"},
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


        //请求函数
        const getList = async (params) => {
            let token = store.state.token
            let strings = token.split(".")
            let payload = JSON.parse(decodeURIComponent(escape(window.atob(strings[1]))))
            let dto = {
                id: payload.id,
                size: params.pageSize,
                current: params.pageNum
            }
            //必须要返回一个对象，包含data数组和total总数
            const data = doctorRoleApi.getPage(dto).then(resp => {
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