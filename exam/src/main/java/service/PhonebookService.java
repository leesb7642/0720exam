package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.PhonbookDAO;
import inter.PhonebookInter;
import vo.PhonebookVO;

@Service
public class PhonebookService implements PhonebookInter {
	@Autowired
	PhonbookDAO dao;
	
	@Override
	public int insert(PhonebookVO phonebook) {
		
		return dao.insert(phonebook);
	}

	@Override
	public List<PhonebookVO> findAll() {
		
		return dao.findAll();
	}

	@Override
	public List<PhonebookVO> search(String keyword) {
		
		return dao.search(keyword);
	}

	@Override
	public PhonebookVO findOne(int idx) {
		return dao.findOne(idx);
	}

	@Override
	public int update(int idx, PhonebookVO phonebook) {
		
		return dao.update(idx, phonebook);
	}

	@Override
	public int delete(int idx) {
		
		return dao.delete(idx);
	}

}
