package goodee.gdj58.online.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import goodee.gdj58.online.service.IdService;

@RestController
public class IdCheckRestController {
	@Autowired IdService idService;
	
	@GetMapping("/employee/idCheck")
	public String idCheck(@RequestParam(value="id") String id) {
		String resultStr = "NO";
		if(idService.getIdCheck(id) == null) {
			resultStr = "YES";
		}
		return resultStr;
	}

}