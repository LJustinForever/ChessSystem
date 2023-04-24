package com.suicidesquad.ChessSystem.service;

import com.suicidesquad.ChessSystem.entity.Admin;
import com.suicidesquad.ChessSystem.entity.User;
import com.suicidesquad.ChessSystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import static com.suicidesquad.ChessSystem.utils.Utils.generateJWT;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    public boolean adminExists(Admin admin){
        Optional<Admin> adminByEmail = adminRepository.findAdminByEmail(admin.getEmailAddress());
        return adminByEmail.isPresent();
    }

    public Admin getAdmin(Long adminId) {
        Optional<Admin> adminById = adminRepository.findById(adminId);
        if(!adminById.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found");
        return adminById.get();
    }

    public Admin getAdmin(String email) {
        Optional<Admin> adminByEmail = adminRepository.findAdminByEmail(email);
        if(adminByEmail.isPresent())
            return adminByEmail.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found");
    }

    public void addNewAdmin(Admin admin) {
        if(adminExists(admin))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email Address is taken");
        admin.encodePassword();
        adminRepository.save(admin);
    }

    public String loginAdmin(Admin admin) throws NoSuchAlgorithmException {
        if(!adminExists(admin))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found");
        admin.encodePassword();
        if(!adminRepository.findAdminByEmailAndPassword(admin.getEmailAddress(), admin.getPassword()).isPresent())
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect Password");
        return generateJWT(admin.toString());
    }
}
