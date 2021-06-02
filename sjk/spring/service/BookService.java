package sjk.spring.service;

import java.util.List;

import sjk.spring.vo.Book;

public interface BookService {

	void newBook();
	List<Book> readBook();
	Book readOneBook(String bookid);
	void modifyBook(Book b);
	void removeBook(String bookid);

}
