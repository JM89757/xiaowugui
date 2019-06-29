package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.entity.PageResult;
import com.pinyougou.pojo.entity.Result;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandService;

    @RequestMapping("/findAll.do")
    public List<TbBrand> findAll() {
        return brandService.findAll();
    }

    @RequestMapping("/findPage.do")
    //public PageResult findPage(@RequestParam(name = "page") int page, @RequestParam(name = "size") int rows)
    //前后端传递参数不一致时候使用RequestParam注解进行匹配
    public PageResult findPage(Integer page, Integer rows) {
        return brandService.findPage(page, rows);
    }

    @RequestMapping("/add.do")
    public Result add(@RequestBody TbBrand brand) {
        try {
            brandService.add(brand);
            return new Result(true, "Success");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "Failure");
        }
    }

    @RequestMapping("/update.do")
    public Result update(@RequestBody TbBrand brand) {
        try {
            brandService.update(brand);
            return new Result(true, "Success");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "Failure");
        }
    }

    @RequestMapping("/findOne.do")
    public TbBrand findOne(Long id) {
        return brandService.findOne(id);
    }


    @RequestMapping("/delete.do")
    public Result delete(Long[] ids) {
        try {
            brandService.delete(ids);
            return new Result(true, "Success");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "Failure");
        }
    }

    @RequestMapping("/search.do")
    public PageResult search(@RequestBody TbBrand brand, Integer page, Integer rows) {
        return brandService.findPage(brand, page, rows);
    }

}
