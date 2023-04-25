<template>
    <el-dialog v-model="outerVisible" title="添加药品" width="40%" center>
        <AddDrug></AddDrug>
    </el-dialog>
    <el-dialog v-model="outVisible" title="编辑" width="40%" center draggable>
        <EditDrug :id="id" ></EditDrug>
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
                    @click="outerVisible=true">添加药品
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
                    @click="deleteDrug(row.id)">删除
            </el-button>
        </template>
    </vue3-pro-table>

</template>

<script>
import {defineComponent, reactive, ref, toRefs} from "vue";
import drugApi from "@/api/drug"
import store from "@/store";
import {ElMessage, ElMessageBox} from "element-plus";
import AddDrug from "@/components/boss/addDrug.vue";
import EditDrug from "@/components/boss/editDrug.vue";


export default defineComponent({
    components: {
        EditDrug,
        AddDrug
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
                {label: "药品编号", prop: "id"},
                {label: "药品名称", prop: "name"},
                {label: "生产厂家", prop: "company"},
                {label:"生产日期",prop: "produceTime"},
                {label:"保质期",prop:"life"},
                {label:"是否处方药",prop: "isLimited"},
                {label: "药品类型",prop: "type"},
                {label: "数量",prop: "count"},
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

        function deleteDrug(id) {
            ElMessageBox.confirm(
                '请您确认是否删除该数据',
                '提示',
                {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning',
                }
            ).then(() => {
                drugApi.deleteById(id)
                ElMessage.success("删除成功")
            }).catch(() => {

            })
        }

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
            const data = drugApi.getPageByBossId(dto).then(resp => {
                let current = resp.data.data.records
                for (let i = 0; i < current.length; i++) {
                    current[i].isLimited=current[i].isLimited==1?'处方药':'非处方药'
                    current[i].life=current[i].life+'年'
                    if(current[i].type==1){
                        current[i].type='甲类药品'
                    }
                    if(current[i].type==2){
                        current[i].type='乙类药品'
                    }
                    if(current[i].type==3){
                        current[i].type='丙类药品'
                    }
                    if(current[i].type==4){
                        current[i].type='肿瘤特药'
                    }
                    if(current[i].type==5){
                        current[i].type='其他类药品'
                    }

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
            deleteDrug,
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