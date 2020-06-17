package com.cumt.internally.controller;

import com.cumt.internally.model.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NNroc
 * @date 2020/6/17 22:19
 */
@RestController
public class PictureController {
    /**
     * 上传图片
     *
     * @return
     */
    @RequestMapping("/send_pic")
    public Result sendPic() {
        return null;
    }

    /**
     * 获取图片
     *
     * @return
     */
    @RequestMapping("/get_pic")
    public Result getPic() {
        return null;
    }
}
