package com.example.globalnotes.controllers;

import com.example.globalnotes.dao.NoteDao;
import com.example.globalnotes.model.Note;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Collections;

@Controller
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    @NonNull
    private NoteDao noteDao;

    @GetMapping
    public String getAllNotes(Model model){
        model.addAttribute("notes", noteDao.findAll());
        return "note/NoteList";
    }

    @GetMapping("/new")
    public String addNewNoteForm(){
        return "notes/AddNewNoteForm";
    }

    @PostMapping()
    public String addNewNote(@ModelAttribute("note") @Valid Note note,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "note/AddNewNoteForm";
        }
        noteDao.saveAll(Collections.singletonList(note));
        return "redirect:/notes";
    }
}
