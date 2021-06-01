package sjk.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sjk.spring.dao.MemberDAO;
import sjk.spring.vo.Member;

/*Service Class에서 쓰인다.
비즈니스 로직을 수행하는 Class라는 것을 나타내는 용도이다.
괄호 안에는 호출하고자하는 Service 패키지 내의 코드(비즈니스 로직과 관련된 모든 코드)를 넣는다*/
@Service("mbsrv")
public class MemberServiceImpl implements MemberService {

	/* 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다.
	 * 타입: 생성자, setter, 필드
	 * Autowired는 기본값이 true이기 때문에 
	 * 의존성 주입을 할 대상을 찾지 못한다면 애플리케이션 구동에 실패한다.*/
	@Autowired
	private MemberDAO mbdao;
	
	@Override
	public void newMember() {
		Member mb = new Member("abc", "abc", "가나다", "abc@mail.com");
		System.out.println("회원 데이터 생성 완료!");
		mbdao.insertMember(mb);				
	}

	@Override
	public void readAllMember() {
		StringBuffer sb = new StringBuffer();
		List<Member> mbs = mbdao.selectAllMember();
		
		for(Member mb: mbs) sb.append(mb);
		System.out.println(sb);
		
	}

	@Override
	public void readOneMember(int mno) {
		Member mb = mbdao.selectOneMember(mno);
		System.out.println(mb);
		
	}

	@Override
	public void modifyMember() {
		Member mb = new Member(null, "12345", "오잉", "no@mail.com");
		mb.setMno("2");
		mbdao.updateMember(mb);
		
		
	}

	@Override
	public void removeMember() {
		mbdao.deleteMember(4);
		
	}


}
