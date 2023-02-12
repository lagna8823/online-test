package goodee.gdj58.online.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import goodee.gdj58.online.vo.Teacher;

@Mapper
public interface TeacherMapper {
	
	
	// ==================== 강사 UI ====================
	int updateTeacherPw(Map<String,Object> paramMap);
	Teacher login(Teacher teacher);
	int selectTeacherCount(Map<String, Object> paramMap);
	int deleteTeacher(int teacherNo);
	int insertTeacher(Teacher teacher);
	List<Teacher> selectTeacherList(Map<String, Object> paramMap);

}
