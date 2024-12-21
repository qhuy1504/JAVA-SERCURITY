package iuh.fit.se.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import iuh.fit.se.entities.Employee;
import iuh.fit.se.repositories.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Employee employee = employeeRepository.findByName(username);
		
		System.out.println("Employee find by name: " + username);
		System.out.println("Employee find by name: " + employee);
		if (employee == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return User.builder().username(employee.getName()).password(employee.getPassword())
				.roles(employee.getRole().getName()).build();
	}

}
