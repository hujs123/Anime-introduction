<template>
  <div class="login-border " id="loginModel">
    <!-- 关闭按钮 -->
    <div class="close-button" @click="handleCancel()">×</div>
    <div style="margin-top: 15%;">
      <div class="login-item">
        <div class="label">账户：</div>
        <a-input class="input" v-model:value="formData.account" name="account"/>
      </div>
      <div class="login-item">
        <div class="label">密码：</div>
        <a-input class="input" v-model:value="formData.password" type="password" name="password"/>
      </div>
      <div class="login-button">
        <div class="login-button-item" style="margin-right: 30px;background: #ffffff;color: #007bff"
             @click="handleCancel()">注册
        </div>
        <div class="login-button-item" style="" @click="handleOk()">确认</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {defineEmits, defineProps, ref, watch} from 'vue';

const props = defineProps({
  mshowModel: Boolean,
  mhandleOk: Function,
  mhandleCancel: Boolean
});

const emit = defineEmits(['select-menu', 'update:mshow-model'])

const formData = ref({
  account: '',
  password: ''
});

function handleOk() {
  if (props.mhandleOk) {
    props.mhandleOk(formData.value);
  }
  alert('功能未实现,参数:'+'\n'+'账号:：'+formData.value.account+'\n'+'密码：'+formData.value.password)
  // alert('功能未实现1', formData.value.account)
  // alert('功能未实现1', formData.value.password)
  console.log('功能未实现', formData.value)
  emit('update:mshow-model', false);
}

function handleCancel() {
  if (props.mhandleCancel) {
    props.mhandleCancel();
    emit('update:mshow-model', false);
  } else {
    emit('update:mshow-model', false);
  }
}

// 监听 prop 的变化以重新初始化 menuItems
watch(() => props.mshowModel, (newValue) => {
  formData.value.account = ""
  formData.value.password = ""
  console.log('登录组件', newValue)
});
</script>

<style scoped>
/* 样式 */
.login-border {
  background-color: #9fc7e1;
  position: relative
}

.login-item {
  margin: 30px 0px 0px -80px;
  display: flex;
  /*justify-content: flex-start;*/
  align-items: center;

.label {
  width: 35%;
  font-weight: 500;
  font-size: 32px;
  color: #333333;
  letter-spacing: 0;
  text-align: right;
}

.input {
  width: 45%;
  height: 50px;
  background: #FFFFFF;
  border: 1px solid #4966F5;
  border-radius: 4px;
  margin: 0;
  font-size: 20px;
}

}
.login-button {
  margin-left: 60px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  font-size: 26px;
  line-height: 40px;

.login-button-item {
  cursor: pointer;
  color: #FFFFFF;
  width: 15%;
  height: 46px;
  background: #4966F5;
  border: 2px solid #AAAAAA;
  border-radius: 4px;
}

}

/* 添加关闭按钮的样式 */
.close-button {
  position: absolute; /* 绝对定位 */
  top: 10px; /* 距离顶部10px */
  right: 10px; /* 距离右边10px */
  font-size: 24px; /* 字体大小 */
  line-height: 24px;
  cursor: pointer; /* 鼠标悬停时显示指针 */
  color: #478d9f; /* 文字颜色 */
  /* 可以根据需要添加更多样式 */
}
</style>
