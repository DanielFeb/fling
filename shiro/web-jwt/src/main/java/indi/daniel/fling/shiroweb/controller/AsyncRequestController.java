package indi.daniel.fling.shiroweb.controller;

import indi.daniel.fling.shiroweb.dto.UserDto;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
public class AsyncRequestController {

	@GetMapping("/async")
	public Callable<UserDto> doAsync(){
		return ()->{
			Thread.sleep(5000);
			return (UserDto)SecurityUtils.getSubject().getPrincipal();
		};
	}
}
