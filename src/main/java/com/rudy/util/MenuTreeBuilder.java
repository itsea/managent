package com.rudy.util;

import com.rudy.entity.MenuInfo;
import com.rudy.entity.MenuTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MenuTreeBuilder {

    public static List<MenuTree> getChildPerms(List<MenuInfo> list, int parentId){
        List<MenuTree> returnList = new ArrayList<MenuTree>();
        for (MenuInfo menu : list){
            //根据传入的某个父节点ID，遍历该父节点的所有子节点
            if (menu.getParentId() == parentId){
                MenuTree tree = new MenuTree();
                tree.setId(menu.getMenuId());
                tree.setTitle(menu.getMenuName());
                tree.setChecked(false);
                //开始递归，把所有菜单和当前菜单放入
                recursionFn(list,tree);
                returnList.add(tree);
            }
        }
        return returnList;
    }

    public static void recursionFn(List<MenuInfo> list, MenuTree tree){
        List<MenuTree> childList = getChildList(list, tree);
        tree.setChildren(childList);
        for (MenuTree child : childList){
            if (hasChild(list, child)){
                Iterator<MenuTree> it = childList.iterator();
                while (it.hasNext()){
                    MenuTree menuTree = it.next();
                    recursionFn(list,menuTree);
                }
            }
        }
    }

    public static List<MenuTree> getChildList(List<MenuInfo> list, MenuTree tree){
        List<MenuTree> treeList = new ArrayList<MenuTree>();
        Iterator<MenuInfo> it = list.iterator();
        while (it.hasNext()){
            MenuInfo menu = it.next();
            if (menu.getParentId() == tree.getId()){
                MenuTree menuTree = new MenuTree();
                menuTree.setId(menu.getMenuId());
                menuTree.setTitle(menu.getMenuName());
                menuTree.setChecked(false);
                treeList.add(menuTree);
            }
        }
        return treeList;
    }

    public static boolean hasChild(List<MenuInfo> list, MenuTree tree){
        int size = getChildList(list,tree).size();
        if (size > 0){
            return true;
        }
        else {
            return false;
        }
    }
}
