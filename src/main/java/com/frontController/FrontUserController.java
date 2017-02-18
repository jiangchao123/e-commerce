package com.frontController;

import com.entity.UserDO;
import com.frontService.FrontUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by sa on 2017-02-15.
 */
@Controller
@RequestMapping("/front")
public class FrontUserController {

    @Autowired
    FrontUserService frontUserService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String userLogin(ModelMap modelMap){
        return "front/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String userLogin(UserDO userDO, ModelMap modelMap, HttpSession session){
        UserDO user= frontUserService.selectUserByUserName(userDO.getUsername());
        if(user !=null && userDO.getPassword().equals(user.getPassword())){
            session.setAttribute("user",userDO);
            modelMap.addAttribute("user",userDO);
            return "redirect:/front/index";
        }else
            return "front/login";
    }

}
