package sjk.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sjk.spring.vo.SungJuk;

/*DAO class에서 쓰인다.
DataBase에 접근하는 method를 가지고 있는 Class에서 쓰인다.
괄호 안에는 호출하고자하는 Repository 패키지 내의 코드(DB에 접근하는 모든 코드)를 넣는다.*/
@Repository("sjdao02")
public class SungJukDAO02Impl implements SungJukDAO {
	
	/* 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.
	 * 타입: 생성자, setter, 필드
	 * Autowired는 기본값이 true이기 때문에 
	 * 의존성 주입을 할 대상을 찾지 못한다면 애플리케이션 구동에 실패한다.*/
	@Autowired
	private SqlSession sqlSession;
	// 위의 구문 안에 우리가 적어놓은 모든 코드들이 적혀있다.
	// 해당 코드들을 주입한다는 의미이다.

	
	@Override
	public void insertSungJuk(SungJuk sj) {
		int cnt = sqlSession.insert("sungjuk.insertSungJuk", sj);
		if (cnt>0) System.out.println("성적 입력 완료!");
	}

	
	@Override
	public List<SungJuk> selectAllSungJuk() {
		return sqlSession.selectList("sungjuk.selectSungJuk");
	}
	

	@Override
	public SungJuk selectOneSungJuk(int sjno) {
		return sqlSession.selectOne("sungjuk.selectOneSungJuk", sjno);
	}
	

	@Override
	public void updateSungJuk(SungJuk sj) {
		int cnt = sqlSession.update("sungjuk.updateSungJuk", sj);
		if (cnt>0) System.out.println("성적 수정 완료!");
	}
	

	@Override
	public void deleteSungJuk(int sjno) {
		int cnt = sqlSession.delete("sungjuk.deleteSungJuk", sjno);
		if (cnt>0) System.out.println("성적 삭제 완료!");
	}
}
