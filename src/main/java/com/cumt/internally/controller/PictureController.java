package com.cumt.internally.controller;

import com.cumt.internally.annotation.AdministratorToken;
import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Result;
import com.cumt.internally.utils.FileUtil;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author NNroc
 * @date 2020/6/17 22:19
 */
@Slf4j
@RestController
@RequestMapping("/picture")
public class PictureController {
    @Autowired
    private ResponseData responseData;

    /**
     * 上传图片
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/send_pic")
    public Result sendPic(MultipartFile svg, HttpServletRequest request) throws Exception {
        // 判断用户是否上传了文件
        if (!svg.isEmpty()) {
            // 文件上传的地址
//            String path = System.getProperty("user.dir");
            String path = getJarRoot();
            // 当前路径:G:\githubuse\internally\target
            System.out.println("当前路径:" + path);
            // 获取文件的名称，去掉后缀
            String fileName = svg.getOriginalFilename();
            System.out.println(fileName);
            // 限制文件上传的类型
            String contentType = svg.getContentType();
            if ("image/svg+xml".equals(contentType)) {
                // 完成文件的上传
                FileUtil.uploadFile(svg.getBytes(), path, fileName);
                return responseData.write("上传成功！", 200, new HashMap<>());
            }
        }
        return responseData.write("上传失败！", 404, new HashMap<>());
    }

    /**
     * 获取图片
     *
     * @return
     */
    @AdministratorToken
    @RequestMapping("/get_pic")
    public Result getPic(@RequestParam String svg) throws UnknownHostException {
        // 判断用户是否上传了文件
        if (!StringUtils.isBlank(svg)) {
            if (!svg.endsWith(".svg")) {
                return responseData.write("请求错误", 400, new HashMap<>());
            }
            // 返回 svg 文件路径
            String url = InetAddress.getLocalHost() + ":8046/internally/piloting/picture/image/" + svg;
            HashMap map = new HashMap();
            map.put("svg", url);
            return responseData.write("成功！", 200, map);
        }
        return responseData.write("未输入信息！", 400, new HashMap<>());
    }


    /**
     * 获得项目工程的绝对路径
     */
    public String getWebRoot(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/");
    }

    /**
     * 获得发布后的jar当前路径
     */
    public String getJarRoot() {
        ApplicationHome home = new ApplicationHome(getClass());
        File jarFile = home.getSource();
        return jarFile.getParentFile().getPath();
    }

}
