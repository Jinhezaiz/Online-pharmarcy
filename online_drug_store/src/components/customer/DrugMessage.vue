<template>
    <vue3-pro-table :request="getList"
                    :columns="columns"
                    border="true"
                    hide-title-bar="true"
                    :pagination="pageConfig"
                    >
    </vue3-pro-table>
</template>

<script>
import {defineComponent, reactive, toRefs} from "vue";
import drugApi from "@/api/drug"
export default defineComponent({
    setup() {
        const state = reactive({
            // 表格列配置，大部分属性跟el-table-column配置一样
            columns: [
                {label: "序号", type: "index"},
                {label: "药品编号",prop: "id"},
                {label: "药品名称", prop: "name"},
                {label: "生产企业", prop: "company"},
                {label: "药品单价", prop: "price"},
                {label: "是否处方药", prop: "isLimited"},
                {label: "药品类型", prop: "type"},
                {label: "所属药店编号", prop: "drugStoreId"}
            ],
            pageConfig: {
                layout: "prev, pager, next,jumper",
                pageSize: 11
            }
        });

        //请求函数
        const getList = async (params) => {
            let page = {
                size:params.pageSize,
                current:params.pageNum
            }
            //必须要返回一个对象，包含data数组和total总数
            const data = drugApi.getPage(page).then(resp=>{
                return{
                    list:resp.data.data.records,
                    total:resp.data.data.total
                }
            })
            return {
                data: (await data).list,
                total: (await data).total,
            };
        };

        return {
            ...toRefs(state),
            getList,
        };
    },
});
</script>
<style scoped>
</style>