package com.cloud.user.service;

import java.util.Map;
import java.util.Set;

import com.cloud.user.model.AppUser;
import com.cloud.user.model.LoginAppUser;
import com.cloud.user.model.SysRole;
import com.cloud.util.Page;


public interface AppUserService {

	void addAppUser(AppUser appUser);

	void updateAppUser(AppUser appUser);

	LoginAppUser findByUsername(String username);

	AppUser findById(Long id);

	void setRoleToUser(Long id, Set<Long> roleIds);

	void updatePassword(Long id, String oldPassword, String newPassword);

	Page<AppUser> findUsers(Map<String, Object> params);

	Set<SysRole> findRolesByUserId(Long userId);

	void bindingPhone(Long userId, String phone);
}
