package sjk.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

	@Override
	public List<SungJuk> selectAllSungJuck() {
		String sql = "select name, kor, eng, mat from sungjuk"
				+ " order by sjno desc";
		/* 위의 sql문을 구현해주는 것이 아래 RowMapper */
		RowMapper<SungJuk> mapper = new SungJukMapper();
		
		return jdbcTemplate.query(sql, mapper);
	}
	
	/* RowMapper를 사용하면, 원하는 형태의 결과값을 반환할 수 있다. 
	 * ResultSet에 값을 담아와서 User 객체에 저장한다.
	 * 그리고 그것을 count만큼 반복한다.
	 * 원문
	 * mapRow(ResultSet rs, int count) 
	 * return user; 
	 * 
	 * 성적 데이터를 출력하기 위한 RowMapeer 클래스*/
	protected class SungJukMapper implements RowMapper<SungJuk> {
		@Override
		public SungJuk mapRow(ResultSet rs, int num) throws SQLException {
			/* System.out.println(num); */
			String name = rs.getString("name");
			int kor = rs.getInt("kor");
			int eng = rs.getInt("eng");
			int mat = rs.getInt("mat");
			SungJuk sj = new SungJuk(name, kor, eng, mat);
			return sj;
		}
	}

	@Override
	public SungJuk selectOneSungJuk(int sjno) {
		String sql = "select * from sungjuk where sjno = ?";
		Object[] params = new Object[] {sjno};
		
		RowMapper<SungJuk> mapper = new SungJukOneMapper();
		/* 콜백 클래스만 등록하고, 호출/실행은 따로 하지 않음.
		 * 단, query 메서드가 실행하는 도중 결과집합이 존재하면(=re.next가 참이라면)
		 * IoC 컨테이너가 mapper 객체의 mapRow를 호출한다.*/
		
		return jdbcTemplate.queryForObject(sql, params, mapper);
	}
	
	
	protected class SungJukOneMapper implements
	RowMapper<SungJuk> {
		@Override
		public SungJuk mapRow(ResultSet rs, int num) throws SQLException {
		
			String name = rs.getString("name");
			int kor = rs.getInt("kor");
			int eng = rs.getInt("eng");
			int mat = rs.getInt("mat");
			
			SungJuk sj = new SungJuk(name, kor, eng, mat);
			sj.setTot(rs.getInt("tot"));
			sj.setAvg(rs.getInt("mean"));
			sj.setGrd(rs.getString("grd").charAt(0));
			return sj;
		}
		
	}

	@Override
	public void updateSungJuk(SungJuk sj) {
		String sql = " update sungjuk set kor = ?, eng = ?, mat = ?, "
				+ " tot=?, mean=?, grd=? where sjno = ? ";
		Object[] params = new Object[] {
				sj.getKor(), sj.getEng(), sj.getMat(),
				sj.getTot(), sj.getAvg(), sj.getGrd()+"", sj.getSjno() 				
		};
			int cnt = jdbcTemplate.update(sql, params);
			if (cnt>0) System.out.println("수정완료!");
	}

	@Override
	public void deleteSungJuk(int sjno) {
		String sql = "delete from sungjuk where sjno = ?";
		Object[] params = new Object[] { sjno };
		int cnt = jdbcTemplate.update(sql, params);
		if (cnt>0) System.out.println("데이터 삭제 완료!");
	}
}
