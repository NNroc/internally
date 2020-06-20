package com.cumt.internally.controller;

import com.cumt.internally.annotation.AdministratorToken;
import com.cumt.internally.component.ResponseData;
import com.cumt.internally.model.Result;
import com.cumt.internally.model.SvgMessage;
import com.cumt.internally.utils.FileUtil;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            String path = getJarRoot() + "/svg/";
            // 当前路径:G:\githubuse\internally\target
            if (!Files.exists(Paths.get(path))) {
                Files.createDirectories(Paths.get(path));
            }
            // 获取文件的名称
            String fileName = svg.getOriginalFilename();
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
    public Result getPic() throws UnknownHostException {
        // 指定路径
        String path = getJarRoot() + "/svg/";
        List<String> files = getFileName(path, ".svg", false);
        List<SvgMessage> panes = new ArrayList<>();
        for (String file : files) {
            SvgMessage svgMessage = new SvgMessage();
            svgMessage.setTitle(file);
            svgMessage.setSVGSrc(InetAddress.getLocalHost().getHostAddress() + ":8046/internally/piloting/picture/get_pic/" + file);
            panes.add(svgMessage);
        }
        HashMap map = new HashMap();
        map.put("panes", panes);
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
        String path = getJarRoot() + "/svg/";
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

    /**
     * 得到文件夹下的所有指定后缀文件名列表
     *
     * @param strFolderPath 文件夹路径
     * @param strSuffix     需要遍历的的文件后缀
     * @param blIsAbsPath   是否采用绝对路径返回
     * @return
     */
    public static List<String> getFileName(String strFolderPath, String strSuffix, boolean blIsAbsPath) {
        List<String> lsFileName = new ArrayList<String>();
        // 用于查找文件
        File getDocument;
        if (strFolderPath == null || strFolderPath.equals("")) {
            return null;
        } else {
            if (strFolderPath.substring(strFolderPath.length() - 1).equals("/")) {
                strFolderPath = strFolderPath.substring(0, strFolderPath.length() - 1);
            }
            getDocument = new File(strFolderPath);
        }
        // 存储文件容器
        String getFileName[];
        getFileName = getDocument.list();
        if (getFileName == null || getFileName.length < 1) {
            log.error("no file in path:" + strFolderPath + "! please check!!!");
            return null;
        }
        // 遍历整合
        for (int i = 0; i < getFileName.length; i++) {
            // 文件名合法性检查
            String strFileNameTmp = getFileName[i];
            if (strFileNameTmp.length() <= strSuffix.length()) {
                continue;
            }
            if (!strFileNameTmp.substring(strFileNameTmp.length() - strSuffix.length()).equals(strSuffix)) {
                // 文件后缀不符合的情况
                continue;
            }
            //文件路径加载
            String strFileName = "";
            if (blIsAbsPath) {
                strFileName = strFolderPath + File.separator + getFileName[i];
            } else {
                strFileName = getFileName[i];
            }
            log.debug("have loaded file = " + strFileName);
            lsFileName.add(strFileName);
        }
        return lsFileName;
    }
}
