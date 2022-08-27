package assetto.afpenalty.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean validateUser(String userid, String password) {
        // admin, af4bikernieki
        return userid.equalsIgnoreCase("admin")
                && password.equalsIgnoreCase("af4bikernieki");
    }

}