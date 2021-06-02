package sjk.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sjk.spring.service.BookService;
import sjk.spring.vo.Book;

public class BookJDBCApp02 {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx =
				new ClassPathXmlApplicationContext("springjdbc02.xml");
		
		BookService bksrv =
				(BookService) ctx.getBean("bksrv02");
		
		// bksrv.newBook();
		// bksrv.readBook();
		// bksrv.readOneBook("999");
		// Book b = new Book("999", "스프링기초", "더나은출판사", "30000");
		// bksrv.modifyBook(b);
		bksrv.removeBook("100");
		
		
		
	}

}
