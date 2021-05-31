package sjk.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sjk.spring.service.SungJukService;


public class HelloSpringJDBCApp01 {
	
	// 스프링 JDBC를 이용한 프로젝트
	
	// 기존 JDBC API 이용시 문제점
	// JDBC는 데이터베이스에 접근하기 위한 자바 표준 API
	// 하지만, JDBC 표준 API를 사용하면서 발생하는 문제는
			
	// 1) Connection/PreparedStatement/ResultSet 객체관리
	// 2) 복잡한 예외/SQLException 처리 문제 - 원인파악 모호
	// 3) DriverManager/JNDI를 이용한 Connection 객체 취득 문제      
			
	// 하지만, 스프링 프레임워크의 JDBC 기능을 이용하면
	// 위 문제 대부분이 해결
	
	// persistance layer 개발의 주요 API
	// JDBC      : 자바 표준 API
	// JPA       : J2EE 응용프로그램 제작시 persistance layer 개발을 위한 표준 API
	// Hibernate : ORM 기반 persistance layer 개발 API
	// myBatis   : SQL Mapping 기반 persistance layer 개발 API
	
	// spring JDBC template/support 클래스
	// jdbc.core.JdbcTemplate
	// jdbc.core.JdbcSimpleTemplate
	// jdbc.core.namedparam.NamedParameterJdbcTemplate
	// orm.hibernate.HibernateTemplate
	// orm.mybatis.SqlMapClientTemplate
	
	// 필요 라이브러리
	// spring-jdbc
	// spring-tx

	public static void main(String[] args) {
		ApplicationContext ctx =
				new ClassPathXmlApplicationContext("springjdbc01.xml");
		
		SungJukService sjsrv =
				(SungJukService) ctx.getBean("sjsrv");
		
		// 성적 데이터 생성 후 DB에 저장
		sjsrv.newSungJuk();

	}

}
