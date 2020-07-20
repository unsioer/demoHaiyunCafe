package com.example.demoHaiyunCafe.Controller;


import com.example.demoHaiyunCafe.Bean.Item;
import com.example.demoHaiyunCafe.Bean.Order;
import com.example.demoHaiyunCafe.Mapper.ItemRepository;
import com.example.demoHaiyunCafe.Service.ItemServiceImpl;
import com.example.demoHaiyunCafe.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.core.io.ResourceLoader;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ItemController {
    @Autowired
    private ItemServiceImpl itemService;


    @GetMapping("/itemManage")
    public ModelAndView list(Item item, Integer pageNum, Model model) {
        //model.addAttribute("title","菜品管理");
        List<String> itemTypeList = new ArrayList<String>();
        itemTypeList.add("饮料");
        itemTypeList.add("小吃");
        itemTypeList.add("主食");
        model.addAttribute("itemTypeList",itemTypeList );

        List<Item> temp = itemService.findAll();

        List<Item>  itemList = new ArrayList<>();
        List<Item>  itemListName ;
        List<Item>  itemListType ;
        List<Item>  itemListPrice ;

        if(item.getItemname()!=null&&!item.getItemname().equals("")){
            itemListName = (itemService.findAllByItemname(item.getItemname()));
        }
        else
            itemListName = temp;
        if(item.getType()!=null&& !item.getType().equals("0")){
            itemListType = itemService.findAllByType(item.getType());
        }
        else
            itemListType = temp;
        if(item.getPrice()!=null){
            itemListPrice = itemService.findAllByPrice(item.getPrice());
        }
        else
            itemListPrice = temp;

        itemList = itemListName.stream()
                .filter(t->itemListType.contains(t))
                .collect(Collectors.toList());

        itemList = itemList.stream()
                    .filter(t->itemListPrice.contains(t))
                    .collect(Collectors.toList());

        if (pageNum == null){
            pageNum = 1;
        }
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNum - 1, 20, sort);

        Page<Item> page = listConvertToPage(itemList,pageable);
        model.addAttribute("pageInfo",page);

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

    public <T> Page<T> listConvertToPage(List<T> list, Pageable pageable) {
        int start = (int)pageable.getOffset();
        int end = (start + pageable.getPageSize()) > list.size() ? list.size() : ( start + pageable.getPageSize());
        return new PageImpl<T>(list.subList(start, end), pageable, list.size());
    }
}
