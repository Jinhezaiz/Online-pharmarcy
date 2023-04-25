import axios from 'axios'
import router from "@/router";
import store from "@/store";

const service = axios.create({
    baseURL:"http://localhost:9000",
    timeout:5000
})

service.interceptors.request.use(
    config=>{
        if(store.state.token!=null&&store.state.token!=''){
            config.headers['token'] = store.state.token
        }
        return config
    },
    error => {
        console.log(error)
        return Promise.reject(error)
    }
)
service.interceptors.response.use(
    resp=>{
        if(resp.status==403){
            alert('hello')
            router.push("/login")
        }
        return resp
    },
    error => {
        console.log(error)
        return Promise.reject(error)
    }
)


export default service