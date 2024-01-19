package com.hospital.admin.member;

import com.hospital.admin.menus.Menu;
import com.hospital.admin.menus.MenuDetail;
import com.hospital.commons.ExceptionProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller("adminMemberController")
@RequestMapping("/admin/member")
public class controllers implements ExceptionProcessor {


    //모든 템플릿영역에서 공유되어야하니까 @ModelAttribute
    // menuCode, subMenus 라는 속성값이 있으면 유지되게
    //컨트롤러내에서 모든 템플릿 영역에서 공유된다
    @ModelAttribute("menuCode")
    public String getMenuCode(){ //주메뉴 코드
        return "member";
    }

    @ModelAttribute("subMenus")
    public List<MenuDetail> getSubMenus(){
        return Menu.getMenus("member");
    }
    @GetMapping
    public String list(Model model){
        model.addAttribute("subMeunCode", "list");
        return "admin/member/list";
    }

    //공통기능
    public String commonProcess(String code){


    }


}
