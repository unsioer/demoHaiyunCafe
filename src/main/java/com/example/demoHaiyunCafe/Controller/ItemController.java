package com.example.demoHaiyunCafe.Controller;


import com.example.demoHaiyunCafe.Bean.Item;
import com.example.demoHaiyunCafe.Mapper.ItemRepository;
import com.example.demoHaiyunCafe.Service.ItemServiceImpl;
import com.example.demoHaiyunCafe.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.core.io.ResourceLoader;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private ItemServiceImpl itemService;


    @GetMapping("/itemManage_{pageCurrent}_{pageSize}_{pageCount}")
    public ModelAndView list(Item item, @PathVariable(required = false) Integer pageCurrent,
                             @PathVariable(required = false) Integer pageSize,
                             @PathVariable(required = false) Integer pageCount,
                             Model model) {
        model.addAttribute("itemList",itemService.findAll());
        model.addAttribute("title","菜品管理");
        List<String> itemTypeList = new ArrayList<String>();
        itemTypeList.add("饮料");
        itemTypeList.add("小吃");
        itemTypeList.add("主食");
        model.addAttribute("itemTypeList",itemTypeList );
//        Integer pCurrent = Integer.parseInt(pageCount);
//        Integer pSize = Integer.parseInt(pageSize);
//        Integer pCount = Integer.parseInt(pageCount);
        String pageHTML = PageUtil.getPageContent("itemManage_{pageCurrent}_{pageSize}_{pageCount}?title=" + item.getItemname() + "&type=" + item.getType() + "&Price", pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("item", item);
        return new ModelAndView("item/itemManage","itemModel",model );
    }

    @GetMapping("/itemEdit")
    public ModelAndView itemEditGet(Model model,Item item){
        List<String> itemTypeList = new ArrayList<String>();
        itemTypeList.add("饮料");
        itemTypeList.add("小吃");
        itemTypeList.add("主食");
        model.addAttribute("itemTypeList",itemTypeList );
        if(item.getId()!=null){
            Item item1 = itemService.findById(item.getId());
            if(item1!=null){

            }
            model.addAttribute("item",item1);
        }
        return new ModelAndView("item/itemEdit","itemEditModel",model );
    }

    @PostMapping("/itemEdit")
    public ModelAndView itemEditPost(Model model,Item item){
        itemService.saveOrUpdateItem(item);
        List<String> itemTypeList = new ArrayList<String>();
        itemTypeList.add("饮料");
        itemTypeList.add("小吃");
        itemTypeList.add("主食");
        model.addAttribute("itemTypeList",itemTypeList );
        return new ModelAndView("item/itemEdit","itemEditModel",model );
    }

    @ResponseBody
    @PostMapping("/itemEditState")
    public String itemDelete(Integer id){
        itemService.deleteById(id);
        return "success";
    }
//    @GetMapping("{id}")
//    public ModelAndView view(@PathVariable("id") Integer id,Model model){
//        Item item = itemService.findById(id);
//        model.addAttribute("item",item);
//        model.addAttribute("title","查看菜品");
//
//        return new ModelAndView("item/itemManage");
//    }

}
