<template>
  <a-modal
      title="表单弹窗2"
      v-model:visible="localShowModals"
      ok-text="提交"
      cancel-text="取消"
      @ok="handleOk"
      @cancel="handleCancel"
  >
    <!-- 表单内容 -->
    <a-form :model="formData" layout="vertical" :rules="formRules">
      <a-form-item
          label="姓名"
          name="name"
          :rules="[
  { required: true, message: '请输入用户名!' },
  { min: 3, max: 12, message: '用户名长度在 3 到 12 个字符之间!' },
  { pattern: /^\w+$/, message: '用户名只能包含字母、数字、下划线!' }
  ]"
      >
        <a-input v-model:value="formData.name" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script setup>
import {ref, defineProps, defineEmits, watch} from 'vue';

const props = defineProps({
  mhandleCancel: Function,
  mhandleOk: Function,
  mshowModals: Boolean
});

const emit = defineEmits(['update:mshowModals']);

const localShowModals = ref(props.mshowModals);

// 表单数据
const formData = ref({
  name: '',
  // 其他表单字段
});

// 表单验证规则
const formRules = {
  // 注意：这里的key 'username' 应该与a-form-item的name属性一致
  name: [
    { required: true, message: '请输入用户名!' },
    { min: 3, max: 12, message: '用户名长度在 3 到 12 个字符之间!' },
    { pattern: /^\w+$/, message: '用户名只能包含字母、数字、下划线!' }
  ],
  // 可以继续添加其他字段的验证规则
};


// 监听 prop 的变化以同步 localShowModals
watch(() => props.mshowModals, (newValue) => {
  localShowModals.value = newValue;
});


// 处理弹窗的确定按钮点击
function handleOk() {
  if (props.mhandleOk) {
    props.mhandleOk();
  }
  // 如果需要关闭模态框，也可以在这里调用 emit
  // emit('update:mshowModals', false);
}

// 处理弹窗的取消按钮点击
function handleCancel() {
  if (props.mhandleCancel) {
    props.mhandleCancel();
  } else {
    emit('update:mshowModals', false);
  }
}
</script>

<style scoped>
</style>
