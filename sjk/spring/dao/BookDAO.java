package sjk.spring.dao;

import java.util.List;

import sjk.spring.vo.Book;

public interface BookDAO {
	
	int insertBook(Book b);
	List<Book> selectBook();
	Book selectOneBook(String bookid);
	int updateBook(Book b);
	int deleteBook(String bookid);
	
	// if (cnt>0) sysout... 대신 int를 앞에 추가함으로써 DAO가 하나의 일에만 집중할 수 있게 함
}
