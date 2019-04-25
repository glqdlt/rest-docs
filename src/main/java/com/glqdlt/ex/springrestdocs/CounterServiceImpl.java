package com.glqdlt.ex.springrestdocs;

import org.springframework.stereotype.Service;

/**
 * @author Jhun
 * 2019-04-25
 */
@Service
public class CounterServiceImpl implements CounterService {
    @Override
    public String greeting(String user) {
        return user + ", nice to meet you.";
    }
}
