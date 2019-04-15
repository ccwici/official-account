package com.xmlmg.wechat.common.util;


import com.xmlmg.wechat.vo.MenuVo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @describe：
 * @version: 1.0
 */

public class TreeBuilder {

    public static Set<MenuVo> findRoots(Set<MenuVo> allNodes) {
        // 根节点
        Set<MenuVo> root = new HashSet<>();
        allNodes.forEach(node -> {
            if (node.getFather() == 0) {
                root.add(node);
            }
        });
        root.forEach(node ->
            findChildren(node, allNodes)
        );
        return root;
    }


    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    private static MenuVo findChildren(MenuVo treeNode, Set<MenuVo> treeNodes) {
        for (MenuVo it : treeNodes) {
            if (treeNode.getPid().equals(it.getFather())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<MenuVo>());
                }
                treeNode.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

    private TreeBuilder() {
        //
    }
}