<template>
  <div>
    <div>{{ message }}</div>
    <div>
      <div>
        <input type="file" @change="changeFile"/>
        <button @click="uploadFile">上传</button>
      </div>
      <div>
        <button @click="toHomePage">返回首页</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue";
import {useRouter} from 'vue-router'
import axios from "axios";

const router = useRouter() // 使用useRouter hook代替直接导入router实例
// 读取env文件title给网页title
const VITE_APP_SSO_URL = process.env.VUE_APP_TITLE
const message = ref("图片页面");

const selectedFile = ref(null)
const changeFile = (e) => {
  console.log('选择文件')
  selectedFile.value = e.target.files[0];
}

//上传文件
const uploadFile = async () => {
  console.log('上传文件')
  const formData = new FormData();
  formData.append('file', selectedFile.value);
  await axios.post('/api/skills/uploadFile', formData).then(response => {
    console.log(response.data); // 打印响应数据
  }).catch(err => {
    console.warn(err.message || err.msg)
  })
}
// const imageUrl=ref(null)
// // 获取文件
// const getFile=async () => {
//   let objectName="微信图片_20240720102940.jpg"
//   console.log('查看文件')
//   await axios.get(`/api/skills/getFileOnMinio/${objectName}`,{responseType: 'blob'}).then(response => {
//     console.log("response.data",response.data); // 打印响应数据
//     // imageUrl.value=url
//     //显示图片
//     const imgUrl = window.URL.createObjectURL(new Blob([response.data], { type: 'image/jpeg' })); // 假设是JPEG图片
//     imageUrl.value = imgUrl; // 赋值给imageUrl以便在<img>标签中显示
//
//     // 创建一个链接来模拟点击下载
//     const url = window.URL.createObjectURL(new Blob([response.data]));
//     // const url = window.URL.createObjectURL(response.data);
//     const link = document.createElement('a');
//     link.href = url;
//     link.setAttribute('download', objectName); // 设置下载文件名
//     document.body.appendChild(link);
//     link.click();
//     // 清理
//     document.body.removeChild(link);
//     window.URL.revokeObjectURL(url);
//     console.log(response.data); // 打印响应数据
//   }).catch(err => {
//     console.warn(err.message || err.msg)
//   })
// }

const toHomePage = async () => {
  console.log(VITE_APP_SSO_URL)
  router.push('/') // 使用router实例的push方法进行导航
}

</script>
