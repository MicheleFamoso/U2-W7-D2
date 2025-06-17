package it.epicode.U2_W7_D2.service;


import com.cloudinary.Cloudinary;
import it.epicode.U2_W7_D2.dto.DipendenteDto;
import it.epicode.U2_W7_D2.exception.NonTrovatoException;
import it.epicode.U2_W7_D2.model.Dipendente;
import it.epicode.U2_W7_D2.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private JavaMailSenderImpl javaMailSender;


    private void sendMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione Servizio rest");
        message.setText("Registrazione al servizio rest avvenuta con successo");

        javaMailSender.send(message);
    }

    //Save
public Dipendente saveDipendente(DipendenteDto dipendenteDto){
    Dipendente dipendente = new Dipendente();
    dipendente.setNome(dipendenteDto.getNome());
    dipendente.setCognome(dipendenteDto.getCognome());
    dipendente.setUserName(dipendenteDto.getUserName());
    dipendente.setEmail(dipendenteDto.getEmail());
   // sendMail(dipendenteDto.getEmail());
    return dipendenteRepository.save(dipendente);
    }

    public List<Dipendente> getDipendenti(){
        return dipendenteRepository.findAll();
    }

    public Dipendente getDipendente(int id) throws NonTrovatoException {
        return dipendenteRepository.findById(id).orElseThrow(()-> new NonTrovatoException("Dipendente Non trovato."));
    }

    public Dipendente updateDipendente(int id, DipendenteDto dipendenteDto) throws NonTrovatoException {

        Dipendente dipendente = getDipendente(id);
        dipendente.setNome(dipendenteDto.getNome());
        dipendente.setCognome(dipendenteDto.getCognome());
        dipendente.setUserName(dipendenteDto.getUserName());
        dipendente.setEmail(dipendenteDto.getEmail());

        return dipendenteRepository.save(dipendente);



    }

    public void deleteDipendente(int id) throws NonTrovatoException {
        Dipendente dipendenteX = getDipendente(id);
        dipendenteRepository.delete(dipendenteX);
    }

    public String patchDipendente(int id, MultipartFile file) throws NonTrovatoException, IOException {
        Dipendente dipendente = getDipendente(id);
        String url= (String)cloudinary.uploader().upload(file.getBytes(), Collections.emptyMap()).get("url");
        dipendente.setAvatar(url);
        dipendenteRepository.save(dipendente);
        return url;
    }



}
