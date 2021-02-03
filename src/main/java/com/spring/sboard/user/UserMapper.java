package com.spring.sboard.user;

import org.apache.ibatis.annotations.Mapper;

import com.spring.sboard.model.AuthEntity;
import com.spring.sboard.model.UserEntity;

@Mapper
public interface UserMapper {
// 유연성을 위해 인터페이스 생성
	
//	유저의 정보를 얻기위함
	UserEntity selUser(UserEntity param);
	
	int insUser(UserEntity param);
	
//	비밀번호찾기/////////////////////////////////
	int insAuth(AuthEntity p);
	
	AuthEntity selAuth(AuthEntity p);
	
	int delAuth(AuthEntity p);
}
