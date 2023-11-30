package com.university.tn.university.Controlleur;

import com.university.tn.university.Model.Dto.MyResponse;
import com.university.tn.university.Model.Dto.UserDto;
import com.university.tn.university.Model.Entity.User;
import com.university.tn.university.PayloadResponse.JwtResponse;
import com.university.tn.university.Security.Service.JwtService;
import com.university.tn.university.Security.UserInfoUserDetails;
import com.university.tn.university.Service.UserService;
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

    public final static MyResponse FOUND = new MyResponse("FOUND");
    public final static MyResponse BAD_REQUEST = new MyResponse("BAD_REQUEST");
    public final static String NULL = "ID NULL DETECTED";
    public final static MyResponse NOT_FOUND = new MyResponse("NOT_FOUND");
    public final static MyResponse ACCES_DENIED=new MyResponse("acces denied");

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService usersService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtUtils;

    public UserController(UserService usersService) {
        super();
        this.usersService = usersService;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("User/Users")
    public List<User> retrieveAllUsers() {
        return usersService.retrieveAllUsers();
    }
    @PostMapping("/User/addUser/{idetudiant}")
    public ResponseEntity<Object> addUser(@RequestBody UserDto usersDTO,@PathVariable("idetudiant")Long idetudiant) throws UnsupportedEncodingException {
        User userReq = modelMapper.map(usersDTO, User.class);
        ResponseEntity<User> user = usersService.addUser(userReq,idetudiant);
        if (user.getStatusCodeValue() == 200) {
            UserDto userRes = modelMapper.map(user.getBody(), UserDto.class);
            return new ResponseEntity<>(userRes, HttpStatus.OK);
        } else if (user.getStatusCodeValue() == 400) {
            return new ResponseEntity<>(BAD_REQUEST, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(FOUND, HttpStatus.FOUND);
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/User/register")
    public ResponseEntity<Object> processRegister(@RequestBody UserDto usersDTO) throws UnsupportedEncodingException {
        User userReq = modelMapper.map(usersDTO, User.class);
        ResponseEntity<User> user = usersService.register(userReq);

        if (user.getStatusCodeValue() == 200) {
            UserDto userRes = modelMapper.map(user.getBody(), UserDto.class);
            return new ResponseEntity<>(userRes, HttpStatus.OK);
        } else if (user.getStatusCodeValue() == 400) {
            return new ResponseEntity<>(BAD_REQUEST, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(FOUND, HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/User/giveaccess/{iduniverste}")
    public ResponseEntity<Object> Giveaccesstoadmin(@RequestBody UserDto usersDTO,@PathVariable("iduniverste")Long iduniverste)  {
        User userReq = modelMapper.map(usersDTO, User.class);
        ResponseEntity<User> user = usersService.Giveaccesstoadminwithuniversity(userReq,iduniverste);

        if (user.getStatusCodeValue() == 200) {
            UserDto userRes = modelMapper.map(user.getBody(), UserDto.class);
            return new ResponseEntity<>(userRes, HttpStatus.OK);
        } else if (user.getStatusCodeValue() == 400) {
            return new ResponseEntity<>(BAD_REQUEST, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(FOUND, HttpStatus.FOUND);
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
            return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(NULL, HttpStatus.BAD_REQUEST);

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
            return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(NULL, HttpStatus.BAD_REQUEST);

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
            return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        else if (!passwordEncoder.matches(user.getPassword(), newUser.get().getPassword())) {
            return new ResponseEntity<>(BAD_REQUEST, HttpStatus.BAD_REQUEST);
        } else if (newUser.get().getAccess()==false) {
            return new ResponseEntity<>(ACCES_DENIED, HttpStatus.FORBIDDEN);
        }
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
        ), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/User/getUser/{email}")
    public Optional<User> retrieveUser(@PathVariable("email") String email) {
        return usersService.retrieveUser(email);
    }
}
