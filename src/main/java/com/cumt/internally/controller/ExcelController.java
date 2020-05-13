package com.cumt.internally.controller;

import com.cumt.internally.model.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NNroc
 * @date 2020/5/13 16:18
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {
    /**
     * 导出风险
     *
     * @return
     */
    @RequestMapping("/export/risk")
    public Result exportRisk() {
        return null;
    }

    /**
     * 导入风险
     *
     * @return
     */
    @RequestMapping("/import/risk")
    public Result importRisk() {
        return null;
    }

    /**
     * 清空风险
     *
     * @return
     */
    @RequestMapping("/clear/risk")
    public Result clearRisk() {
        return null;
    }

}
