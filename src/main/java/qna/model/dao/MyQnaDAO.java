package qna.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import qna.model.vo.Qna;

public class MyQnaDAO {
	public int insertQna(SqlSession session, Qna qna) {
		int result = session.insert("QnaMapper.insertQna", qna);
		return result;
	}
	public int updateQna(SqlSession session, Qna qna) {
		int result = session.update("QnaMapper.updateQna", qna);
		return result;
	}
	public int deleteQna(SqlSession session, int myQnaNo) {
		int result = session.delete("QnaMapper.deleteQna", myQnaNo);
		return result;
	}
	/**
	 * 전체 Qna 글 수 조회
	 * @param session
	 * @return result count(*)
	 */
	public int selectCountAllQna(SqlSession session) {
		int result = session.selectOne("QnaMapper.selectCountAllQna");
		return result;
	}
	/**
	 *  계정별 Qna 작성글 수 조회
	 * @param session
	 * @param myQnaWriter
	 * @return
	 */
	public int selectCountMyQna(SqlSession session, String myQnaWriter) {
		int result = session.selectOne("QnaMapper.selectCountMyQna", myQnaWriter);
		return result;
	}
	
	public List<Qna> selectAllQnaList(SqlSession session, int currentPage) {
		int limit = 5;
		int offset = (currentPage-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Qna> qList = session.selectList("QnaMapper.selectAllQnaList", null, rowBounds);
		return qList;
	}

	public List<Qna> selectMyQnaList(SqlSession session, int currentPage, String myQnaWriter) {
		int limit = 5;
		int offset = (currentPage-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Qna> qList = session.selectList("QnaMapper.selectMyQnaList", myQnaWriter, rowBounds);
		return qList;
	}

	public Qna selectOneByNo(SqlSession session, int myQnaNo) {
		Qna qna = session.selectOne("QnaMapper.selectOneByNo", myQnaNo);
		return qna;
	}
	public String generateAllPageNavi(SqlSession session, int currentPage) {
		int totalCount = selectCountAllQna(session);
		int recordCountPerPage = 5;
		int naviTotalCount = 0;
		if(totalCount % recordCountPerPage > 0) {
			naviTotalCount = totalCount / recordCountPerPage + 1;
		}else {
			naviTotalCount = totalCount / recordCountPerPage;
		}
		int naviCountPerPage = 5;
		int startNavi = ((currentPage-1)/naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		boolean needPrev = (startNavi == 1) ? false : true;
		boolean needNext = (endNavi == naviTotalCount) ? false : true;
		StringBuilder result = new StringBuilder();
		if(needPrev) {
			result.append("<a href='/qna/list.do?currentPage="+(startNavi-1)+"'>"
					+ "<button class=\"prev-btn\">\r\n"
					+ "                                <img src=\"https://cdn-icons-png.flaticon.com/512/271/271220.png\" alt=\"\">\r\n"
					+ "                            </button></a>");
		}
		for(int i = startNavi; i <= endNavi; i++) {
			result.append("<a href='/qna/list.do?currentPage="+i+"'>"+i+"&nbsp;&nbsp;</a>");
		}
		if(needNext) {
			result.append("<a href='/qna/list.do?currentPage="+(endNavi+1)+"'><button class=\"next-btn\">\r\n"
					+ "                                <img src=\"https://cdn-icons-png.flaticon.com/512/87/87425.png\" alt=\"\">\r\n"
					+ "                            </button></a>");
		}
		return result.toString();
	}


	public String generateMyPageNavi(SqlSession session, int currentPage, String myQnaWriter) {
		int totalCount = selectCountMyQna(session, myQnaWriter);
		int recordCountPerPage = 5;
		int naviTotalCount = 0;
		if(totalCount % recordCountPerPage > 0) {
			naviTotalCount = totalCount / recordCountPerPage + 1;
		}else {
			naviTotalCount = totalCount / recordCountPerPage;
		}
		int naviCountPerPage = 5;
		int startNavi = ((currentPage-1)/naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		boolean needPrev = (startNavi == 1) ? false : true;
		boolean needNext = (endNavi == naviTotalCount) ? false : true;
		StringBuilder result = new StringBuilder();
		if(needPrev) {
			result.append("<a href='/myQna/list.do?memberId="+myQnaWriter+"&currentPage="+(startNavi-1)+"'>"
					+ "<button class=\"prev-btn\">\r\n"
					+ "                                <img src=\"https://cdn-icons-png.flaticon.com/512/271/271220.png\" alt=\"\">\r\n"
					+ "                            </button></a>");
		}
		for(int i = startNavi; i <= endNavi; i++) {
			result.append("<a href='/myQna/list.do?memberId="+myQnaWriter+"&currentPage="+i+"'>"+i+"&nbsp;&nbsp;</a>");
		}
		if(needNext) {
			result.append("<a href='/myQna/list.do?memberId="+myQnaWriter+"&currentPage="+(endNavi+1)+"'><button class=\"next-btn\">\r\n"
					+ "                                <img src=\"https://cdn-icons-png.flaticon.com/512/87/87425.png\" alt=\"\">\r\n"
					+ "                            </button></a>");
		}
		return result.toString();
	}

}
