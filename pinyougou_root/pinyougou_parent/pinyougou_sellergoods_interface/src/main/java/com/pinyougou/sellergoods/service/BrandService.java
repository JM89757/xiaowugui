package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;

import java.util.List;
import java.util.Map;

public interface BrandService {

    public List<Map<String, Object>> selectOptionList();

    public List<TbBrand> findAll();

    public PageResult findPage(Integer pageNum, Integer pageSize);

    public void add(TbBrand brand);

    public void update(TbBrand brand);

    public TbBrand findOne(Long id);

    public void delete(Long[] ids);

    /**
     * @param brand    对象
     * @param pageNum  当前页码
     * @param pageSize 当前总条数
     * @return 封装到结果集
     */
    public PageResult findPage(TbBrand brand, Integer pageNum, Integer pageSize);

}
