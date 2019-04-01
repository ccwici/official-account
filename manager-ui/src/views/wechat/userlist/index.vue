<template>
  <div>
    <el-table :data="tableData" :row-class-name="tableRowClassName" row-key="openid" style="width: 100%">
      <el-table-column prop="nickname" label="微信名" width="180"/>
      <el-table-column :formatter="formatDate" prop="subscribe_time" label="订阅时间" width="180"/>
      <el-table-column prop="openid" label="openid"/>
      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button size="small" type="text" @click="sendMsg('personalMsgSender', scope.row)">发信息</el-button>
        </template>
      </el-table-column>
    </el-table>
    <personal-msg-sender ref="personalMsgSender" :visible.sync="personalMsgSenderVisible" />
  </div>
</template>

<style>
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>

<script>
import request from '@/utils/request'
import PersonalMsgSender from '@/components/wechat/PMSender'
import dateUtil from '@/utils/datetime'
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
      request({ url: '/user/list/' }).then(res => {
        _this.tableData = res.data
      }).catch(error => {
        reject(error)
      })
    })
  },
  methods: {
    sendMsg(msgForm, row) {
      if (this.$refs[msgForm]) {
        this.$refs[msgForm].initForm(row.openid)
      }
      this.personalMsgSenderVisible = true
    },
    formatDate(row) {
      return dateUtil.timestampToTime(row.subscribe_time)
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
