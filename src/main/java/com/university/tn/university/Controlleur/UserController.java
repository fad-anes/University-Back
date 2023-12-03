package com.university.tn.university.Controlleur;

import com.university.tn.university.Model.Dto.MyResponse;
import com.university.tn.university.Model.Dto.UserDto;
import com.university.tn.university.Model.Entity.Notification;
import com.university.tn.university.Model.Entity.User;
import com.university.tn.university.PayloadResponse.JwtResponse;
import com.university.tn.university.Security.Service.JwtService;
import com.university.tn.university.Security.UserInfoUserDetails;
import com.university.tn.university.Service.UserService;
import com.university.tn.university.Service.ServiceNotification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;


@RestController
public class UserController {

    public final static MyResponse FOUND = new MyResponse("OBJECT FOUND");
    public final static MyResponse BAD_REQUEST = new MyResponse("BAD_REQUEST");
    public final static MyResponse NULL =  new MyResponse("OBJECT NULL DETECTED");
    public final static MyResponse NOT_FOUND = new MyResponse("OBJECT NOT_FOUND");
    public final static MyResponse ACCES_DENIED=new MyResponse("YOU DONT HAVE ACEESS");

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService usersService;
    @Autowired
    private ServiceNotification ServiceNotification;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtUtils;

    public UserController(UserService usersService,ServiceNotification ServiceNotification) {
        super();
        this.usersService = usersService;
        this.ServiceNotification = ServiceNotification;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/User/Users")
    public List<User> retrieveAllUsers() {
        return usersService.retrieveAllUsers();
    }
    @PostMapping("/User/addUser/{idetudiant}/{iduniverste}")
    public ResponseEntity<Object> addUser(@RequestBody UserDto usersDTO,@PathVariable("idetudiant")Long idetudiant,@PathVariable("iduniverste")Long iduniverste) throws UnsupportedEncodingException {
        User userReq = modelMapper.map(usersDTO, User.class);
        ResponseEntity<User> user = usersService.addUser(userReq,idetudiant,iduniverste);
        if (user.getStatusCodeValue() == 200) {
            UserDto userRes = modelMapper.map(user.getBody(), UserDto.class);
            return new ResponseEntity<>(userRes, HttpStatus.OK);
        } else if (user.getStatusCodeValue() == 400) {
            return new ResponseEntity<>(BAD_REQUEST.getMessage(), HttpStatus.BAD_REQUEST);
        }else if (user.getStatusCodeValue() == 404) {
            return new ResponseEntity<>(NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(FOUND.getMessage(), HttpStatus.FOUND);
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/names")
    public List<String> names(){
        return usersService.getnames();
    }
    @GetMapping("/notificationcount")
    public long notificationcount(){
        return ServiceNotification.nombredenotification();
    }
    @GetMapping("/notification")
    public List<Notification> notification(){
        return ServiceNotification.gettallunseen();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/User/register/{nom}")
    public ResponseEntity<Object> processRegister(@RequestBody UserDto usersDTO,@PathVariable("nom")String nom) throws UnsupportedEncodingException {
        User userReq = modelMapper.map(usersDTO, User.class);
        ResponseEntity<User> user = usersService.register(userReq,nom);

        if (user.getStatusCodeValue() == 200) {
            UserDto userRes = modelMapper.map(user.getBody(), UserDto.class);
            return new ResponseEntity<>(userRes, HttpStatus.OK);
        } else if (user.getStatusCodeValue() == 400) {
            return new ResponseEntity<>(BAD_REQUEST.getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(FOUND.getMessage(), HttpStatus.FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/User/giveaccess/{iduniverste}/{iduser}")
    public ResponseEntity<Object> Giveaccesstoadmin(@PathVariable("iduser") Integer iduser,@PathVariable("iduniverste")Long iduniverste)  {
        ResponseEntity<User> user = usersService.Giveaccesstoadminwithuniversity(iduser,iduniverste);

        if (user.getStatusCodeValue() == 200) {
            UserDto userRes = modelMapper.map(user.getBody(), UserDto.class);
            return new ResponseEntity<>(userRes, HttpStatus.OK);
        } else if (user.getStatusCodeValue() == 400) {
            return new ResponseEntity<>(BAD_REQUEST.getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/User/changeStatus/{email}")
    public ResponseEntity<Object> changeStatus(@PathVariable("email") String email) {
        ResponseEntity<User> user = usersService.changeStatus(email);
        if (user.getStatusCodeValue() == 200) {
            UserDto usersDTO = modelMapper.map(user.getBody(), UserDto.class);
            return new ResponseEntity<>(usersDTO, HttpStatus.OK);
        } else if (user.getStatusCodeValue() == 404) {
            return new ResponseEntity<>(NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(NULL.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/User/userUpdate")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        System.out.println(user);
        ResponseEntity<User> userr=usersService.updateUser(user);
        if (userr.getStatusCodeValue() == 200) {
            UserDto UserDto = modelMapper.map(userr.getBody(), UserDto.class);
            return new ResponseEntity<>(UserDto, HttpStatus.OK);
        } else if (userr.getStatusCodeValue() == 404) {
            return new ResponseEntity<>(NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(NULL.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/User/deleteUser/{idUser}")
    public String removeUser(@PathVariable("idUser") Integer idUser) {
        usersService.removeUser(idUser);
        return "Deleted Successfully";
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/User/signin")
    public ResponseEntity<Object> authenticateUser(@RequestBody UserDto usersDTO) {
        User user = modelMapper.map(usersDTO, User.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Optional<User> newUser=usersService.retrieveUser(user.getEmail());
        if (!newUser.isPresent()) {
            return new ResponseEntity<>(NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        }
        else if (!passwordEncoder.matches(user.getPassword(), newUser.get().getPassword())) {
            return new ResponseEntity<>(BAD_REQUEST.getMessage(), HttpStatus.BAD_REQUEST);
        } else if (newUser.get().getAccess()==false) {
            return new ResponseEntity<>(ACCES_DENIED.getMessage(), HttpStatus.FORBIDDEN);
        }
        else{
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserInfoUserDetails userDetails = (UserInfoUserDetails) authentication.getPrincipal();
        return new ResponseEntity<>(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getRole(),
                userDetails.getAccess()
        ), HttpStatus.OK);}
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/User/getUser/{email}")
    public Optional<User> retrieveUser(@PathVariable("email") String email) {
        return usersService.retrieveUser(email);
    }
}
