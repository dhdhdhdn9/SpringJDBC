package sjk.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sjk.spring.vo.Book;

@Repository("bkdao02")
public class BookDAO02Impl implements BookDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertBook(Book b) {
		return sqlSession.insert("book.insertBook", b);
	}

	@Override
	public List<Book> selectBook() {
		return sqlSession.selectList("book.selectBook");
	}

	@Override
	public Book selectOneBook(String bookid) {
		return sqlSession.selectOne("book.selectOneBook", bookid);
	}

	@Override
	public int updateBook(Book b) {
		// TODO Auto-generated method stub
		return sqlSession.update("book.updateBook", b);
	}

	@Override
	public int deleteBook(String bookid) {
		return sqlSession.delete("book.deleteBook",bookid);
	}

}
