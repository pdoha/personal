package com.hospital.admin.menus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {

    private final static Map<String, List<MenuDetail>> menus;

    static {
        menus = new HashMap<>();
        menus.put("member", Arrays.asList(
                new MenuDetail("setting", "기본설정", "/admin/member"),
                new MenuDetail("list", "회원목록", "/admin/member/list")

        ));

        menus.put("borad", Arrays.asList(
                new MenuDetail("list", "게시판목록", "/admin/board"),
                new MenuDetail("add", "게시판등록", "/admin/board/add"),
                new MenuDetail("posts", "게시글관리", "/admin/board/posts")

        ));
    }

    public static List<MenuDetail> getMenus(String code){
        return menus.get(code);
    }
}
