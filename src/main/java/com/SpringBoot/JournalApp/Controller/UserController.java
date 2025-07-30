package com.SpringBoot.JournalApp.Controller;

import com.SpringBoot.JournalApp.Service.UserService;
import com.SpringBoot.JournalApp.Service.QuotesService;
import com.SpringBoot.JournalApp.Service.WeatherService;
import com.SpringBoot.JournalApp.entry.User;
import com.SpringBoot.JournalApp.repositor.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuotesService quotesService;

    @Autowired
    private WeatherService weatherService; // Inject WeatherService instead of static call


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        try {
            String quote = quotesService.getMotivationalQuote();
            String weatherInfo = getWeatherInfo("Mumbai");
            String message = "Hi " + userName + "! Here's your daily motivation: " + quote;
            return new ResponseEntity<>(message + "\n" + weatherInfo, HttpStatus.OK);
        } catch (Exception e) {
            // Fallback if services fail
            String message = "Hi " + userName + "! (Quote and weather services temporarily unavailable)";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    private String getWeatherInfo(String city) {
        try {
            var weatherResponse = weatherService.getWeather(city);
            if (weatherResponse != null && weatherResponse.getCurrent() != null) {
                int feelsLike = weatherResponse.getCurrent().getFeelslike();
                return "Weather in " + city + " feels like: " + feelsLike + "°C";
            } else {
                return "Weather information unavailable for " + city;
            }
        } catch (Exception e) {
            return "Weather service temporarily unavailable";
        }
    }
}