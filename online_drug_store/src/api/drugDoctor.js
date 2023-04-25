import axios from "@/utils/axiosUtil"

const drugDoctor ={
    getPage(dto){
        return axios.post("/drugdoctor/page",dto)
    },
    delete(id){
        return axios.delete("/drugdoctor/"+id)
    },
    update(dto){
        return axios.put("/drugdoctor",dto)
    },
    getById(id){
        return axios.get("/drugdoctor/"+id)
    }
}
export default drugDoctor