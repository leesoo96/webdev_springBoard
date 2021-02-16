package com.spring.sboard.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.sboard.model.AuthDTO;
import com.spring.sboard.model.AuthEntity;
import com.spring.sboard.model.UserEntity;
import com.spring.sboard.model.UserImgEntity;

@Mapper
public interface UserMapper {
// 유연성을 위해 인터페이스 생성
	
//	로그인
	UserEntity selUser(UserEntity param);
	
//	회원가입
	int insUser(UserEntity param);
	
//	비밀번호 변경 
	int updUser(UserEntity p);
	
//	비밀번호찾기/////////////////////////////////
	int insAuth(AuthEntity p);
	
	AuthEntity selAuth(AuthEntity p);
	
	int delAuth(AuthEntity p);
	
//	프로필 이미지 변경
	int insUserImg(UserImgEntity p);

	List<UserImgEntity> selUserImgList(UserEntity p);
	
	int delProfileImg(UserImgEntity p);
}
