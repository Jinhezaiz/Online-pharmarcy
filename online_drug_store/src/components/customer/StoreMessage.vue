<template>
    <vue3-pro-table :request="getList"
                    :columns="columns"
                    border="true"
                    :pagination="pageConfig"
                    hide-title-bar="true"
                    >
    </vue3-pro-table>

</template>

<script>
import {defineComponent, reactive, ref, toRefs} from "vue";
import drugStoreApi from "@/api/drugStore"

export default defineComponent({
    setup() {
        const state = reactive({
            // 表格列配置，大部分属性跟el-table-column配置一样
            columns: [
                {label: "序号", type: "index"},
                {label: "药店编号", prop: "id"},
                {label: "药店名称", prop: "name"},
                {label: "药店地址", prop: "address"},
                {label: "药店公告", prop: "announcement"},
            ],
            pageConfig: {
                layout: "prev, pager, next,jumper",
                pageSize: 11
            },
        });

        //请求函数
        const getList = async (params) => {
            let page = {
                size: params.pageSize,
                current: params.pageNum
            }
            //必须要返回一个对象，包含data数组和total总数
            const data = drugStoreApi.getPage(page).then(resp => {
                return {
                    list: resp.data.data.records,
                    total: +resp.data.data.total
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