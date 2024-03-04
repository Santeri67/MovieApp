package com.friendlist.tehtava;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.friendlist.tehtava.domain.Friend;

@Controller
public class FriendController {

    private List<Friend> friends = new ArrayList<>();

    // Konstruktori, joka alustaa listan esimerkkiystävillä
    public FriendController() {
        friends.add(new Friend("Jukka", "Juslin"));
    }

    @GetMapping("/list-friends") // Muutettu polku
    public String listFriends(Model model) {
        model.addAttribute("friends", friends); // Käytetään luokan jäsenmuuttujaa
        model.addAttribute("friend", new Friend()); // Lisätään tyhjä Friend-olio lomakkeelle
        return "friends"; // Yhdistetty listaus ja lomake
    }

    @PostMapping("/list-friends")
    public String addFriend(@ModelAttribute Friend friend) {
        friends.add(friend);
        return "redirect:/list-friends"; // Päivitetty lista näkyy uudelleenohjauksen jälkeen
    }
}