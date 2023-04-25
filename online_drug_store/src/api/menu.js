import axios from "@/utils/axiosUtil"

const menu ={
    getListByRoleId(id){
        return axios.get("/rolemenu/list/"+id)
    }

}
export default menu