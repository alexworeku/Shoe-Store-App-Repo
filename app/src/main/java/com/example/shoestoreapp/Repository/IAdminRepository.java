package com.example.shoestoreapp.Repository;

import com.example.shoestoreapp.Models.Admin;

public interface IAdminRepository {
    Admin get();
    boolean updateStatus(boolean newStatus);
}
