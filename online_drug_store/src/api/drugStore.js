import axios from "@/utils/axiosUtil"

const drugStore ={
    getPage(page){
        return axios.post("/drugstore/page",page)
    },
    getMessage(id){
        return axios.get("/drugstore/"+id)
    },
    update(dto){
        return axios.put("/drugstore",dto)
    },

}
export default drugStore