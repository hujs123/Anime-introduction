<template>
  <div>
    <div>{{ message }}</div>
    <div>
      <div>
        本金<a-input v-model:value="param1" placeholder="请输入" allowClear />
        利率<a-input v-model:value="param2" placeholder="请输入" allowClear />
        年份周期<a-input v-model:value="param3" placeholder="请输入" allowClear />
        总年份<a-input v-model:value="param4" placeholder="请输入" allowClear />
      </div>

    </div>

    <div>
      <button @click="test(param1,param2,param3,param4)">利息计算</button>
      <button @click="init">初始化</button>
      <button @click="posttest">发送POST请求</button>
      <button @click="test">测试非加密网关</button>
      <button @click="toEncrypt">信息加密</button>
      <button @click="toDecrypt">信息解密</button>
      <button @click="toHomePage">返回首页</button>
    </div>
  </div>
</template>
<script setup>
import {ref,onMounted} from "vue";
import { useRouter } from 'vue-router' // 使用useRouter hook来获取router实例
import axios from "axios";
import {encryptByAes,decryptByAes} from "@/utils/encrypt";

const router = useRouter() // 使用useRouter hook代替直接导入router实例
const message = ref("图片页面");
const param1=ref(0)
const param2=ref(0)
const param3=ref(0)
const param4=ref(0)


onMounted(() => {
  init()
});
const init=async () => {
  await posttest()
}

//加解密信息
const to_encrypt=ref({
  message:'这是一个常量信息',//加密前
  encryptmsg:'',//加密后信息
  decryptmsg:'',//解密后信息
})

const posttest = async () => {
  await axios.post('/api/users/getUsers').then(response=>{
    console.log(response.data); // 打印响应数据
  }).catch(err=>{
    console.warn(err.message||err.msg)
  })
}
//返回首页
const toHomePage = async () => {
  router.push('/') // 使用router实例的push方法进行导航
}
/**
 *
 * @param param1  本金
 * @param param2  利率
 * @param param3  存款年份
 * @param param4  总年份
 * @returns {Promise<void>}
 */
const test = (param1, param2, param3, param4) => {
  console.log(param1,' ',param2,' ',param3,' ',param4,' ')
  // 假设param2是百分比，需要除以100
  const rate = param2 / 100;
  let sum = 0; // 初始总金额为0

  for (let i = 0; i < param4; i++) {
    // 在每年年初存款param1
    sum += param1;
    // 如果不是第一年，计算上一年的复利
    if (i > 0) {
      sum += sum * rate; // 这里只计算上一年的复利
    }
  }
  console.log('总收益（包括本金）：', sum); // 输出总收益（包括本金）
};

// 示例调用
// test(1000, 5, 1, 5); // 每年存款1000元，年利率5%，每年存一次，持续5年



/**
 * 加密
 * @returns {Promise<void>}
 */
const toEncrypt = async () => {
  console.log('未加密信息',to_encrypt.value.message)
  to_encrypt.value.encryptmsg = encryptByAes(to_encrypt.value.message);
  console.log('加密信息',to_encrypt.value.encryptmsg)
}

/**
 * 解密
 * @returns {Promise<void>}
 */
const toDecrypt = async () => {
  console.log('加密信息',to_encrypt.value.encryptmsg)
  to_encrypt.value.decryptmsg= decryptByAes(to_encrypt.value.encryptmsg);
  console.log('解密信息',to_encrypt.value.decryptmsg)
}



</script>
