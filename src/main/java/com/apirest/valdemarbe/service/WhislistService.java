package com.apirest.valdemarbe.service;

import com.apirest.valdemarbe.model.entitybean.Whislist;

public interface WhislistService {
    Whislist findByUser(String email);
}
