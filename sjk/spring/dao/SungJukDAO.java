package sjk.spring.dao;

import java.util.List;

import sjk.spring.vo.SungJuk;

public interface SungJukDAO {
	void insertSungJuk(SungJuk sj);
	List<SungJuk> selectAllSungJuck();
	SungJuk selectOneSungJuk(int sjno);
	void updateSungJuk(SungJuk sj);
	void deleteSungJuk(int i);
}
