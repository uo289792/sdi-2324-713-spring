package com.uniovi.sdi2324713spring.services;
import java.util.*;
import javax.annotation.PostConstruct;

import com.uniovi.sdi2324713spring.repositories.UsersRepository;
import org.springframework.stereotype.Service;
import com.uniovi.sdi2324713spring.entities.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service
public class UsersService {
 private final UsersRepository usersRepository;
 private final BCryptPasswordEncoder bCryptPasswordEncoder;
 public UsersService(UsersRepository usersRepository, BCryptPasswordEncoder
         bCryptPasswordEncoder) {
  this.usersRepository = usersRepository;
  this.bCryptPasswordEncoder = bCryptPasswordEncoder;
 }
 @PostConstruct
 public void init() {
 }
 public List<User> getUsers() {
  List<User> users = new ArrayList<User>();
  usersRepository.findAll().forEach(users::add);
  return users;
 }
 public User getUser(Long id) {
  return usersRepository.findById(id).get();
 }
 public void addUser(User user) {
  //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
  usersRepository.save(user);
 }

 public void editUser (Long id,User user) {
   User originalService = getUser(id);
   originalService.setDni(user.getDni());
   originalService.setName(user.getName());
   originalService.setLastName(user.getLastName());
   usersRepository.save(originalService);
 }

 public User getUserByDni(String dni) {
  return usersRepository.findByDni(dni);
 }
 public void deleteUser(Long id) {
  usersRepository.deleteById(id);
 }
}
