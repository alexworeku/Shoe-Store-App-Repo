package com.example.shoestoreapp.Controller;

import com.example.shoestoreapp.Models.Admin;
import com.example.shoestoreapp.Repository.IAdminRepository;

public class AdminController {
    private IAdminRepository adminRepository;

    public AdminController(IAdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin getAdmin() {
        return adminRepository.get();
    }
    public boolean changeLoginStatusTo(boolean newStatus) {
        return adminRepository.updateStatus(newStatus);
    }
}
