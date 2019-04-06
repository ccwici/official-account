<template>
  <div>
    <el-table :data="tableData" :row-class-name="tableRowClassName" row-key="id" style="width: 100%">
      <el-table-column label="操作名称" width="180">
        <template slot-scope="scope">
          <el-button size="small" type="text" @click="clickOperationName(scope.row)">{{ scope.row.name }}</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="remark" label="备注说明"/>
      <el-table-column prop="api" label="API"/>
    </el-table>
    <personal-msg-sender ref="personalMsgSender" :visible.sync="personalMsgSenderVisible" />
  </div>
</template>

<style>
.el-table .warning-row {
  background: oldlace
}

.el-table .success-row {
  background: #f0f9eb
}
</style>

<script>
import { get } from '@/utils/request'
import PersonalMsgSender from '@/components/wechat/PMSender'
export default {
  components: {
    'personal-msg-sender': PersonalMsgSender
  },
  data() {
    return {
      tableData: [],
      personalMsgSenderVisible: false
    }
  },
  mounted: function() {
    const _this = this // 很重要！！
    new Promise((resolve, reject) => {
      get('/oponline/shortcutOp/entranceList/').then(res => {
        _this.tableData = res.data
      }).catch(error => {
        reject(error)
      })
    })
  },
  methods: {
    clickOperationName(row) {
      const id = row.id
      const _this = this
      new Promise((resolve, reject) => {
        get('/oponline/shortcutOp/more/' + id).then(res => {
          if ((typeof res.data === 'string') && res.data.constructor === String) {
            this.$confirm({ message: res.data })
            return // 直接显示执行结果
          }
          _this.tableData = res.data
        }).catch(error => {
          reject(error)
        })
      })
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === 1) {
        return 'warning-row'
      } else if (rowIndex === 3) {
        return 'success-row'
      }
      return ''
    }
  }
}
</script>
