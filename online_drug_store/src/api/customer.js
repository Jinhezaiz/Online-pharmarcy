import axios from "@/utils/axiosUtil"

const customer = {
    getMessage(id){
        return axios.get("/customer/"+id)
    },
    update(dto){
        return axios.put("/customer",dto)
    }
}


export default customer