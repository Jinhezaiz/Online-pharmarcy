<template>
    <el-dialog v-model="outerVisible" title="添加角色" width="40%" center>
        <AddRole></AddRole>
    </el-dialog>
    <el-dialog v-model="outVisible" title="编辑" width="40%" center draggable>
        <EditRole :id="id" ></EditRole>
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
                    @click="outerVisible=true">添加角色
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
                    @click="deleteRole(row.id)">删除
            </el-button>
        </template>
    </vue3-pro-table>

</template>

<script>
import {defineComponent, reactive, ref, toRefs} from "vue";

import {ElMessage, ElMessageBox} from "element-plus";
import AddRole from "@/components/boss/addRole.vue";
import EditRole from "@/components/boss/editRole.vue";
import roleApi from "@/api/role";


export default defineComponent({
    components: {
        EditRole,
        AddRole
    },
    setup() {
        const outerVisible = ref(false);
        const outVisible = ref(false);
        const id = ref("")

        function initRow(row){
            if(row.id==1||row.id==2||row.id==3){
                ElMessage.error("该角色为初始角色，不能编辑")
                return
            }
            id.value = row.id
            outVisible.value=true
        }
        const state = reactive({
            // 表格列配置，大部分属性跟el-table-column配置一样
            columns: [
                {label: "序号", type: "index"},
                {label: "角色编号", prop: "id"},
                {label: "角色名称", prop: "name"},
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

        function deleteRole(id) {
            if(id==1||id==2||id==3){
                ElMessage.error("该角色为初始角色，不能删除")
                return
            }
            ElMessageBox.confirm(
                '请您确认是否删除该数据',
                '提示',
                {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                }
            ).then(() => {
                roleApi.delete(id)
                ElMessage.success("删除成功")
            }).catch(() => {
            })
        }

        //请求函数
        const getList = async (params) => {
            let dto = {
                size: params.pageSize,
                current: params.pageNum
            }
            //必须要返回一个对象，包含data数组和total总数
            const data = roleApi.getPage(dto).then(resp => {
                let current = resp.data.data.records
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
            deleteRole,
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