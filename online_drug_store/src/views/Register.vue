<template>
    <div id="background">
        <div id="contain">
            <h1>用户注册</h1>

            <div class="form">
                <label>用户名：</label><input type="text" v-model="userName" required="required"><br/>
            </div>
            <div class="form">
                <label>密码：</label><input type="password" required="required" v-model="password"><br/>
            </div>
            <div class="form">
                <label>邮箱：</label><input type="text" v-model="email" required="required"><br/>
            </div>
            <div class="form">
                <label>姓名：</label><input type="text" v-model="name" required="required"><br/>
            </div>
            <div class="form">
                <label>地址：</label><input type="text" v-model="address" required="required"><br/>
            </div>
            <div class="form">
                <label>手机号：</label><input type="text" v-model="phone" required="required"><br/>
            </div>
            <el-button @click="register" type="primary">注册</el-button>
        </div>
    </div>
</template>
<script>
import userApi from "@/api/user";
import {ElMessage} from "element-plus";

export default {
    data() {
        let userName
        let password
        let email
        let phone
        let name
        let address
        return {
            userName,
            password,
            email,
            phone,
            name,
            address
        }

    },
    methods: {
        register() {
            let registerDto = {
                userName: this.userName,
                sex: 1,
                auth: 3,
                name: this.name,
                phone: this.phone,
                address: this.address,
                password: this.password,
                roleId: 3
            }
            if (this.userName == null || this.userName == '' || this.name == null ||
                this.name == '' || this.phone == null || this.phone == '' || this.password == null || this.password == ''
                || this.address == null || this.address == '') {
                ElMessage.error("必填信息不能为空")
                return
            }
            userApi.register(registerDto).then(resp => {
                if (resp.data.code == 0) {
                    ElMessage.error(resp.data.message)
                } else {
                    ElMessage.success("注册成功")
                    this.$router.push("/login")
                }
            })
        }
    }
}
</script>
<style scoped>
#background {
    width: 100%;
    height: 100%;
    background: url("../assets/bk.jpg");
    background-size: 100% 100%;
    position: fixed;
    top: 0;
    left: 0;
}

#contain {
    width: 580px;
    height: 700px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background: rgba(0, 0, 0, 0.8);
    text-align: center;
    border-radius: 20px;
}

#contain h1 {
    color: white;
}

.form {
    color: white;
    margin-left: 20%;
    margin-top: 60px;
    font-size: 20px;
    text-align: left;
}

label {
    float: left;
    width: 5em;
    margin-right: 1em;
    text-align: right;
}

input, textarea {
    margin-left: 10px;
    padding: 4px;
    border: solid 1px #4e5ef3;
    outline: 0;
    font: normal 13px/100% Verdana, Tahoma, sans-serif;
    width: 200px;
    height: 20px;
    background: #f1f1f190;
    box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 8px;
}

input:hover, textarea:hover, input:focus, textarea:focus {
    border-color: #0d0aa1;
}

button {
    position: relative;
    height: 33px;
    width: 150px;
    border-radius: 10px;
    margin-top: 38px;
    box-shadow: none;
    color: white;
    margin-left: 40px;
}
</style>
