<template>
  <el-dialog :visible.sync="visible" :show-close="false" :close-on-click-modal="false" :modal="true" :close-on-press-escape="false" title="给个人发信息" width="600px">
    <button type="button" aria-label="Close" class="el-dialog__headerbtn" @click.stop="cancelModal"><i class="el-dialog__close el-icon el-icon-close" /></button>
    <el-form ref="msgSenderForm" :rules="addRules" :model="msgSenderForm" class="form-wrapper" label-width="110px">
      <el-form-item label="消息内容：" prop="message">
        <el-input v-model="msgSenderForm.message" type="textarea" placeholder="请输入要发送的消息内容" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="buttons-wrap">
      <el-button type="primary" @click="submitForm">确定</el-button>
    </div>
  </el-dialog>
</template>
<script>
import { post } from '@/utils/request'
export default {
  props: {
    visible: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      msgSenderForm: {
        openid: '',
        message: ''
      },
      addRules: {
        message: [{ required: true, message: '请输入消息内容！', trigger: 'blur' }]
      }
    }
  },
  methods: {
    initForm(openidParam) {
      this.msgSenderForm = {
        openid: openidParam,
        message: ''
      }
      if (this.$refs.msgSenderForm) {
        this.$refs.msgSenderForm.resetFields()
      }
    },
    sendMsg(_this) {
      new Promise((resolve, reject) => {
        const openid = _this.msgSenderForm.openid
        const message = _this.msgSenderForm.message
        post('/message/publish/personal/', { 'openid': openid, 'message': message }).then(res => {
          // 关闭弹窗，触发父组件修改visible值
          _this.$emit('update:visible', false)
          alert('success')
          resolve()
        }).catch(error => {
          // 关闭弹窗，触发父组件修改visible值
          _this.$emit('update:visible', false)
          reject(error)
        })
      })
    },
    submitForm() {
      const _this = this

      this.$refs['msgSenderForm'].validate((valid) => {
        if (valid) {
          _this.sendMsg(_this)
        } else {
          return false
        }
      })
    },
    cancelModal() {
      // 关闭弹窗，触发父组件修改visible值
      this.$emit('update:visible', false)
    }
  }
}
</script>
<style lang="scss" scoped>
.buttons-wrap {
  .el-button {
    margin-right: 20px;
    min-width: 100px;
  }
}
</style>
