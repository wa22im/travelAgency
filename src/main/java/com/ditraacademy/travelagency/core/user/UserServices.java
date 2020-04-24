package com.ditraacademy.travelagency.core.user;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.ditraacademy.travelagency.core.user.model.SignInModel;
import com.ditraacademy.travelagency.core.user.model.SingInResponse;
import com.ditraacademy.travelagency.utils.ErrorResponseModel;
import com.ditraacademy.travelagency.utils.JWTUtils;
import com.ditraacademy.travelagency.utils.MailSenderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class UserServices  implements UserDetailsService {
    @Autowired
    UserRepository userRepository ;

    @Autowired
    AuthenticationManager authenticationManager ;


    @Autowired
    JWTUtils jwtUtils;
    @Autowired
    MailSenderUtil mailSender ;

    @Value("${SERVER_HOST}")
    String hostname ;
    public ResponseEntity<?> creatUser( User user){
        if ( user.getName()==null) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel(" user name was not sent");
            return new ResponseEntity<>(errorResponseModel, HttpStatus.BAD_REQUEST);
        }
        if ( user.getName().length()<3) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel(" user name is not valid ");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }
        if ( user.getAge()==0) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel(" user age was not send ");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }
        if ( user.getAge()<0) {
            ErrorResponseModel errorResponseModel = new ErrorResponseModel(" user age is not valide ");
            return new ResponseEntity<>(errorResponseModel,HttpStatus.BAD_REQUEST);
        }
        String pwd = passwordEncoder().encode(user.getPassword());
        user.setPassword(pwd);
        String activationToken = passwordEncoder().encode(new Date().toString());
        user.setActivateToken(activationToken);

        String activateLink = hostname+"auth/activation?activationtoken="+activationToken;
        String mail  = user.getMail();
        String Subject = "Testing Spring mail" ;
        String Text = " It Actually Works Awesome :D  " + activateLink;

        mailSender.send(mail,Subject,Text);

        user = userRepository.save(user);
        return  new ResponseEntity<>(user,HttpStatus.OK);
    }
    public List<User> GetUsers (){
        return userRepository.findAll();
    }
    public Optional<User> getUserbyId( int id ){

        return userRepository.findById(id);
    }
    public ResponseEntity<?> DeleteUser (  int id ){
        Optional<User> userOp  =     userRepository.findById(id);
        if ( userOp.isPresent()){
            userRepository.deleteById(id);
            return new ResponseEntity<>(userOp.get(),HttpStatus.OK);
        }
        else  {
            return   new ResponseEntity<>(new ErrorResponseModel("the user that you want to delete " +
                    "does not exist "),HttpStatus.BAD_REQUEST) ;
        }

    }
    public ResponseEntity<?> updateUser( User user ,  int id ){
        System.out.println(user);
        Optional<User> userOptional = userRepository.findById(id);
        if ( userOptional.isPresent()){

            User updatedUser = userOptional.get();
            if ( user.getName()!=null) {
                if ( user.getName().length()<3)
                    return new   ResponseEntity<>( new ErrorResponseModel("invalid name"),HttpStatus.BAD_REQUEST);
                else
                    updatedUser.setName(user.getName());

            }
            if ( user.getAge()!=0) {
                if ( user.getAge()<0)
                    return new   ResponseEntity<>( new ErrorResponseModel("invalid age"),HttpStatus.BAD_REQUEST);
                else
                    updatedUser.setAge(user.getAge());

            }
            userRepository.save(updatedUser);
            return new ResponseEntity<>(updatedUser , HttpStatus.OK);

        }
        else {
            return   new ResponseEntity<>(new ErrorResponseModel("the user that you want to updated " +
                    "does not exist "),HttpStatus.BAD_REQUEST) ;
        }

    }

    public ResponseEntity<?> signIn(SignInModel signInModel){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInModel.getMail() , signInModel.getPassword())
        );
        String token = jwtUtils.generateToken(signInModel.getMail());


        return  new ResponseEntity<>(new SingInResponse(token),HttpStatus.OK);

    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByMail(mail);
        if (  !userOptional.isPresent()){
            return  null ;
        }
        User user =  userOptional.get();
        if ( !user.isActivated())
            return  null ;
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getRole());

        return  new org.springframework.security.core.userdetails.User(mail,user.getPassword(), authorities);
    }

     @Bean
    private PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    public String activateAccount(String activation) {

        Optional<User> userOptional =  userRepository.findByActivatedAndActivateToken(false ,activation);
        if ( !userOptional.isPresent()){
            return  "no user" ;
        }
        User user = userOptional.get();

        if ( user.isActivated())
            return  " ween machi wen ";

        user.setActivated(true);
        userRepository.save(user);


        return  "jawek fes fes ye booo";

    }
}
