package com.suleiman.TransactionEntitiyWithJWT.Auth;

import com.google.common.collect.Lists;
import com.suleiman.TransactionEntitiyWithJWT.Utils.ApplicationUserRoles;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository("inMemory")
public class InMemoryApplicationUserDaoService implements  ApplicationUserDao{
  private final  PasswordEncoder passwordEncoder;

    public InMemoryApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream().filter((applicationUser) -> {
           return username.equalsIgnoreCase(applicationUser.getUsername());
        }).findFirst();
    }


   public  List<ApplicationUser> getApplicationUsers() {
       ArrayList<ApplicationUser> applicationUsers = Lists.newArrayList
               (
               new ApplicationUser("suleiman", passwordEncoder.encode("sas"), ApplicationUserRoles.DEVELOPER.getGrantedAuthority(), true, true, true, true),

               new ApplicationUser("abdulmalik", passwordEncoder.encode("malik"), ApplicationUserRoles.SENIOR_DEVELOPER.getGrantedAuthority(), true, true, true, true),

               new ApplicationUser("john", passwordEncoder.encode("john"), ApplicationUserRoles.CUSTOMER.getGrantedAuthority(), true, true, true, true)
       );
       return applicationUsers;
   }
}
