import axios from "@/utils/axiosUtil"
const drug = {
    getPage(page){
        return axios.post("/drug/page",page)
    },
    getPageByBossId(dto){
        return axios.post("/drug/storepage",dto)
    },
    deleteById(id){
        return axios.delete("/drug/"+id)
    },
    insert(dto){
        return axios.post("/drug",dto)
    },
    update(dto){
        return axios.put("/drug",dto)
    }
}
export default drug