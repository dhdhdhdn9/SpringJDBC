package sjk.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sjk.spring.vo.SungJuk;

/*DAO class에서 쓰인다.
DataBase에 접근하는 method를 가지고 있는 Class에서 쓰인다.
괄호 안에는 호출하고자하는 Repository 패키지 내의 코드(DB에 접근하는 모든 코드)를 넣는다.*/
@Repository("sjdao")
public class SungJukDAOImpl implements SungJukDAO {
	
	/* 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.
	 * 타입: 생성자, setter, 필드
	 * Autowired는 기본값이 true이기 때문에 
	 * 의존성 주입을 할 대상을 찾지 못한다면 애플리케이션 구동에 실패한다.*/
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void insertSungJuk(SungJuk sj) {
		String sql = "insert into sungjuk (name, kor, eng, mat, tot, mean, grd)"
				+ " values (?,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			// 물음표에 들어갈 매개변수
			sj.getName(), sj.getKor(), sj.getEng(), sj.getMat(), sj.getTot(), sj.getAvg(), sj.getGrd()+""
		};
		
		int cnt = jdbcTemplate.update(sql,params);
		if (cnt>0) System.out.println("성적 추가 완료!");
		
	}

}
