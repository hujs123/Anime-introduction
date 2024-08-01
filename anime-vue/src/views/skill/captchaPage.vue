<template>
  <div>{{ message }}</div>
  <div>
    <img :src="captchaUrl" @click="refreshCaptcha" alt="captcha" /><br>
    <input type="text" v-model="captchaInput" placeholder="输入验证码" />
    <button @click="verifyCaptcha" :disabled="!captchaInput">验证</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from "axios";

const message = ref("验证码界面");
const captchaUrl = ref('/captcha?' + new Date().getTime());
const captchaInput = ref('');

onMounted(() => {
  refreshCaptcha(); // 组件挂载时加载验证码
});

const refreshCaptcha = () => {
  captchaUrl.value = '/api/skills/captcha?' + new Date().getTime(); // 直接修改captchaUrl的值来刷新验证码
};

const verifyCaptcha = async () => {
  try {
    const queryString = `captcha=${encodeURIComponent(captchaInput.value)}`;
    const response = await axios.post('/api/skills/verifyCaptcha?' + queryString);
    console.log(response.data); // 打印响应数据
    if(response.data.code==0){
      alert('验证成功')
    }else{
      throw new Error(response.data.msg)
    }
    // 根据响应处理验证结果
  } catch (err) {
    console.warn(err.message || '验证码验证失败');
    // 显示错误消息
  }

};
</script>
