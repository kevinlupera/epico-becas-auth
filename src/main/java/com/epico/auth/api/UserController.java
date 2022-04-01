package com.epico.auth.api;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epico.auth.entity.User;
import com.epico.auth.repository.UserRepository;

@RestController
public class UserController {
	
	private final UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@PostMapping("/api/users")
	@ResponseBody
	public User save(@RequestBody User user) {
		user.getEmail();
		user.getActivated();
		System.out.println("user: " + user.toString() );
		return this.userRepository.save(user);
	}
	
	@GetMapping("/api/users")
	@ResponseBody
	public List<User> findAll(){
		List<User> prueba = this.userRepository.findAll();
		System.out.println("prueba: " + prueba.toString() );
		return prueba;
	}
	
	@GetMapping("/api/users/{id}")
	public User findOneById(@PathVariable Long id) throws Exception {
		Optional<User> userOptional = this.userRepository.findById(id);
		System.out.println("user: " + userOptional );
		if(userOptional.isEmpty()) {
			throw new Exception("El usuario no existe.");
		}
		return userOptional.get();
	}
	
	@DeleteMapping("/api/users/{id}")
	public void delete(@PathVariable Long id) {
		this.userRepository.deleteById(id);
	}
	
	

}
