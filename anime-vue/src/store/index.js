//pinia状态管理
import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', {
    state: () => ({
        name: 'hujs',
        email: 'hujs@qq.com'
    }),
    getters: {
        getStateName(){
            return this.name
        },
        fullName(state) {
            return `${state.name} (${state.email})`
        }
    },
    actions: {
        setName(newName) {
            this.name = newName
        },
        setEmail(newEmail) {
            this.email = newEmail
        }
    }
})
