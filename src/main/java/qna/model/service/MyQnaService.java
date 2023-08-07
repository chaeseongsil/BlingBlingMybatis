package qna.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.SqlSessionTemplate;
import qna.model.dao.MyQnaDAO;
import qna.model.vo.PageData;
import qna.model.vo.Qna;

public class MyQnaService {
	private MyQnaDAO qDao;
	
	public MyQnaService() {
		qDao = new MyQnaDAO();
	}

	public int insertQna(Qna qna) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = qDao.insertQna(session, qna);
		if(result > 0) {
			session.commit();
		}else {
			session.rollback();
		}
		session.close();
		return result;
	}

	public int updateQna(Qna qna) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = qDao.updateQna(session, qna);
		if(result > 0) {
			session.commit();
		}else {
			session.rollback();
		}
		session.close();
		return result;
	}

	public int deleteQna(int myQnaNo) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = qDao.deleteQna(session, myQnaNo);
		if(result > 0) {
			session.commit();
		}else {
			session.rollback();
		}
		session.close();
		return result;
	}

	public PageData selectAllQnaList(int currentPage) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		List<Qna> qList = qDao.selectAllQnaList(session, currentPage);
		String pageNavi = qDao.generateAllPageNavi(session, currentPage);
		PageData pd = new PageData(qList, pageNavi);
		session.close();
		return pd;
	}

	public PageData selectMyQnaList(int currentPage, String myQnaWriter) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		List<Qna> qList = qDao.selectMyQnaList(session, currentPage, myQnaWriter);
		String pageNavi = qDao.generateMyPageNavi(session, currentPage, myQnaWriter);
		PageData pd = new PageData(qList, pageNavi);
		session.close();
		return pd;
	}

	public Qna selectOneByNo(int myQnaNo) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		Qna qna = qDao.selectOneByNo(session, myQnaNo);
		session.close();
		return qna;
	}

}
