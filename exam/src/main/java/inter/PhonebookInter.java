package inter;

import java.util.List;

import vo.PhonebookVO;

public interface PhonebookInter {
public int insert(PhonebookVO phonebook);
public List<PhonebookVO> findAll();
public List<PhonebookVO> search(String keyword);
public PhonebookVO findOne(int idx);
public int update(int idx,PhonebookVO phonebook);
public int delete(int idx);
}
