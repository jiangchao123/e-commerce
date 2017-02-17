package com.frontController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sa on 2017-02-14.
 */
@Controller
@RequestMapping("/front")
public class FrontController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "/front/index";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
        return "/front/list";
    }

    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String add(){
        return "/front/new";
    }

    @RequestMapping(value = "/contactus",method = RequestMethod.GET)
    public String contactus(){

        return "/front/contactus";
    }

    @RequestMapping(value = "/view",method = RequestMethod.GET)
    public String view(){
        return "/front/view";
    }

    @RequestMapping(value = "/product_detail",method = RequestMethod.GET)
    public String product_detail(){
        return "/front/product_detail";
    }

    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public String products(){
        return "/front/products";
    }
}
