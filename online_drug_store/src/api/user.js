import axios from "@/utils/axiosUtil"


const user = {
    login(user) {
        return axios.post("/user/login", user)
    },
    register(registerDto) {
        return axios.post("/user/register", registerDto)
    },
    getMessage(id){
        return axios.get("/user/"+id)
    },
    update(dto){
        return axios.put("/user",dto)
    }
}

export default user



