import axios from "@/utils/axiosUtil"


const transfer = {
    getPage(dto){
        return axios.post("/transfer/page",dto)
    },
    delete(id){
        return axios.delete("/transfer/"+id)
    },
    insert(dto){
        return axios.post("/transfer",dto)
    },
    update(dto){
        return axios.put("/transfer",dto)
    }

}

export default transfer