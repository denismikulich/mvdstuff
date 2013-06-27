package com.stuff.stuffapp.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:root-context.xml" })
public class DomainTests {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	public void setUp() {
		Assert.assertNotNull("session factory must be initialized", sessionFactory);
	}
	
	@Test
	public void stuffTest() {
		Role role1 = new Role();
		role1.setName("role1");
		role1 = saveRole(role1);
		Assert.assertNotNull(role1.getId());
		System.out.println(role1.toString());
		
		Role role2 = new Role();
		role2.setName("role2");
		role2 = saveRole(role2);
		Assert.assertNotNull(role2.getId());
		
		System.out.println(role2.toString());
		
		Role loadedRole1 = getRole(role1.getId());
		Assert.assertEquals(loadedRole1.getId(), role1.getId());
	}
	
	@Transactional
	private Role saveRole(Role role) {
		Session session = sessionFactory.openSession();
		session.save(role);
		session.close();
		return role;
	}
	
	@Transactional
	private Role getRole(Long id) {
		Session session = sessionFactory.openSession();
		Role role = (Role)session.load(Role.class, id);
		session.close();
		return role;
	}
}
