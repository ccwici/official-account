package com.xmlmg.wechat.controller.wechat;

import com.xmlmg.wechat.common.controller.WechatBaseController;
import com.xmlmg.wechat.service.chat.ChatService;
import com.xmlmg.wechat.common.util.SignUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@ApiOperation("公众号互动")
public class WechatController extends WechatBaseController {
    private final Logger logger = LoggerFactory.getLogger(ChatService.class);

    @Autowired
    ChatService chatService;
    /**
     * 微信消息接收和token验证
     */
    @RequestMapping(value = "/wechat.do", method = RequestMethod.GET)
    public void weChat(Model model, HttpServletRequest request,HttpServletResponse response) throws IOException {
        // 微信加密签名
        String signature = request.getParameter(WX_SIGNATURE);
        // 时间戳
        String timestamp = request.getParameter(WX_TIMESTAMP);
        // 随机数
        String nonce = request.getParameter(WX_NONCE);
        // 随机字符串
        String echostr = request.getParameter(WX_ECHOSTR);
        doGet(response, signature, timestamp, nonce, echostr);
    }

    private void doGet(HttpServletResponse response, String signature, String timestamp, String nonce, String echostr) throws IOException {
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (signature != null && SignUtil.checkSignature(publicAccountToken, signature, timestamp, nonce)) {
            try {
                PrintWriter print = response.getWriter();
                print.write(echostr);
                print.flush();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        } else {
            logger.error("签名校验不通过");
        }
    }

    @RequestMapping(value = "/wechat.do", method = RequestMethod.POST)
    @ResponseBody
    public void getWeiXinMessage(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 接收参数微信加密签名、 时间戳、随机数
        String signature = request.getParameter(WX_SIGNATURE);
        String timestamp = request.getParameter(WX_TIMESTAMP);
        String nonce = request.getParameter(WX_NONCE);

        try(PrintWriter out = response.getWriter()) {
            // 请求校验
            if (signature != null && SignUtil.checkSignature(publicAccountToken, signature, timestamp, nonce)) {
                // 调用核心服务类接收处理请求
                String respXml = chatService.processRequest(request);
                out.print(respXml);
            } else {
                logger.error("签名校验不通过");
            }
        }
    }
}
