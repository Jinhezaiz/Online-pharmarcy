package org.hainu.onlinedrugstore.service;

import org.hainu.onlinedrugstore.entity.User;

public interface LoginService {
    String login(User user, String firstLoginTime) throws Exception;
}
