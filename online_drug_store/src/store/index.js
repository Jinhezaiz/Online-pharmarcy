import { createStore } from 'vuex'

export default createStore({
  state: {
    token:localStorage.getItem("token")?localStorage.getItem("token"):""
  },
  getters: {
  },
  mutations: {
    login(state,token){
      state.token = token
    }
  },
  actions: {
  },
  modules: {
  }
})
