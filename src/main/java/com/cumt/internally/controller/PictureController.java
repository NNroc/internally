package com.cumt.internally.controller;

import com.cumt.internally.annotation.AdministratorToken;
import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Result;
import com.cumt.internally.utils.FileUtil;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author NNroc
 * @date 2020/6/17 22:19
 */
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
    public Result sendPic(MultipartFile photo, @RequestParam(value = "content") String content) throws Exception {
        // 判断用户是否上传了文件
        if (!photo.isEmpty()) {
            // TODO 当前路径
            String path = System.getProperty("user.dir");
            System.out.println("当前路径:" + path);
            // 文件上传的地址
//            String path = "/root/use/easy/";
            // 获取文件的名称，去掉后缀
            String fileName = photo.getOriginalFilename();
            String[] names = fileName.split("\\.svg");
            fileName = names[0];
            // 限制文件上传的类型
            String contentType = photo.getContentType();
            System.out.println("contentType:" + contentType);
            if ("svg".equals(contentType)) {
                // 完成文件的上传
                FileUtil.uploadFile(photo.getBytes(), path, fileName);
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
    public Result getPic() {
        return null;
    }
}
