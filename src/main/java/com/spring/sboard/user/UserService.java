package com.spring.sboard.user;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.sboard.common.Const;
import com.spring.sboard.common.MailUtils;
import com.spring.sboard.common.SecurityUtils;
import com.spring.sboard.model.AuthDTO;
import com.spring.sboard.model.AuthEntity;
import com.spring.sboard.model.UserEntity;
import com.spring.sboard.model.UserImgEntity;

// dao, service는 세트
@Service
public class UserService {

//	new()없이 객체 접근 가능
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private MailUtils mailUtils;
		
	public UserEntity selUser(UserEntity param) {
		return mapper.selUser(param);
	}
	
//	 로그인
	public int login(UserEntity param, HttpSession hs) {
		UserEntity dbData = selUser(param);
		if(dbData == null) { 
			return 2; // 아이디 없음
		}
		String cryptLoginPw = SecurityUtils.hashPassword(param.getUser_pw(), dbData.getSalt());
		
		if(!cryptLoginPw.equals(dbData.getUser_pw())) { 
			return 3; // 비밀번호 다름
		}
		
		// 보안을 위해 세션값 제거
		dbData.setSalt(null);
		dbData.setUser_pw(null);
		
		// 실수 방지를 위해서 final로 만듦
		hs.setAttribute(Const.KEY_LOGINUSER, dbData);
		
		return 1; // 로그인 성공
	}
	
//	회원가입
	public int insUser(UserEntity param) {
		String salt = SecurityUtils.getSalt();
		String encryptPw = SecurityUtils.hashPassword(param.getUser_pw(), salt);
		
		param.setSalt(salt);
		param.setUser_pw(encryptPw);
		
		return mapper.insUser(param);
	}
	
//	비밀번호찾기 1 - 성공 2 - 아이디확인 0 - 메일전송실패
	public int findPwProc(AuthEntity p) {
//		이메일 주소 얻어오기
		UserEntity param = new UserEntity();
		param.setUser_id(p.getUser_id());
		UserEntity vo = selUser(param);
		if(vo == null) {
			return 2; // 아이디 확인 
		}
		
		String email = vo.getEmail();
		
		String code = SecurityUtils.getPrivateCode(10);
		System.out.println("code = " + code);
		
		mapper.delAuth(p); // 코드가 입력되어있을 경우를 대비해 일단 삭제
		
		p.setCd(code);
		mapper.insAuth(p);
		
		System.out.println("email - "  + email);
		
		return mailUtils.sendFindPwEmail(email, p.getUser_id(), code);
	}
	
//	비밀번호 변경
	public int findPwAuthProc(AuthDTO p) {
//		cd, user_id 확인 처리
		AuthEntity ae = mapper.selAuth(p);
		if(ae == null) {
			return 0;  // id 없음
		}else if(ae.getRest_sec() > Const.AUTH_REST_SEC) {
			return 2; // 인증 제한 시간 초과 
		}
		
//		비밀번호 암호화
		String salt = SecurityUtils.getSalt();
		String encryptPw = SecurityUtils.hashPassword(p.getUser_pw(), salt);
		
		UserEntity ue = new UserEntity();
		ue.setUser_id(p.getUser_id());
		ue.setUser_pw(encryptPw);
		ue.setSalt(salt);
		
		return mapper.updUser(ue);
	}
	
//	이미지 업로드
	public int profileUpload(MultipartFile[] imgs, HttpSession hs) {
		int i_user = SecurityUtils.getLoingUserPk(hs);
		
//																    톰캣이 구동되는 위치를 잡기편하다
		String basePath = hs.getServletContext().getRealPath("/resources/img/user" + i_user + "/");
		System.out.println("basePath = "+basePath);
		
		try {
			for (int i = 0; i < imgs.length; i++) {
				MultipartFile files = imgs[i];
//										  파일이름이 중복되는 것을 막는다
				String fileName = UUID.randomUUID().toString();
				String extension = FilenameUtils.getExtension(files.getOriginalFilename());
				fileName += "." + extension;
				
				File file = new File(basePath + fileName);
				files.transferTo(file);
				
				if(i == 0) { // 이미지 업데이트
					UserEntity p = new UserEntity();
					p.setI_user(i_user);
					p.setProfile_img(fileName);
					
					mapper.updUser(p);
				}
				
				UserImgEntity uie = new UserImgEntity();
				uie.setI_user(i_user);
				uie.setImg(fileName);
				
				mapper.insUserImg(uie);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}
}
