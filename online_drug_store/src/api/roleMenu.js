import axios from "@/utils/axiosUtil"


const roleMenu = {
    getPage(dto){
        return axios.post("/rolemenu/page",dto)
    },
    delete(id){
        return axios.delete("/rolemenu/"+id)
    },
    insert(dto){
        return axios.post("/rolemenu",dto)
    },
    update(dto){
        return axios.put("/rolemenu",dto)
    }

}

export default roleMenu