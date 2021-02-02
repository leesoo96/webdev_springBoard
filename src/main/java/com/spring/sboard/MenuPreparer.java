package com.spring.sboard;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.sboard.common.Const;

@Component("menuPreparer")
public class MenuPreparer implements ViewPreparer{

	@Autowired
	private CommonMapper mapper;
	
	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {		
		if(Const.menuList == null) {
			System.out.println("메뉴 리스트를 불러옵니다."); 
			Const.menuList = mapper.selManageBoardList();
		}
        attributeContext.putAttribute("menuList", new Attribute(Const.menuList), true);		
	}
}
