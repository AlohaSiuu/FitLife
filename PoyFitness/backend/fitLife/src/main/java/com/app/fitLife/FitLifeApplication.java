package com.app.fitLife;

import com.app.fitLife.model.Permissions;
import com.app.fitLife.model.RoleEntity;
import com.app.fitLife.model.UserEntity;
import com.app.fitLife.model.UserTracking;
import com.app.fitLife.repository.UserRepository;
import com.app.fitLife.utils.PermissionsEnum;
import com.app.fitLife.utils.RoleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class FitLifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitLifeApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository){
		return args ->  {
			Permissions createPermissions = Permissions.builder()
					.permissionsEnum(PermissionsEnum.CREATE)
					.build();

			Permissions readPermissions = Permissions.builder()
					.permissionsEnum(PermissionsEnum.READ)
					.build();

			Permissions updatePermissions = Permissions.builder()
					.permissionsEnum(PermissionsEnum.UPDATE)
					.build();

			Permissions deletePermissions = Permissions.builder()
					.permissionsEnum(PermissionsEnum.DELETE)
					.build();

			RoleEntity roleAdmin = RoleEntity.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionsSet(Set.of(createPermissions, readPermissions, updatePermissions, deletePermissions))
					.build();

			RoleEntity roleTrainer = RoleEntity.builder()
					.roleEnum(RoleEnum.TRAINER)
					.permissionsSet(Set.of(createPermissions, readPermissions))
					.build();
			RoleEntity roleUser = RoleEntity.builder()
					.roleEnum(RoleEnum.USER)
					.permissionsSet(Set.of(readPermissions))
					.build();

//			UserTracking userTracking = UserTracking.builder()
//					.date("2233232")
//					.weight(12.4)
//					.bim(33.2)
//					.calories_burned(23.2)
//					.userEntity(null)
//					.build();

			UserEntity userEntityAdmin = UserEntity.builder()
					.username("Admin")
					.email("admin@gmail.com")
					.password("admin1234")
					.age(22)
					.weight(70.0)
					.height(1.70)
					.trakings(null)
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserEntity userUserEntity = UserEntity.builder()
					.username("User")
					.email("user@gmail.com")
					.password("user1234")
					.age(22)
					.weight(70.0)
					.height(1.70)
					.trakings(null)
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();



			UserEntity userEntityTrainer = UserEntity.builder()
					.username("Trainer")
					.email("trainer@gmail.com")
					.password("trainer1234")
					.age(22)
					.weight(70.0)
					.height(1.70)
					.trakings(null)
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleTrainer))
					.build();

			userRepository.saveAll(List.of(userEntityAdmin, userUserEntity, userEntityTrainer));

		};
	}

}



