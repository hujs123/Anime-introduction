// vuex状态管理
import { createStore } from 'vuex';

// 定义 state
const state = {
    count: 0,
    // 其他状态...
    //进入界面显示页面,参考router/modules/common.js
    defaultpathtype:'1'
};

// 定义 mutations，用于更新state
const mutations = {
    setCount(state, value) {
        state.count = value;
    },
    setDefaultPathType(value) {
        state.count = value;
    },
    // 其他 mutations...
};

// 定义 actions,用于执行异步操作
const actions = {
    async decrementIfOdd({ state, commit }) {
        // 假设这里有一个实际的异步操作，比如 API 调用
        // 但为了示例，我们只是简单地等待一段时间
        await new Promise(resolve => {
            setTimeout(() => {
                if (state.count % 2 !== 0) {
                    commit('decrement');
                }
                resolve();
            }, 1000);
        });
    },
    // 其他 actions...
};

// 定义 getters，获取state值
const getters = {
    getState(state) {
        return state.count*2;
    },
    getDefaultPathType(state) {
        return state.defaultpathtype;
    },
    // 其他 getters...
};

// 导出 store
export default createStore({
    state,
    mutations,
    actions,
    getters,
    // 其他选项...
});
