package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Teacher;
import goodee.gdj58.online.vo.Test;

@Mapper
public interface TeacherMapper {
	
	
	// ==================== 문제(TestOne)====================
	// Test 상세보기 (문제목록)
	int selectLastQuestionNo(); 
	Test thisTest(int testNo);
	List<Map<String, Object>> selectAnswerList(int testNo);
	List<Map<String,Object>> selectExampleList(int testNo);
	
	// ==================== 시험( Test)====================
	int updateTest(Test test);
	int deleteTest(int testNo);
	int insertTest(Test test);
	int selectTestCount(Map<String, Object> paramMap);
	List<Test> selectTestList(Map<String, Object> paramMap);
	
	
	// ==================== 강사 UI ====================
	int updateTeacherPw(Map<String,Object> paramMap);
	Teacher login(Teacher teacher);
	int selectTeacherCount(Map<String, Object> paramMap);
	int deleteTeacher(int teacherNo);
	int insertTeacher(Teacher teacher);
	List<Teacher> selectTeacherList(Map<String, Object> paramMap);
}
