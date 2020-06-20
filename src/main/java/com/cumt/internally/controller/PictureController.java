package com.cumt.internally.controller;

import com.cumt.internally.annotation.AdministratorToken;
import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Result;
import com.cumt.internally.utils.FileUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public Result sendPic(MultipartFile svg) throws Exception {
        // 判断用户是否上传了文件
        if (!svg.isEmpty()) {
            // 文件上传的地址;
            String path = getJarRoot() + "\\svg";
            // 当前路径:G:\githubuse\internally\target
            System.out.println("当前路径:" + Paths.get(path));
            if (!Files.exists(Paths.get(path))) {
                Files.createDirectories(Paths.get(path));
            }
            // 获取文件的名称
            String fileName = svg.getOriginalFilename();
            System.out.println(fileName);
            // 限制文件上传的类型
            String contentType = svg.getContentType();
            if ("image/svg+xml".equals(contentType)) {
                // 完成文件的上传
                FileUtil.uploadFile(svg.getBytes(), path, fileName);
                return responseData.write("上传成功！", 200, new HashMap<>());
            } else {
                return responseData.write("文件非svg形式！", 400, new HashMap<>());
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
    @RequestMapping("/get_all_pic")
    public Result getPic() {
        String path = getJarRoot() + "\\svg\\";
        HashMap map = new HashMap();
        return responseData.write("成功！", 200, map);
    }

    /**
     * 获取单个图片
     *
     * @param response
     * @param svg
     * @throws IOException
     */
    @RequestMapping("/get_pic/{svg}")
    public void getImage(HttpServletResponse response, @PathVariable("svg") String svg) throws IOException {
        response.setContentType("image/svg+xml;charset=utf-8");
        response.setHeader("Content-Disposition", "inline; filename=" + svg);
        ServletOutputStream outputStream = response.getOutputStream();
        String path = getJarRoot() + "\\svg\\";
        outputStream.write(Files.readAllBytes(Paths.get(path).resolve(svg)));
        outputStream.flush();
        outputStream.close();
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
