import axios from "@/utils/axiosUtil"


const roleMenu = {
    getPage(dto){
        return axios.post("/drugdoctor/rolepage",dto)
    }
}

export default roleMenu