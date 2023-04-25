import axios from "@/utils/axiosUtil"

const boss = {
    getIdByDoctorId(id){
        return axios.get("/boss/"+id)
    }
}


export default boss