package goodee.gdj58.online.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import goodee.gdj58.online.mapper.TeacherMapper;
import goodee.gdj58.online.vo.Example;
import goodee.gdj58.online.vo.Question;
import goodee.gdj58.online.vo.Teacher;
import goodee.gdj58.online.vo.Test;

@Service
@Transactional
public class TeacherService {
	// DI = new EmployeeMapper()
	@Autowired private TeacherMapper teacherMapper;
	
	
	// ==================== 문제(TestOne)====================\
	
	// Test 상세보기( 문제 등록- Question)
	public int addQuestion(Question question) {
		return teacherMapper.insertQuestion(question);
	}
	
	// Test 상세보기( 문제 등록 - Example)
	public int addExample(Example example) {
		return teacherMapper.insertExample(example);
	}
	
	// Test 상세보기( 마지막 문제번호)
	public int getLastQuestionNo() {
		return teacherMapper.selectLastQuestionNo();
	}
	
	// Test 상세보기 (시험 한개)
	public Test thisTest(int testNo) {
		return teacherMapper.thisTest(testNo);
	}
	
	// Test 상세보기 (답안지목록)
	public List<Map<String, Object>> getAnswerList(int testNo) {
		return teacherMapper.selectAnswerList(testNo);
	}
	
	// Test 상세보기 (문제목록)
	public List<Map<String, Object>> getExampleList(int testNo) {
		return teacherMapper.selectExampleList(testNo);
	}
	
	// ==================== 시험( Test)====================
	// Test 수정
	public int modifyTest(Test test) {
		return teacherMapper.updateTest(test);
	}
		
	
	// Test 삭제
	public int removeTest(int testNo) {
		return teacherMapper.deleteTest(testNo);
	}
	
	// Test 등록
	public int addTest(Test test) {
		return teacherMapper.insertTest(test);
	}
		
	// 검색 후 카운트
	public int getTestCount(String searchWord) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("searchWord", searchWord);
		return teacherMapper.selectTestCount(paramMap);
	}
	
	// 시험 리스트
	public List<Test> getTestList(int currentPage, int rowPerPage, String searchWord) {
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		return teacherMapper.selectTestList(paramMap);
	}
	
	
	// ==================== 강사 UI ====================
	// 강사 pw 수정
	public int updateTeacherPw(int teacherNo, String oldPw, String newPw) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teacherNo", teacherNo);
		paramMap.put("oldPw", oldPw);
		paramMap.put("newPw", newPw);
		System.out.println(paramMap.get("newPw"));
		return teacherMapper.updateTeacherPw(paramMap);
	}
	
	// 강사 로그인
	public Teacher login(Teacher teacher) {
		return teacherMapper.login(teacher);
	}
		
	// 검색후 카운트
	public int getTeacherCount(String searchWord) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("searchWord", searchWord);
		return teacherMapper.selectTeacherCount(paramMap);
	}
	
	// 강사 삭제
	public int removeTeacher(int teacherNo) {
		return teacherMapper.deleteTeacher(teacherNo);
	}
	
	// 강사 등록
	public int addTeacher(Teacher teacher) {
		return teacherMapper.insertTeacher(teacher);
	}
	
	// 강사 리스트 
	public List<Teacher> getTeacherList(int currentPage, int rowPerPage, String searchWord) {
		int beginRow = (currentPage-1)*rowPerPage;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginRow", beginRow);
		paramMap.put("rowPerPage", rowPerPage);
		paramMap.put("searchWord", searchWord);
		return teacherMapper.selectTeacherList(paramMap);
	}
}
