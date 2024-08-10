<template>
  <aside class="left-sidebar">
    <ul class="sidebar-links">
      <li v-for="item in menuItems" :key="item.id">
        <router-link :to="item.path">{{ item.name }}</router-link>
      </li>
    </ul>
    <!-- 可以添加更多元素，如搜索框、用户信息等 -->
  </aside>
</template>

<script setup>
import { defineProps, onMounted, ref, watch } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
// 定义 prop 类型，这里假设 menuType 是一个字符串
const props = defineProps({
  menuType: String
});

const menuItems = ref([]);

// 监听 prop 的变化以重新初始化 menuItems
watch(() => props.menuType, (newValue) => {
  init(newValue);
});

onMounted(() => {
  init(props.menuType); // 组件挂载时立即初始化
});

const init = (type) => {
  let pathType=type
  menuItems.value = []; // 清空现有项
  for (let i = 0; i < router.options.routes.length; i++) {
    if (router.options.routes[i].meta && router.options.routes[i].meta.type == pathType) {
      menuItems.value.push({
        id: menuItems.value.length, // 使用数组长度作为 id
        name: router.options.routes[i].meta.title,
        path: router.options.routes[i].path
      });
    }
  }
  // console.log('pathType',pathType)
  // console.log('menuItems.value',menuItems.value)
};
</script>

<style scoped>
.left-sidebar {
  background-color: #fff;
  padding: 20px;
  overflow-y: auto; /* 如果侧边栏内容过多，则允许滚动 */
  border-right: 1px solid #e8e8e8; /* 可选：添加右侧边框以分隔内容区域 */
}

.sidebar-links {
  list-style: none;
  padding: 0;
}

.sidebar-links li {
  margin-bottom: 8px; /* 链接项之间的间隔 */
}

.sidebar-link {
  color: #333;
  text-decoration: none;
  font-size: 16px;
  display: block;
  padding: 8px 16px;
  border-radius: 4px;
  transition: background-color 0.3s;
}
</style>
