<template>
  <div>
    <div>{{ message }}</div>
    <div>
        <div>
          <input type="file" @change="changeFile" />
          <button @click="uploadFile">上传</button>
        </div>
      <div>
        <!-- 展示图片 -->
        <img v-if="imageUrl" :src="imageUrl" alt="Image">
        <button @click="getFile">查看并下载文件</button>
      </div>
    </div>
    <div>
      <button @click="test">测试</button>
      <button @click="init">初始化</button>
      <button @click="posttest">发送POST请求</button>
      <button @click="test">测试非加密网关</button>
      <button @click="toEncrypt">信息加密</button>
      <button @click="toDecrypt">信息解密</button>
      <!-- 触发按钮 -->
      <button type="primary" @click="showModals= true">
        打开表单弹窗2
      </button>
      <button @click="toHomePage">返回首页</button>
    </div>

    <AddPersonPage :mshowModals="showModals" :mhandle-cancel="handleCancel" :mhandle-ok="handleOk"></AddPersonPage>
<!--    <AddPersonPage :mshowModals="showModals"></AddPersonPage>-->
  </div>
</template>
<script setup>
import {ref,onMounted} from "vue";
import { useRouter } from 'vue-router' // 使用useRouter hook来获取router实例
import axios from "axios";
// import { AModal, AForm, AFormItem, AInput } from 'ant-design-vue';
import {encryptByAes,decryptByAes} from "@/utils/encrypt";
import AddPersonPage from "@/views/personInfo/addPersonPage";
const router = useRouter() // 使用useRouter hook代替直接导入router实例
const message = ref("图片页面");


// 控制弹窗显示
const showModals = ref(false);
// 处理弹窗的确定按钮点击
function handleOk() {
  console.log('点击确认按钮')
  showModals.value = false;
}

// 处理弹窗的取消按钮点击
function handleCancel() {
  console.log('点击取消按钮')
  showModals.value = false;
}

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


const test = () => {
  let name='河北省直属机关第一门诊部'
  let titleDrawX=titleSizeSet(name)
  console.log('titleDrawX',titleDrawX); // true
};
const  titleSizeSet=(title)=>{
  console.log('title',title)
  console.log('title.length',title.length)
  let length=105-7*title.length
  console.log('length',length)
  return length
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


//上传文件
const uploadFile=async () => {
  console.log('上传文件')
  const formData = new FormData();
  formData.append('file', selectedFile.value);
  await axios.post('/api/skills/uploadFile',formData).then(response => {
    console.log(response.data); // 打印响应数据
  }).catch(err => {
    console.warn(err.message || err.msg)
  })
}

const imageUrl=ref(null)
// 获取文件
const getFile=async () => {
  let objectName="微信图片_20240720102940.jpg"
  console.log('查看文件')
  await axios.get(`/api/skills/getFileOnMinio/${objectName}`,{responseType: 'blob'}).then(response => {
    console.log("response.data",response.data); // 打印响应数据
    // imageUrl.value=url
    //显示图片
    const imgUrl = window.URL.createObjectURL(new Blob([response.data], { type: 'image/jpeg' })); // 假设是JPEG图片
    imageUrl.value = imgUrl; // 赋值给imageUrl以便在<img>标签中显示

    // 创建一个链接来模拟点击下载
    const url = window.URL.createObjectURL(new Blob([response.data]));
    // const url = window.URL.createObjectURL(response.data);
    const link = document.createElement('a');
    link.href = url;
    link.setAttribute('download', objectName); // 设置下载文件名
    document.body.appendChild(link);
    link.click();
    // 清理
    document.body.removeChild(link);
    window.URL.revokeObjectURL(url);
    console.log(response.data); // 打印响应数据
  }).catch(err => {
    console.warn(err.message || err.msg)
  })
}


// // 获取文件并显示图片（不直接下载）
// const getFileForDisplay = async () => {
//   let objectName = "微信图片_20240720102940.jpg";
//   try {
//     // 注意：这里假设你的API直接返回图片的URL，而不是图片的二进制数据
//     // 如果API返回二进制数据，你需要将其转换为URL（但这通常不是推荐的做法，因为它会增加前端负担）
//     const response = await axios.get(`/api/skills/getFileUrlOnMinio/${objectName}`); // 假设这是返回图片URL的API
//     imageUrl.value = response.data; // 假设response.data是图片的URL
//   } catch (err) {
//     console.error('获取图片URL时发生错误:', err.message || err.toString());
//   }
// };

// // 可以将 getFile 改为调用 getFileForDisplay 和 downloadFile（如果需要同时显示和下载）
// const getFile = async () => {
//   await getFileForDisplay(); // 显示图片
//   await downloadFile(); // 下载图片
// };


// // 单独的下载函数
// const downloadFile = async () => {
//   let objectName = "微信图片_20240720102940.jpg";
//   try {
//     const response = await axios.get(`/api/skills/getFileOnMinio/${objectName}`, { responseType: 'blob' });
//     console.log("response.data",response.data); // 打印响应数据
//     imageUrl.value=url
//     // 创建一个链接来模拟点击下载
//     // const url = window.URL.createObjectURL(new Blob([response.data]));
//     const url = window.URL.createObjectURL(response.data);
//     const link = document.createElement('a');
//     link.href = url;
//     link.setAttribute('download', objectName); // 设置下载文件名
//     document.body.appendChild(link);
//     link.click();
//     // 清理
//     document.body.removeChild(link);
//     window.URL.revokeObjectURL(url);
//     console.log(response.data); // 打印响应数据
//   } catch (err) {
//     console.error('下载文件时发生错误:', err.message || err.toString());
//   }
// };

const selectedFile=ref(null)
const changeFile=(e)=>{
  console.log('选择文件')
  selectedFile.value = e.target.files[0];
}



</script>
