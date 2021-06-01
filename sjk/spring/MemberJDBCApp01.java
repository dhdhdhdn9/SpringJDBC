package sjk.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sjk.spring.service.MemberService;


public class MemberJDBCApp01 {

	public static void main(String[] args) {
		ApplicationContext ctx =
				new ClassPathXmlApplicationContext("springjdbc01.xml");
		
//		MemberService mbsrv =
//				(MemberService) ctx.getBean("mbsrv");
		MemberService mbsrv =
				(MemberService) ctx.getBean("mbsrv");
		
		// 회원 등록
		// mbsrv.newMember();
		
		// 회원 전체 조회
		// mbsrv.readAllMember();
		
		// 회원 상세 조회
		// mbsrv.readOneMember(2);
		
		// 회원 수정
		// mbsrv.modifyMember();
		
		// 회원 삭제
		// mbsrv.removeMember();

	}

}
