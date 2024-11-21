package kr.co.dong;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.dong.catdog.CatDogService;
import kr.co.dong.catdog.ProductGroupDTO;

/**
 * Handles requests for the application home page.
 */

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Inject
	CatDogService catDogService;  

	@RequestMapping(value = "/", method = RequestMethod.GET) // 프로젝트 처음 시작할 때 "/" 하나 있는 애 부터 실행이 된다.
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		List<ProductGroupDTO> list = catDogService.list();
		mav.addObject("list", list);
		mav.setViewName("home");
		return mav;
	}
	
	@RequestMapping(value="/main")
	public String testmain(Model model) {
		String name = "Hello Song World~~~";
		model.addAttribute("name", name);//모델에 저장
		return "main";
	}
	
}