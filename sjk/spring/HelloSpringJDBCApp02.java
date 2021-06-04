package sjk.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sjk.spring.service.SungJukService;


public class HelloSpringJDBCApp02 {
	
	// myBatis를 이용한 프로젝트
	
	// main -> service --> DAO
	//				(SungJuk)

	public static void main(String[] args) {
		ApplicationContext ctx =
				new ClassPathXmlApplicationContext("springjdbc02.xml");
		
		SungJukService sjsrv =
				(SungJukService) ctx.getBean("sjsrv02");
		
		// 성적 데이터 생성 후 DB에 저장
		// sjsrv.newSungJuk();
		
		// 성적 데이터 조회
		// sjsrv.readAllSungJuk();
		
		// 성적 데이터 상세 조회
		// sjsrv.readOneSungJuk(2);
		
		// 성적 데이터 수정
		// sjsrv.modifySungJuk();
		
		// 성적 데이터 삭제
		sjsrv.removeSungJuk();

	}

}
