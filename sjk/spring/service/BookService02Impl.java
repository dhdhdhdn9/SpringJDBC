package sjk.spring.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sjk.spring.dao.BookDAO;
import sjk.spring.vo.Book;

@Service("bksrv02")
public class BookService02Impl implements BookService {
	
	@Autowired
	private BookDAO bkdao02;

	@Override
	public void newBook() {
		Book b  = new Book("100", "스프링5 기초", "더 좋은", "56000");
		int cnt = bkdao02.insertBook(b);
		if (cnt>0) System.out.println("도서 등록 완료!");
	}

	@Override
	public List<Book> readBook() {
		StringBuffer sb = new StringBuffer();
		List<Book> bs = bkdao02.selectBook();
		for(Book b : bs) sb.append(b);
		System.out.println(sb.toString());
		return null;
	}

	@Override
	public Book readOneBook(String bookid) {
		Book b = bkdao02.selectOneBook("999");
		System.out.println(b);
		return null;
	}

	@Override
	public void modifyBook(Book b) {
		int cnt = bkdao02.updateBook(b);
		if (cnt>0) System.out.println("도서 정보 수정 완료!");
	}

	@Override
	public void removeBook(String bookid) {
		int cnt = bkdao02.deleteBook(bookid);
		if (cnt>0) System.out.println("도서 정보 삭제 완료!");
	}
	
	
	
	

}
