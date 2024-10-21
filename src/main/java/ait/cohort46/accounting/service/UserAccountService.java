package ait.cohort46.accounting.service;


import ait.cohort46.accounting.dto.RolesDto;
import ait.cohort46.accounting.dto.UserDto;
import ait.cohort46.accounting.dto.UserEditDto;
import ait.cohort46.accounting.dto.UserRegisterDto;

public interface UserAccountService {
	UserDto register(UserRegisterDto userRegisterDto);

	UserDto getUser(String login);

	UserDto removeUser(String login);

	UserDto updateUser(String login, UserEditDto userEditDto);

	RolesDto changeRolesList(String login, String role, boolean isAddRoles);

	void changePassword(String login, String newPassword);
}
