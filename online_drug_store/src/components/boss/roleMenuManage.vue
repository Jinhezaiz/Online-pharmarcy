<template>
    <el-dialog v-model="outerVisible" title="添加角色-权限" width="40%" center>
        <AddRoleMenu></AddRoleMenu>
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
                    @click="outerVisible=true">添加角色-权限
            </el-button>
            <el-button type="danger" icon="el-icon-refresh" @click="refresh">刷新
            </el-button>
        </template>
        <!-- 表格操作栏 -->
        <template #page-operate="{ row }">
            <el-button
                    type="text"
                    @click="deleteRoleMenu(row)">删除
            </el-button>
        </template>
    </vue3-pro-table>

</template>

<script>
import {defineComponent, reactive, ref, toRefs} from "vue";

import {ElMessage, ElMessageBox} from "element-plus";
import AddRoleMenu from "@/components/boss/addRoleMenu.vue";
import roleMenuApi from "@/api/roleMenu";


export default defineComponent({
    components: {
        AddRoleMenu
    },
    setup() {
        const outerVisible = ref(false);
        const outVisible = ref(false);
        const id = ref("")

        const state = reactive({
            // 表格列配置，大部分属性跟el-table-column配置一样
            columns: [
                {label: "序号", type: "index"},
                {label: "编号", prop: "id"},
                {label: "角色编号", prop: "roleId"},
                {label: "角色名称", prop: "roleName"},
                {label: "菜单编号",prop: "menuId"},
                {label: "菜单名称",prop: "menuName"},
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

        function deleteRoleMenu(row) {
            if(row.roleId==1||row.roleId==2||row.roleId==3){
                ElMessage.error("该角色为初始角色，不能更改权限")
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
                roleMenuApi.delete(row.id)
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
            const data = roleMenuApi.getPage(dto).then(resp => {
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
            deleteRoleMenu,
            outerVisible,
            outVisible,
            id,
        };
    },
});
</script>
<style scoped>
</style>