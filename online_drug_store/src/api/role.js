import axios from "@/utils/axiosUtil"


const role = {
    getPage(dto){
        return axios.post("/role/page",dto)
    },
    delete(id){
        return axios.delete("/role/"+id)
    },
    insert(dto){
        return axios.post("/role",dto)
    },
    update(dto){
        return axios.put("/role",dto)
    },
    list(){
        return axios.get("/role/list")
    }

}

export default role