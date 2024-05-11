<template>
  <div>
    <div>{{ message }}</div>
    <div>
      <button @click="init">初始化</button>
      <button @click="posttest">发送POST请求</button>
      <button @click="test">测试非加密网关</button>
      <button @click="toEncrypt">信息加密</button>
      <button @click="toDecrypt">信息解密</button>
      <button @click="toHomePage">返回首页</button>
    </div>
    <div>
      <vxe-table
          border
          ref="tableRef"
          height="300"
          :data="tableData"
          :radio-config="{highlight: true}"
          @cell-click="cellClickEvent"
          @radio-change="radioChangeEvent">
        <vxe-column type="radio" width="60">
          <template #header>
            <vxe-button mode="text" @click="clearRadioRowEevnt" :disabled="!selectRow">取消</vxe-button>
          </template>
        </vxe-column>
        <vxe-column field="id" title="ID"></vxe-column>
        <vxe-column field="num" title="序号"></vxe-column>
        <vxe-column field="name" title="姓名"></vxe-column>
        <vxe-column field="mark" title="备注" show-overflow></vxe-column>
      </vxe-table>
    </div>
  </div>
</template>
<script setup>
import {ref,onMounted} from "vue";
import { useRouter } from 'vue-router' // 使用useRouter hook来获取router实例
// import axios from '../../utils/axiosService'
import axios from "axios";
import {request} from "@/utils/request";
import {encryptByAes,decryptByAes} from "@/utils/encrypt";

const router = useRouter() // 使用useRouter hook代替直接导入router实例
// 读取env文件title给网页title
const VITE_APP_SSO_URL = process.env.VUE_APP_TITLE
const message = ref("图片页面");
const tableData=ref([])
const selectRow=ref(false)

onMounted(() => {
  init()
});
const init=async () => {
  await getDataList()
}

//加解密信息
const to_encrypt=ref({
  message:'这是一个常量信息',//加密前
  encryptmsg:'',//加密后信息
  decryptmsg:'',//解密后信息
})

//获取表格数据
const getDataList = async () => {
  await axios.get('/hujs/datasource/search').then(res=>{
    let response=res.data
    tableData.value= response.data
    console.log('response',response)
  }).catch(err=>{
    console.warn(err.message)
  })
}

const posttest = async () => {
  await axios.post('/api/users/getUsers').then(response=>{
    console.log(response.data); // 打印响应数据
  }).catch(err=>{
    console.warn(err.message||err.msg)
  })
}
//返回首页
const toHomePage = async () => {
  console.log(VITE_APP_SSO_URL)
  router.push('/') // 使用router实例的push方法进行导航
}

const test = async () => {
  console.log('调用测试接口')
  return await request('http://172.30.10.31:7300/mock/652396bf63eb8c002142ec43/mockmain/queryitems','','POST').then(res=>{
    let response=res
    console.log('response',response)
  }).catch(err=>{
    console.log('err',err)
  })
}

const cellClickEvent=()=>{
  console.log('cellClickEvent')
}

const radioChangeEvent=()=>{
  console.log('radioChangeEvent')
}

const clearRadioRowEevnt=()=>{
  console.log('clearRadioRowEevnt')
}


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
