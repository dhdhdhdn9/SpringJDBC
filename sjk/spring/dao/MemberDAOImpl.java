package sjk.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import sjk.spring.vo.Member;

/*DAO class에서 쓰인다.
DataBase에 접근하는 method를 가지고 있는 Class에서 쓰인다.
괄호 안에는 호출하고자하는 Repository 패키지 내의 코드(DB에 접근하는 모든 코드)를 넣는다.*/
@Repository("mbdao")
public class MemberDAOImpl implements MemberDAO {
	
	/* 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.
	 * 타입: 생성자, setter, 필드
	 * Autowired는 기본값이 true이기 때문에 
	 * 의존성 주입을 할 대상을 찾지 못한다면 애플리케이션 구동에 실패한다.*/
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insertMember(Member mb) {
		String sql = " insert into member (userid, passwd, name, email) "
				+ " values (?,?,?,?)";
		Object[] params = new Object[] {
				mb.getUserid(), mb.getPasswd(), mb.getName(), mb.getEmail()
		};
		int cnt = jdbcTemplate.update(sql, params);
		if (cnt>0) System.out.println("회원 등록 완료!");
		
	}

	@Override
	public List<Member> selectAllMember() {
		String sql = " select * from member "
				+ " order by mno asc ";
		RowMapper<Member> mapper = new MemberMapper();
		return jdbcTemplate.query(sql, mapper);
	}
	
	protected class MemberMapper implements RowMapper<Member> {
		@Override
		public Member mapRow(ResultSet rs, int num) throws SQLException {
			String mno = rs.getString("mno");
			String userid = rs.getString("userid");
			String name = rs.getString("name");
			String joindate = rs.getString("joindate").substring(0,10);
			Member mb = new Member(mno, userid, "", name, "", joindate);
			return mb;
		}	
	}
	

	@Override
	public Member selectOneMember(int mno) {
		String sql = " select * from member where mno = ? ";
		Object[] params = new Object[] {mno};
		
		RowMapper<Member> mapper = new MemberOneMapper();
		return jdbcTemplate.queryForObject(sql, params, mapper);
	}
	
	protected class MemberOneMapper implements RowMapper<Member> {
		@Override
		public Member mapRow(ResultSet rs, int num) throws SQLException {
			String mno = rs.getString("mno");
			String userid = rs.getString("userid");
			String name = rs.getString("name");
			String email = rs.getString("email");
			String joindate = rs.getString("joindate");
			Member mb = new Member(mno, userid, "", name, email, joindate);
			return mb;
		}
		
	}


	@Override
	public void updateMember(Member mb) {
		String sql = " update member set passwd=?, name=?, email=? where mno=? ";
		Object[] params = new Object[] {
				mb.getPasswd(), mb.getName(), mb.getEmail(), mb.getMno()
		};
			int cnt = jdbcTemplate.update(sql, params);
			if (cnt>0) System.out.println("회원 수정 완료!");
		
	}

	@Override
	public void deleteMember(int mno) {
		String sql = "delete from member where mno = ? ";
		Object[] params = new Object[] { mno };
		int cnt = jdbcTemplate.update(sql, params);
		if (cnt>0) System.out.println("회원 삭제 완료!");
		
		
	}
	
	
	
	
	
	
	
	
	
}
