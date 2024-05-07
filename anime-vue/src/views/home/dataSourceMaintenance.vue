<template>
  <Card>
    <Card-header>
      <div class="card-header-content">
        <img src="../../assets/case/tree_icon@2x.png" alt="数据源图标" />
        <span>数据来源维护</span>
      </div>
      <div class="card-header-toolbar">
        <button @click="refreshData" icon="reload">刷新</button>
        <button :disabled="!disabled1" @click="addInfo" icon="plus">新增</button>
        <button :disabled="!disabled1 || disabled2" @click="editInfo" icon="edit">修改</button>
        <button :disabled="!disabled1 || disabled2" @click="deleteInfo" icon="delete">删除</button>
      </div>
    </Card-header>
    <Card-body>
        <a-grid :columns="2" :style="{ maxWidth: '100%' }">
          <a-grid-col :span="14">
            <Card :body-style="{ padding: '24px' }" class="shadow">
              <template #title>
                <div class="card-title">
                  <img :src="require('@/assets/case/detail_icon@2x.png')" />
                  <span>数据来源列表</span>
                </div>
              </template>
              <a-table
                  select-type="single"
                  :table-height="tableMaxHeight"
                  :column-defs="columnDefs"
                  :loading="tableLoading"
                  ref="dataSourceTable"
                  @select="selectData"
                  @row-click="selectData"
                  :data-source="tableList"
              />
            </Card>
          </a-grid-col>
        </a-grid>
        <!-- 这里是内容区域，可以使用 a-row, a-col, a-table 等组件 -->
    </Card-body>
  </Card>
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
    const disabled1 = ref(false); // 假设的 disabled1 状态
    const disabled2 = ref(false); // 假设的 disabled2 状态


    // 方法
    const refreshData = () => {
      // 刷新数据的逻辑
      console.log('刷新数据');
    };

    const addInfo = () => {
      // 新增数据的逻辑
      console.log('新增数据');
    };

    const editInfo = () => {
      // 修改数据的逻辑
      console.log('修改数据');
    };

    const deleteInfo = () => {
      // 删除数据的逻辑
      console.log('删除数据');
    };

    // 由于 debounce 通常用于优化函数调用的频率，这里不直接实现 debounce
    // 但如果需要，可以引入 lodash 的 debounce 函数或自行实现

    // 返回需要在模板中使用的数据和方法
    return {
      disabled1,
      disabled2,
      refreshData,
      addInfo,
      editInfo,
      deleteInfo,
    };
  },
};
</script>

<style scoped>
/* 样式部分，根据需要添加 */
.card-header-content {
  display: flex;
  align-items: center;
}

.card-header-toolbar {
  margin-left: auto; /* 将工具栏移到右边 */
}
</style>
